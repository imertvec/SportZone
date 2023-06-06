package ru.vagavagus.sportzone.domain.repository

import ru.vagavagus.sportzone.domain.model.PlayerDetailsItem
import ru.vagavagus.sportzone.domain.model.PlayerItem

interface PlayerRepository {

    suspend fun fetchPlayers(): List<PlayerItem>
    suspend fun fetchPlayerDetailsById(playerId: Long): PlayerDetailsItem

}