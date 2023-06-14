package ru.vagavagus.sportzone

import android.content.Context
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
import ru.vagavagus.sportzone.presentation.splash.SplashViewModel
import ru.vagavagus.sportzone.presentation.webview.WebViewCompose
import ru.vagavagus.sportzone.ui.theme.SportZoneTheme
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.Base64.Encoder
import java.util.UUID

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
                            val viewModel = getViewModel<SplashViewModel>()
                            viewModel.fetchUrl(
                                name = android.os.Build.MODEL,
                                loc = this@MainActivity.resources.configuration.locale.language,
                                unique = fetchUID()
                            )

                            SplashScreen(
                                onNavigate = {
                                    val state = viewModel.state.value

                                    if(state.url == "no")
                                        navController.navigate(Screen.Home.route) {
                                            popUpTo(route = Screen.Splash.route) {
                                                inclusive = true
                                            }
                                        }
                                    if(state.url == "nopush") {
                                        disablePush()
                                        navController.navigate(Screen.Home.route) {
                                            popUpTo(route = Screen.Splash.route) {
                                                inclusive = true
                                            }
                                        }
                                    }
                                    if(state.url.contains("http")) {
                                        val encodedUrl = URLEncoder.encode(state.url, StandardCharsets.UTF_8.toString())
                                        navController.navigate("${Screen.WebView.route}/$encodedUrl") {
                                            popUpTo(route = Screen.Splash.route) {
                                                inclusive = true
                                            }
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
                        composable(
                            route = "${Screen.WebView.route}/{url}",
                            arguments = listOf(
                                navArgument(name = "url") { NavType.StringType }
                            )
                        ) {
                            val url = it.arguments?.getString("url") ?: "https://google.com"
                            WebViewCompose(url)
                        }
                    }
                }
            }
        }
    }

    private fun fetchUID(): String {
        val sharedPrefs = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
        var unique = sharedPrefs.getString("unique", null)
        unique?.let { return it }
        unique = UUID.randomUUID().toString()

        sharedPrefs.edit().putString("unique", unique).apply()
        return unique
    }

    private fun disablePush() {
        val sharedPrefs = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
        sharedPrefs.edit().putBoolean("disabledpush", true).apply()
    }
}