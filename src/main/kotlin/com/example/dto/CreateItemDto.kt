package com.example.dto

import kotlinx.serialization.Serializable
import com.example.models.*

@Serializable
data class CreateItemDto (
    val name: String,
    val description: String,
    val location: LatLong
) {
}

