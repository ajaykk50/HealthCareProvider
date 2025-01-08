package kmp.project.projectcodetest.data.repository

import kmp.project.projectcodetest.data.local.entity.AppointmentEntity
import kmp.project.projectcodetest.data.local.entity.DoctorEntity
import kmp.project.projectcodetest.data.local.entity.ServiceEntity
import kmp.project.projectcodetest.domain.model.Appointment
import kmp.project.projectcodetest.domain.model.Doctor
import kmp.project.projectcodetest.domain.model.Service

fun ServiceEntity.toDomain() = Service(id, name, category, price)
fun Service.toEntity() = ServiceEntity(id, name, category, price)

fun DoctorEntity.toDomain() = Doctor(id, name, specialization, experience, availability)
fun Doctor.toEntity() = DoctorEntity(id, name, specialization, experience, availability)

fun AppointmentEntity.toDomain() = Appointment(id, serviceId, doctorId, appointmentDate)
fun Appointment.toEntity() = AppointmentEntity(id, serviceId, doctorId, appointmentDate)
