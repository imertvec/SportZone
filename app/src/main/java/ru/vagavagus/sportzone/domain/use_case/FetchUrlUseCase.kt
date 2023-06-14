package ru.vagavagus.sportzone.domain.use_case

import kotlinx.coroutines.flow.flow
import ru.vagavagus.sportzone.domain.repository.PlayerRepository

class FetchUrlUseCase(
    private val repository: PlayerRepository
){
    operator fun invoke(
        name: String,
        loc: String,
        unique: String
    ) = flow {
        try {
            val data = repository.fetchUrl(name, loc, unique)
            emit(Result.success(data))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}