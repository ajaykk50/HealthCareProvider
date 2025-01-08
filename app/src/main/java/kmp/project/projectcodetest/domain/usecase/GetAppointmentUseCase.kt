package kmp.project.projectcodetest.domain.usecase

import kmp.project.projectcodetest.domain.model.Appointment
import kmp.project.projectcodetest.domain.model.AppointmentDetails
import kmp.project.projectcodetest.domain.repository.AppointmentRepository
import javax.inject.Inject

class GetAppointmentUseCase @Inject constructor(
    private val appointmentRepository: AppointmentRepository
) {
    suspend operator fun invoke() {
        appointmentRepository.getAllAppointments()
    }

    suspend fun getAppointments(): List<AppointmentDetails> {
        return appointmentRepository.getAllAppointmentsWithDetails()
    }

    suspend fun getTotalServicePrice(): Double {
        return appointmentRepository.getTotalServicePrice()
    }

    suspend fun deleteAppointment(appointmentId: Int) {
        appointmentRepository.deleteAppointmentById(appointmentId)
    }
}
