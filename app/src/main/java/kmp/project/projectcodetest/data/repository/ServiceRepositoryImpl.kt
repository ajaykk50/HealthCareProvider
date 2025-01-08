package kmp.project.projectcodetest.data.repository

import kmp.project.projectcodetest.data.local.dao.ServiceDao
import kmp.project.projectcodetest.domain.model.Service
import kmp.project.projectcodetest.domain.repository.ServiceRepository
import javax.inject.Inject


class ServiceRepositoryImpl @Inject constructor(
    private val serviceDao: ServiceDao
) : ServiceRepository {

    override suspend fun getServicesByCategory(category: String): List<Service> {
        return serviceDao.getServicesByCategory(category).map { it.toDomain() }
    }

    override suspend fun getServiceById(serviceId: Int): Service? {
        return serviceDao.getServiceById(serviceId)?.toDomain()
    }

    override suspend fun insertServices(services: List<Service>) {
        serviceDao.insertServices(services.map { it.toEntity() })
    }

    override suspend fun getAllCategory(): List<String> {
       return serviceDao.getAllCategories()
    }

    override suspend fun getServiceIdByName(serviceName: String): Int? {
        return serviceDao.getServiceIdByName(serviceName)
    }
}
