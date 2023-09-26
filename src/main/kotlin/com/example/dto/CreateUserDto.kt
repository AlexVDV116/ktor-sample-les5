package com.example.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserDto (val name: String, val email: String)