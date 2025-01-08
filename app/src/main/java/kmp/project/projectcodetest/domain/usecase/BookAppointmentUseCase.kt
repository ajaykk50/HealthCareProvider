package kmp.project.projectcodetest.domain.usecase

import kmp.project.projectcodetest.domain.model.Appointment
import kmp.project.projectcodetest.domain.repository.AppointmentRepository
import javax.inject.Inject

class BookAppointmentUseCase @Inject constructor(
    private val appointmentRepository: AppointmentRepository
) {
    suspend operator fun invoke(appointment: Appointment) {
        appointmentRepository.insertAppointment(appointment)
    }

    suspend fun isDoctorAlreadyBooked(doctorId: Int): Boolean? {
        return appointmentRepository.isDoctorBooked(doctorId)
    }

    suspend fun isServiceAlreadyBooked(serviceId: Int): Boolean? {
        return appointmentRepository.isServiceBooked(serviceId)
    }
}
