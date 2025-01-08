package kmp.project.projectcodetest.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kmp.project.projectcodetest.data.local.dao.AppointmentDao
import kmp.project.projectcodetest.data.local.dao.DoctorDao
import kmp.project.projectcodetest.data.local.dao.ServiceDao
import kmp.project.projectcodetest.data.local.entity.AppointmentEntity
import kmp.project.projectcodetest.data.local.entity.DoctorEntity
import kmp.project.projectcodetest.data.local.entity.ServiceEntity

@Database(
    entities = [ServiceEntity::class, DoctorEntity::class, AppointmentEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun serviceDao(): ServiceDao
    abstract fun doctorDao(): DoctorDao
    abstract fun appointmentDao(): AppointmentDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        const val DATABASE_NAME = "healthcare_app_db"

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "healthcare_app_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
