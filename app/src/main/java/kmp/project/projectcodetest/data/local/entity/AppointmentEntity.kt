package kmp.project.projectcodetest.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointments")
data class AppointmentEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val serviceId: Int,
    val doctorId: Int,
    val appointmentDate: String
)
