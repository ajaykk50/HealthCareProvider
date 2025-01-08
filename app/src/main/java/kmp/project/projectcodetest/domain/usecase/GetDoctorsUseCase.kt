package kmp.project.projectcodetest.domain.usecase

import kmp.project.projectcodetest.domain.model.Doctor
import kmp.project.projectcodetest.domain.repository.DoctorRepository
import javax.inject.Inject

class GetDoctorsUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository
) {
    suspend operator fun invoke(specialization: String): List<Doctor> {
        return doctorRepository.getDoctorsBySpecialization(specialization)
    }
}
