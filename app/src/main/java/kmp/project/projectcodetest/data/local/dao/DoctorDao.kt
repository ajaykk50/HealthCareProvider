package kmp.project.projectcodetest.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kmp.project.projectcodetest.data.local.entity.DoctorEntity

@Dao
interface DoctorDao {
    @Query("SELECT * FROM doctors WHERE specialization = :specialization")
    fun getDoctorsBySpecialization(specialization: String): List<DoctorEntity>

    @Query("SELECT * FROM doctors WHERE id = :doctorId")
    fun getDoctorById(doctorId: Int): DoctorEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoctors(doctors: List<DoctorEntity>)
}
