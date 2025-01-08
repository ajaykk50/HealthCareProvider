package kmp.project.projectcodetest.presentation.services

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dagger.hilt.android.lifecycle.HiltViewModel
import kmp.project.projectcodetest.domain.model.Appointment
import kmp.project.projectcodetest.domain.model.CategoryWithServices
import kmp.project.projectcodetest.domain.model.Service
import kmp.project.projectcodetest.domain.usecase.BookAppointmentUseCase
import kmp.project.projectcodetest.domain.usecase.GetCategoryUseCase
import kmp.project.projectcodetest.domain.usecase.GetServicesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ServiceListViewModel @Inject constructor(
    private val application: Application,
    private val getServicesUseCase: GetServicesUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val bookAppointmentUseCase: BookAppointmentUseCase
) : ViewModel() {

    private val _categoryWithServices = MutableLiveData<List<CategoryWithServices>>()
    val categoryWithServices: LiveData<List<CategoryWithServices>> get() = _categoryWithServices

    init {
        loadCategoriesWithServices()
    }

    private fun loadCategoriesWithServices() {
        viewModelScope.launch(Dispatchers.IO) {
            val categories = getCategoryUseCase()
            val categoryWithServicesList = categories.map { category ->
                val services = getServicesUseCase(category)
                CategoryWithServices(category, services)
            }
            _categoryWithServices.postValue(categoryWithServicesList)
        }
    }

    suspend fun checkServiceBooking(serviceId: Int): Boolean? {
        return bookAppointmentUseCase.isServiceAlreadyBooked(serviceId)
    }

    fun bookAppointment(doctorId: Int, serviceId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val isServiceAlreadyBooked = checkServiceBooking(serviceId.toInt())
                if (isServiceAlreadyBooked == true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            application.applicationContext,
                            "Service is already booked!",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                } else {
                    val currentDate =
                        java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
                            .format(java.util.Date())

                    val appointment = Appointment(
                        doctorId = doctorId,
                        serviceId = serviceId.toInt(),
                        appointmentDate = currentDate
                    )

                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            application.applicationContext,
                            "Service booked successfully!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    bookAppointmentUseCase(appointment)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        application.applicationContext,
                        "Failed to book service: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}