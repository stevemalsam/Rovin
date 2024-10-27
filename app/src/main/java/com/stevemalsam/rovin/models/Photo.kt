package com.stevemalsam.rovin.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Photo(val id: Int,
    val sol: Int,
    val camera: PhotoCamera,
    val imgSrc: String,
    val earthDate: LocalDate,
    val rover: Rover)
