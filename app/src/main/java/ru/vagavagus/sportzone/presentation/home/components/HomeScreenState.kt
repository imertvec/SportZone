package ru.vagavagus.sportzone.presentation.home.components

import ru.vagavagus.sportzone.domain.model.PlayerItem

data class HomeScreenState(
    val isLoading: Boolean = true,
    val data: List<PlayerItem>? = null,
    val error: String? = null
)
