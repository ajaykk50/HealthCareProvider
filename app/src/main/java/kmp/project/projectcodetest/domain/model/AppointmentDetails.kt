package kmp.project.projectcodetest.domain.model

data class AppointmentDetails(
    val appointmentId: Int,
    val serviceName: String,
    val servicePrice: Double,
    val doctorName: String?,
    val doctorSpecialization: String?,
    val doctorExperience: Int?,
    val doctorAvailability: Boolean?
)
