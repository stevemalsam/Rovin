package com.stevemalsam.rovin.network.models

import kotlinx.serialization.Serializable

@Serializable
data class JsonResponse(val photos: List<Photo>)
