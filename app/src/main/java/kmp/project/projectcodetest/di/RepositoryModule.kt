package kmp.project.projectcodetest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kmp.project.projectcodetest.data.repository.AppointmentRepositoryImpl
import kmp.project.projectcodetest.data.repository.DoctorRepositoryImpl
import kmp.project.projectcodetest.data.repository.ServiceRepositoryImpl
import kmp.project.projectcodetest.domain.repository.AppointmentRepository
import kmp.project.projectcodetest.domain.repository.DoctorRepository
import kmp.project.projectcodetest.domain.repository.ServiceRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindServiceRepository(
        repositoryImpl: ServiceRepositoryImpl
    ): ServiceRepository

    @Binds
    @Singleton
    abstract fun bindDoctorRepository(
        repositoryImpl: DoctorRepositoryImpl
    ): DoctorRepository

    @Binds
    @Singleton
    abstract fun bindAppointmentRepository(
        repositoryImpl: AppointmentRepositoryImpl
    ): AppointmentRepository
}