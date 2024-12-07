package com.mawinda.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MedDTO(
    @SerialName("name")
    val name: String,
    @SerialName("dose")
    val dose: String,
    @SerialName("strength")
    val strength: String
)
