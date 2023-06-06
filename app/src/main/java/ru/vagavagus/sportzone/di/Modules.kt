package ru.vagavagus.sportzone.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.vagavagus.sportzone.common.Constants.BASE_URL
import ru.vagavagus.sportzone.data.repository.PlayerRepositoryImpl
import ru.vagavagus.sportzone.data.retrofit.PlayersApi
import ru.vagavagus.sportzone.domain.repository.PlayerRepository
import ru.vagavagus.sportzone.presentation.home.components.HomeViewModel
import ru.vagavagus.sportzone.presentation.details.components.DetailsViewModel

val dataModule = module {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    single<PlayersApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(PlayersApi::class.java)
    }

    single<PlayerRepository> { PlayerRepositoryImpl(api = get()) }
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailsViewModel)
}