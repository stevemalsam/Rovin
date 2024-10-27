package com.stevemalsam.rovin.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Rover(val id: Int,
    val name: String,
    val landingDate: LocalDate,
    val launchDate: LocalDate,
    val status: String = "active",
    val maxSol: Int,
    val maxDate: LocalDate,
    val totalPhotos: Int = 0,
    val cameras: Array<RoverCamera>)
