package ru.vagavagus.sportzone.presentation.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.vagavagus.sportzone.presentation.details.components.DetailsScreenState
import ru.vagavagus.sportzone.presentation.details.ui_components.PlayerDetailsCard
import ru.vagavagus.sportzone.ui.theme.SportZoneTheme

@Composable
fun DetailsScreen(
    state: DetailsScreenState,
    onBackPressed: () -> Unit,
    onLaunch: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        onLaunch()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if(state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else if(state.error != null) {
            Text(text = "Error: ${state.error}")
        } else if(state.data != null) {
            PlayerDetailsCard(
                details = state.data,
                onBackPressed = onBackPressed
            )
        }
    }
}

@Preview
@Composable
private fun DetailsScreenPreview() {
    SportZoneTheme {
        DetailsScreen(
            state = DetailsScreenState(),
            onBackPressed = {},
            onLaunch = {}
        )
    }
}