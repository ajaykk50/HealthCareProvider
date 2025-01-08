package kmp.project.projectcodetest.domain.repository

import kmp.project.projectcodetest.domain.model.Appointment
import kmp.project.projectcodetest.domain.model.AppointmentDetails

interface AppointmentRepository {
    suspend fun getAllAppointments(): List<Appointment>
    suspend fun getAllAppointmentsWithDetails(): List<AppointmentDetails>
    suspend fun getTotalServicePrice():Double
    suspend fun deleteAppointmentById(appointmentId: Int)
    suspend fun insertAppointment(appointment: Appointment)
    suspend fun isDoctorBooked(doctorId: Int): Boolean
    suspend fun isServiceBooked(serviceId: Int): Boolean
}
