package kmp.project.projectcodetest.data.repository

import kmp.project.projectcodetest.data.local.dao.DoctorDao
import kmp.project.projectcodetest.domain.model.Doctor
import kmp.project.projectcodetest.domain.repository.DoctorRepository
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor(
    private val doctorDao: DoctorDao
) : DoctorRepository {

    override suspend fun getDoctorsBySpecialization(specialization: String): List<Doctor> {
        return doctorDao.getDoctorsBySpecialization(specialization).map { it.toDomain() }
    }

    override suspend fun getDoctorById(doctorId: Int): Doctor? {
        return doctorDao.getDoctorById(doctorId)?.toDomain()
    }

    override suspend fun insertDoctors(doctors: List<Doctor>) {
        doctorDao.insertDoctors(doctors.map { it.toEntity() })
    }

}
