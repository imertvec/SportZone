package ru.vagavagus.sportzone.data.retrofit

import retrofit2.http.GET
import retrofit2.http.POST
import ru.vagavagus.sportzone.data.model.UrlResponseDto
import ru.vagavagus.sportzone.domain.model.PlayerDetailsItem
import ru.vagavagus.sportzone.domain.model.PlayerItem

interface PlayersApi {

    @GET("/10/players.json")
    suspend fun fetchPlayerItemsList(): List<PlayerItem>

    @GET("/10/players_details_list.json")
    suspend fun fetchPlayerDetailsById(): List<PlayerDetailsItem>

    @POST("/splash.php")
    suspend fun fetchData(): UrlResponseDto
}