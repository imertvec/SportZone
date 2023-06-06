package ru.vagavagus.sportzone.presentation.details.components

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vagavagus.sportzone.domain.repository.PlayerRepository

class DetailsViewModel(
    private val repository: PlayerRepository
): ViewModel() {

    private val _stateFlow = MutableStateFlow(DetailsScreenState())
    val stateFlow: StateFlow<DetailsScreenState> = _stateFlow

    fun fetchDetailsById(playerId: Long) = viewModelScope.launch(Dispatchers.IO) {
        try {
            _stateFlow.update {
                DetailsScreenState(
                    isLoading = false,
                    data = repository.fetchPlayerDetailsById(playerId)
                )
            }
        } catch (e: Exception) {
            _stateFlow.update { DetailsScreenState(isLoading = false, error = e.localizedMessage) }
        }
    }
 }