package ru.vagavagus.sportzone.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Team(
    @Json(name = "image_url") val imageUrl: String,
    @Json(name = "name") val name: String
)