package ru.vagavagus.sportzone.presentation.home.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vagavagus.sportzone.domain.repository.PlayerRepository

class HomeViewModel(
    private val repository: PlayerRepository
): ViewModel() {

    private val _stateFlow = MutableStateFlow(HomeScreenState())
    val stateFlow: StateFlow<HomeScreenState> = _stateFlow

    init {
        fetchPlayers()
    }

    private fun fetchPlayers() = viewModelScope.launch {
        _stateFlow.update { HomeScreenState(isLoading = false, data = repository.fetchPlayers()) }
    }
}