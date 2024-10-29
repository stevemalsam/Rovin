package com.stevemalsam.rovin.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rover(
    val id: Int,
    val name: String,
    @SerialName("landing_date") val landingDate: LocalDate,
    @SerialName("launch_date") val launchDate: LocalDate,
    val status: String = "active",
    @SerialName("max_sol") val maxSol: Int,
    @SerialName("max_date") val maxDate: LocalDate,
    @SerialName("total_photos") val totalPhotos: Int = 0,
    val cameras: List<RoverCamera>
)
