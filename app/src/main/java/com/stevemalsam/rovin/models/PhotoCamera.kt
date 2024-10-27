package com.stevemalsam.rovin.models

import kotlinx.serialization.Serializable

@Serializable
data class PhotoCamera(val id: Int,
    val name: String,
    val roverId: Int,
    val fullName: String)
