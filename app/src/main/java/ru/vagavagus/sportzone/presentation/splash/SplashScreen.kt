package ru.vagavagus.sportzone.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import ru.vagavagus.sportzone.R
import ru.vagavagus.sportzone.ui.theme.LightMainBackground
import ru.vagavagus.sportzone.ui.theme.MainBackground
import ru.vagavagus.sportzone.ui.theme.SportZoneTheme

@Composable
fun SplashScreen(
    onNavigate: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        delay(2000)
        onNavigate()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackground)
    ) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.Center)
                .clip(shape = CircleShape)
                .background(LightMainBackground)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = R.string.app_name_splitted),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 50.sp,
                    color = Color.White
                )
            )
        }
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SportZoneTheme {
        SplashScreen(
            onNavigate = {}
        )
    }
}