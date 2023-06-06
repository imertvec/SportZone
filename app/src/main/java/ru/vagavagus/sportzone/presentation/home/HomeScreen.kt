package ru.vagavagus.sportzone.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vagavagus.sportzone.R
import ru.vagavagus.sportzone.presentation.home.components.HomeScreenState
import ru.vagavagus.sportzone.presentation.home.ui_components.PlayerCard
import ru.vagavagus.sportzone.ui.theme.GraySpacerBackgroundColor
import ru.vagavagus.sportzone.ui.theme.LightPurple
import ru.vagavagus.sportzone.ui.theme.MainBackground
import ru.vagavagus.sportzone.ui.theme.SportZoneTheme
import ru.vagavagus.sportzone.ui.theme.TitleListBackground

@Composable
fun HomeScreen(
    state: HomeScreenState,
    onPlayerClick: (playerId: Long) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        if(state.isLoading) CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = Color.White
        ) else if(state.error != null) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = state.error,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color.White
                )
            )
        } else if(state.data != null) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                            .background(TitleListBackground)
                            .padding(30.dp)
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = stringResource(R.string.list_title),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 20.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        )
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(LightPurple)
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        Row {
                            Text(
                                text = stringResource(id = R.string.players),
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily.SansSerif
                                )
                            )
                            Spacer(modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth())
                            Text(
                                text = stringResource(id = R.string.goals),
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily.SansSerif
                                )
                            )
                        }
                    }
                }
                items(state.data) { item ->
                    PlayerCard(
                        playerItem = item,
                        onPlayerClick = onPlayerClick
                    )
                    Spacer(
                        modifier = Modifier
                            .height(2.dp)
                            .fillMaxWidth()
                            .background(GraySpacerBackgroundColor)
                    )
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                            .background(GraySpacerBackgroundColor)
                            .padding(5.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.list_bottom_header),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                fontSize = 14.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        )
                    }
                }
            }
        }

    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    SportZoneTheme {
        HomeScreen(
            state = HomeScreenState(),
            onPlayerClick = {}
        )
    }
}