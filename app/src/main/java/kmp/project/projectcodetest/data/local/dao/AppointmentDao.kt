package kmp.project.projectcodetest.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kmp.project.projectcodetest.data.local.entity.AppointmentEntity
import kmp.project.projectcodetest.domain.model.AppointmentDetails

@Dao
interface AppointmentDao {
    @Query("SELECT * FROM appointments")
    fun getAllAppointments(): List<AppointmentEntity>

    @Query("SELECT * FROM appointments WHERE id = :appointmentId")
    fun getAppointmentById(appointmentId: Int): AppointmentEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAppointment(appointment: AppointmentEntity)


    @Query("""
        SELECT 
            a.id as appointmentId, 
            s.name as serviceName, 
            s.price as servicePrice, 
            d.name as doctorName, 
            d.specialization as doctorSpecialization,
            d.experience as doctorExperience,
            d.availability as doctorAvailability
        FROM appointments a
        LEFT JOIN services s ON a.serviceId = s.id
        LEFT JOIN doctors d ON a.doctorId = d.id
    """)
    fun getAllAppointmentsWithDetails(): List<AppointmentDetails>

    @Query("SELECT SUM(s.price) FROM appointments a INNER JOIN services s ON a.serviceId = s.id")
    fun getTotalServicePrice(): Double

    @Query("DELETE FROM appointments WHERE id = :appointmentId")
    fun deleteAppointmentById(appointmentId: Int)

    @Query("SELECT COUNT(*) > 0 FROM appointments WHERE doctorId = :doctorId")
    fun isDoctorBooked(doctorId: Int): Boolean

    @Query("SELECT COUNT(*) > 0 FROM appointments WHERE serviceId = :serviceId")
    fun isServiceBooked(serviceId: Int): Boolean
}
