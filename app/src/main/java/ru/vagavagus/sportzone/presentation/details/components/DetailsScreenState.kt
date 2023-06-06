package ru.vagavagus.sportzone.presentation.details.components

import ru.vagavagus.sportzone.domain.model.PlayerDetailsItem

data class DetailsScreenState(
    val isLoading: Boolean = true,
    val data: PlayerDetailsItem? = null,
    val error: String? = null
)
