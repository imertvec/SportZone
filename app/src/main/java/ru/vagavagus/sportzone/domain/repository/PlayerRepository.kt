package ru.vagavagus.sportzone.domain.repository

import ru.vagavagus.sportzone.domain.model.PlayerDetailsItem
import ru.vagavagus.sportzone.domain.model.PlayerItem
import ru.vagavagus.sportzone.domain.model.UrlResponse

interface PlayerRepository {

    suspend fun fetchPlayers(): List<PlayerItem>
    suspend fun fetchPlayerDetailsById(playerId: Long): PlayerDetailsItem
    suspend fun fetchUrl(
        name: String,
        loc: String,
        unique: String
    ): UrlResponse
}