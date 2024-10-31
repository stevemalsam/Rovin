package com.stevemalsam.rovin.network.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val id: Int,
    val sol: Int,
    val camera: PhotoCamera,
    @SerialName("img_src") val imgSrc: String,
    @SerialName("earth_date") val earthDate: LocalDate,
    val rover: Rover
)
