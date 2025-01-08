package kmp.project.projectcodetest.domain.usecase

import kmp.project.projectcodetest.domain.model.Service
import kmp.project.projectcodetest.domain.repository.ServiceRepository
import javax.inject.Inject

class GetServicesUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository
) {
    suspend operator fun invoke(category: String): List<Service> {
        return serviceRepository.getServicesByCategory(category)
    }

    suspend fun getIdByServiceName(serviceName: String): Int? {
        return serviceRepository.getServiceIdByName(serviceName)
    }
}
