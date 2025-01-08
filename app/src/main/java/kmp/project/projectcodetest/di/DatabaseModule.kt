package kmp.project.projectcodetest.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kmp.project.projectcodetest.data.local.AppDatabase
import kmp.project.projectcodetest.data.local.dao.AppointmentDao
import kmp.project.projectcodetest.data.local.dao.DoctorDao
import kmp.project.projectcodetest.data.local.dao.ServiceDao
import kmp.project.projectcodetest.data.local.entity.DoctorEntity
import kmp.project.projectcodetest.data.local.entity.ServiceEntity
import kmp.project.projectcodetest.domain.ServiceCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                CoroutineScope(Dispatchers.IO).launch {
                    val database = AppDatabase.getInstance(context)
                    populateInitialData(database)
                }
            }
        }).build()
    }

    @Provides
    @Singleton
    fun provideServiceDao(database: AppDatabase): ServiceDao {
        return database.serviceDao()
    }

    @Provides
    @Singleton
    fun provideDoctorDao(database: AppDatabase): DoctorDao {
        return database.doctorDao()
    }

    @Provides
    @Singleton
    fun provideAppointmentDao(database: AppDatabase): AppointmentDao {
        return database.appointmentDao()
    }

    private fun populateInitialData(database: AppDatabase) {
        // Services
        val services = listOf(
            ServiceEntity(
                name = "General Physician",
                category = ServiceCategory.DOCTOR_CONSULTATION.name,
                price = 50.0
            ),
            ServiceEntity(
                name = "Cardiologist",
                category = ServiceCategory.DOCTOR_CONSULTATION.name,
                price = 100.0
            ),
            ServiceEntity(
                name = "Blood Test",
                category = ServiceCategory.DIAGNOSTICS.name,
                price = 30.0
            ),
            ServiceEntity(
                name = "MRI Scan",
                category = ServiceCategory.DIAGNOSTICS.name,
                price = 250.0
            ),
            ServiceEntity(
                name = "Annual Health Checkup",
                category = ServiceCategory.HEALTH_PACKAGES.name,
                price = 200.0
            ),
            ServiceEntity(
                name = "Diabetes Screening",
                category = ServiceCategory.HEALTH_PACKAGES.name,
                price = 80.0
            )
        )

        database.serviceDao().insertServices(services)

        // Doctors
        val doctors = listOf(
            DoctorEntity(
                name = "Dr. John Smith",
                specialization = "General Physician",
                experience = 10,
                availability = true
            ),
            DoctorEntity(
                name = "Dr. Sarah Johnson",
                specialization = "General Physician",
                experience = 10,
                availability = true
            ),
            DoctorEntity(
                name = "Dr. Michael Brown",
                specialization = "Cardiologist",
                experience = 10,
                availability = true
            ),
            DoctorEntity(
                name = "Dr. Emily Davis",
                specialization = "Cardiologist",
                experience = 10,
                availability = true
            )
        )

        val doctorIds = database.doctorDao().insertDoctors(doctors)
    }
}
