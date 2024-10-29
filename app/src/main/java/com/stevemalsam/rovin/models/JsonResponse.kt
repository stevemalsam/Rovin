package com.stevemalsam.rovin.models

import kotlinx.serialization.Serializable

@Serializable
data class JsonResponse(val photos: List<Photo>)
