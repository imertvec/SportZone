package ru.vagavagus.sportzone.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlayerDetailsItem(
    @Json(name = "best_foot") val bestFoot: String?,
    @Json(name = "born_on") val bornOn: String?,
    @Json(name = "first_cap") val firstCap: String?,
    @Json(name = "first_international_goal") val firstInternationalGoal: String?,
    @Json(name = "goals") val goals: Int?,
    @Json(name = "height") val height: String?,
    @Json(name = "id") val id: Long,
    @Json(name = "image_url") val imageUrl: String,
    @Json(name = "impact_assist") val impactAssist: Int?,
    @Json(name = "impact_goals") val impactGoals: Int?,
    @Json(name = "minutes_played") val minutesPlayed: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "nationality") val nationality: String?,
    @Json(name = "nums_of_international_caps") val numsOfInternationalCaps: String?,
    @Json(name = "player_matches") val playedMatches: Int?,
    @Json(name = "team") val team: Team,
    @Json(name = "weight") val weight: String?
)