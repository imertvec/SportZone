package ru.vagavagus.sportzone.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UrlResponseDto(
    @Json(name = "url") val url: String
)