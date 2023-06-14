package ru.vagavagus.sportzone.presentation.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.vagavagus.sportzone.domain.model.UrlResponse
import ru.vagavagus.sportzone.domain.use_case.FetchUrlUseCase

class SplashViewModel(
    private val fetchUrlUseCase: FetchUrlUseCase
): ViewModel() {
    private val _state: MutableState<UrlResponse> = mutableStateOf(UrlResponse("no"))
    val state: State<UrlResponse> = _state

    fun fetchUrl(name: String, loc: String, unique: String) = viewModelScope.launch(Dispatchers.IO) {
        fetchUrlUseCase(name, loc, unique).collect { result ->
            if(result.isFailure) _state.value = UrlResponse("no")
            if(result.isSuccess) _state.value = result.getOrNull() ?: UrlResponse("no")
        }
    }
}