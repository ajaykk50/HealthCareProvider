package kmp.project.projectcodetest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kmp.project.projectcodetest.data.local.entity.ServiceEntity

@Dao
interface ServiceDao {
    @Query("SELECT * FROM services WHERE category = :category")
    fun getServicesByCategory(category: String): List<ServiceEntity>

    @Query("SELECT * FROM services WHERE id = :serviceId")
    fun getServiceById(serviceId: Int): ServiceEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertServices(services: List<ServiceEntity>)

    @Query("SELECT DISTINCT category FROM services")
    fun getAllCategories(): List<String>

    @Query("SELECT id FROM services WHERE name = :serviceName LIMIT 1")
    suspend fun getServiceIdByName(serviceName: String): Int?
}
