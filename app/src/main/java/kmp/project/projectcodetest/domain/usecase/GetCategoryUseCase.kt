package kmp.project.projectcodetest.domain.usecase

import kmp.project.projectcodetest.domain.model.Service
import kmp.project.projectcodetest.domain.repository.ServiceRepository
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository
) {
    suspend operator fun invoke(): List<String> {
        return serviceRepository.getAllCategory()
    }
}
