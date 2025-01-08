package kmp.project.projectcodetest.domain.repository

import kmp.project.projectcodetest.domain.model.Service

interface ServiceRepository {
    suspend fun getServicesByCategory(category: String): List<Service>
    suspend fun getServiceById(serviceId: Int): Service?
    suspend fun insertServices(services: List<Service>)
    suspend fun getAllCategory():List<String>
    suspend fun getServiceIdByName(serviceName: String): Int?
}
