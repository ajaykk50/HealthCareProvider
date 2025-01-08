package kmp.project.projectcodetest.domain.model

data class Appointment(
    val id: Int = 0,
    val serviceId: Int,
    val doctorId: Int,
    val appointmentDate: String
)
