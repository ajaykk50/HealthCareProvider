package kmp.project.projectcodetest.data.repository

import kmp.project.projectcodetest.data.local.dao.AppointmentDao
import kmp.project.projectcodetest.domain.model.Appointment
import kmp.project.projectcodetest.domain.model.AppointmentDetails
import kmp.project.projectcodetest.domain.repository.AppointmentRepository
import javax.inject.Inject

class AppointmentRepositoryImpl @Inject constructor(
    private val appointmentDao: AppointmentDao
) : AppointmentRepository {

    override suspend fun getAllAppointments(): List<Appointment> {
        return appointmentDao.getAllAppointments().map { it.toDomain() }
    }

    override suspend fun getAllAppointmentsWithDetails(): List<AppointmentDetails> {
        return appointmentDao.getAllAppointmentsWithDetails()
    }

    override suspend fun getTotalServicePrice(): Double {
        return appointmentDao.getTotalServicePrice()
    }

    override suspend fun deleteAppointmentById(appointmentId: Int) {
        appointmentDao.deleteAppointmentById(appointmentId)
    }

    override suspend fun insertAppointment(appointment: Appointment) {
        appointmentDao.insertAppointment(appointment.toEntity())
    }

    override suspend fun isDoctorBooked(doctorId: Int): Boolean {
       return appointmentDao.isDoctorBooked(doctorId)
    }

    override suspend fun isServiceBooked(serviceId: Int): Boolean {
        return appointmentDao.isServiceBooked(serviceId)
    }
}
