package ru.vagavagus.sportzone.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlayerItem(
    @Json(name = "id") val id: Long,
    @Json(name = "country_image_url") val countryImageUrl: String,
    @Json(name = "name") val name: String,
    @Json(name = "goals") val goals: Int
)
