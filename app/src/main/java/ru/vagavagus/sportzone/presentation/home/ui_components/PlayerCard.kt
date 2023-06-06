package ru.vagavagus.sportzone.presentation.home.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import ru.vagavagus.sportzone.common.Constants.BASE_IMAGE_URL
import ru.vagavagus.sportzone.common.Constants.BASE_URL
import ru.vagavagus.sportzone.domain.model.PlayerItem
import ru.vagavagus.sportzone.ui.theme.GoalsTextColor
import ru.vagavagus.sportzone.ui.theme.SportZoneTheme
import ru.vagavagus.sportzone.ui.theme.White50

@Composable
fun PlayerCard(
    playerItem: PlayerItem,
    onPlayerClick: (playerId: Long) -> Unit
) {
    Row(
        modifier = Modifier
            .background(White50)
            .clickable { onPlayerClick(playerItem.id) }
            .padding(horizontal = 12.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier.size(30.dp),
            model = "${BASE_IMAGE_URL}/${playerItem.countryImageUrl}",
            contentDescription = null,
            loading = { CircularProgressIndicator() }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = playerItem.name,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
        Spacer(modifier = Modifier
            .weight(1f)
            .fillMaxWidth())
        Text(
            text = playerItem.goals.toString(),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = GoalsTextColor
            )
        )
    }
}

@Preview
@Composable
private fun PlayerCardPreview() {
    SportZoneTheme {
        PlayerCard(
            playerItem = PlayerItem(1, "", "Erling Haaland", 25),
            onPlayerClick = {}
        )
    }
}