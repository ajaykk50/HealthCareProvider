package kmp.project.projectcodetest.domain

enum class ServiceCategory(val displayName: String) {
    DOCTOR_CONSULTATION("Doctor Consultation"),
    DIAGNOSTICS("Diagnostics"),
    HEALTH_PACKAGES("Health Packages");

    companion object {
        fun fromString(value: String): ServiceCategory =
            values().find { it.name == value } ?: throw IllegalArgumentException("Invalid category: $value")
    }
}