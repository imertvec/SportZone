package ru.vagavagus.sportzone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import ru.vagavagus.sportzone.presentation.Screen
import ru.vagavagus.sportzone.presentation.details.DetailsScreen
import ru.vagavagus.sportzone.presentation.details.components.DetailsViewModel
import ru.vagavagus.sportzone.presentation.home.HomeScreen
import ru.vagavagus.sportzone.presentation.home.components.HomeViewModel
import ru.vagavagus.sportzone.presentation.splash.SplashScreen
import ru.vagavagus.sportzone.ui.theme.SportZoneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SportZoneTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Splash.route
                    ) {
                        composable(route = Screen.Splash.route) {
                            SplashScreen(
                                onNavigate = {
                                    navController.navigate(Screen.Home.route) {
                                        popUpTo(route = Screen.Splash.route) {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }
                        composable(route = Screen.Home.route) {
                            val viewModel = getViewModel<HomeViewModel>()
                            val state = viewModel.stateFlow.collectAsState().value
                            HomeScreen(
                                state = state,
                                onPlayerClick = { playerId ->
                                    println("pass playerId = $playerId")
                                    navController.navigate("${Screen.Details.route}/$playerId")
                                }
                            )
                        }
                        composable(
                            route = "${Screen.Details.route}/{playerId}",
                            arguments = listOf(
                                navArgument(name = "playerId") { NavType.StringType }
                            )
                        ) {
                            val playerId = it.arguments?.getString("playerId")?.toLong() ?: 0
                            val viewModel = getViewModel<DetailsViewModel> { parametersOf(playerId) }
                            val state = viewModel.stateFlow.collectAsState().value
                            DetailsScreen(
                                state = state,
                                onBackPressed = { navController.popBackStack() },
                                onLaunch = { viewModel.fetchDetailsById(playerId) }
                            )
                        }
                    }
                }
            }
        }
    }
}