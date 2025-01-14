package com.stevemalsam.rovin.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoCamera(
    val id: Int,
    val name: String,
    @SerialName("rover_id") val roverId: Int,
    @SerialName("full_name") val fullName: String
)
