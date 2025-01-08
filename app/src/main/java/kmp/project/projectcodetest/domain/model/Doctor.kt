package kmp.project.projectcodetest.domain.model

data class Doctor(
    val id: Int = 0,
    val name: String,
    val specialization: String,
    val experience: Int,
    val availability: Boolean
)
