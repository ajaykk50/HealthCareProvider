package kmp.project.projectcodetest.domain.repository

import kmp.project.projectcodetest.domain.model.Doctor

interface DoctorRepository {
    suspend fun getDoctorsBySpecialization(specialization: String): List<Doctor>
    suspend fun getDoctorById(doctorId: Int): Doctor?
    suspend fun insertDoctors(doctors: List<Doctor>)
}
