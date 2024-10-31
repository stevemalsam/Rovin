package com.stevemalsam.rovin.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoverCamera(val name: String, @SerialName("full_name") val fullName: String)