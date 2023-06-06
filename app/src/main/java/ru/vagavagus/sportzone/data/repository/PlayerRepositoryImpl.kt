package ru.vagavagus.sportzone.data.repository

import ru.vagavagus.sportzone.data.retrofit.PlayersApi
import ru.vagavagus.sportzone.domain.model.PlayerDetailsItem
import ru.vagavagus.sportzone.domain.model.PlayerItem
import ru.vagavagus.sportzone.domain.repository.PlayerRepository

class PlayerRepositoryImpl(
    private val api: PlayersApi
): PlayerRepository {

    override suspend fun fetchPlayers(): List<PlayerItem> {
        return api.fetchPlayerItemsList()
    }

    override suspend fun fetchPlayerDetailsById(playerId: Long): PlayerDetailsItem {
        return api.fetchPlayerDetailsById().first { it.id == playerId }
    }
}