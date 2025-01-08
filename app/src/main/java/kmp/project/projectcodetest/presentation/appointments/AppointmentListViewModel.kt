package kmp.project.projectcodetest.presentation.appointments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kmp.project.projectcodetest.domain.model.AppointmentDetails
import kmp.project.projectcodetest.domain.usecase.GetAppointmentUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppointmentListViewModel @Inject constructor(
    private val getAppointmentsUseCase: GetAppointmentUseCase
) : ViewModel() {

    private val _appointments = MutableLiveData<List<AppointmentDetails>>()
    val appointments: LiveData<List<AppointmentDetails>> get() = _appointments

    private val _totalServicePrice = MutableLiveData<Double>()
    val totalServicePrice: LiveData<Double> get() = _totalServicePrice

    init {
        loadAppointments()
    }

    private fun loadAppointments() {
        viewModelScope.launch(Dispatchers.IO) {
            val appointmentsList = getAppointmentsUseCase.getAppointments()
            val totalPrice = getAppointmentsUseCase.getTotalServicePrice()

            _appointments.postValue(appointmentsList)
            _totalServicePrice.postValue(totalPrice)
        }
    }

    fun deleteAppointment(appointmentId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getAppointmentsUseCase.deleteAppointment(appointmentId)
            loadAppointments()
        }
    }
}