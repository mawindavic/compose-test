package com.mawinda.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Med(
    val id: Int,
    val name: String,
    val dose: String,
    val strength: String
)