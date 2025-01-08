package kmp.project.projectcodetest.presentation.doctors

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kmp.project.projectcodetest.domain.model.Appointment
import kmp.project.projectcodetest.domain.model.Doctor
import kmp.project.projectcodetest.domain.usecase.BookAppointmentUseCase
import kmp.project.projectcodetest.domain.usecase.GetDoctorsUseCase
import kmp.project.projectcodetest.domain.usecase.GetServicesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DoctorListViewModel @Inject constructor(
    private val application: Application,
    private val getDoctorsUseCase: GetDoctorsUseCase,
    private val bookAppointmentUseCase: BookAppointmentUseCase,
    private val getServicesUseCase: GetServicesUseCase
) : ViewModel() {

    private val _doctorsList = MutableLiveData<List<Doctor>>()
    val doctorsList: LiveData<List<Doctor>> get() = _doctorsList


    fun loadDoctors(specialization: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val doctors = getDoctorsUseCase(specialization)
            _doctorsList.postValue(doctors)
        }
    }


    fun bookAppointment(doctorId: Int, specialization: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val isDoctorAlreadyBooked = checkDoctorBooking(doctorId)
                if (isDoctorAlreadyBooked == true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            application.applicationContext,
                            "Doctor is already booked!",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                } else {

                    val currentDate =
                        java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
                            .format(java.util.Date())

                    val serviceId = getServicesUseCase.getIdByServiceName(specialization)

                    val appointment = Appointment(
                        doctorId = doctorId,
                        serviceId = serviceId!!,
                        appointmentDate = currentDate
                    )

                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            application.applicationContext,
                            "Appointment booked successfully!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    bookAppointmentUseCase(appointment)

                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        application.applicationContext,
                        "Failed to book appointment: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        }
    }

    suspend fun checkDoctorBooking(doctorId: Int): Boolean? {
        return bookAppointmentUseCase.isDoctorAlreadyBooked(doctorId)
    }
}