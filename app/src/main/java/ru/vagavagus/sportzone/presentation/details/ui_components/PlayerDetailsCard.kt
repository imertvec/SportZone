package ru.vagavagus.sportzone.presentation.details.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import ru.vagavagus.sportzone.R
import ru.vagavagus.sportzone.domain.model.PlayerDetailsItem
import ru.vagavagus.sportzone.domain.model.Team
import ru.vagavagus.sportzone.ui.theme.MainBackground
import ru.vagavagus.sportzone.ui.theme.SportZoneTheme

@Composable
fun PlayerDetailsCard(
    details: PlayerDetailsItem,
    onBackPressed: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackground)
            .padding(10.dp)
    ) {
        Column {
            Row {
                Icon(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable { onBackPressed() }
                        .padding(5.dp),
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                SubcomposeAsyncImage(
                    modifier = Modifier.size(120.dp, 160.dp),
                    model = details.imageUrl,
                    contentDescription = null,
                    loading = { CircularProgressIndicator(
                        color = Color.White
                    ) }
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(text = "${stringResource(R.string.name)}: ${details.name}")
                    Text(text = "${stringResource(R.string.nationality)}: ${details.nationality}")
                    details.height?.let { Text(text = "${stringResource(R.string.height)}: ${details.height}") }
                    details.weight?.let { Text(text = "${stringResource(R.string.weight)}: ${details.weight}") }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "${stringResource(id = R.string.team)}: ${details.team.name}")
                        SubcomposeAsyncImage(
                            modifier = Modifier.size(30.dp),
                            model = details.team.imageUrl,
                            contentDescription = null,
                            loading = { CircularProgressIndicator(
                                color = Color.White
                            ) }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))

            details.bornOn?.let { Text(text = "${stringResource(R.string.born_on)}: ${details.bornOn}") }
            details.numsOfInternationalCaps?.let { Text(text = "${stringResource(R.string.number_international_caps)}: ${details.numsOfInternationalCaps}") }
            details.firstCap?.let { Text(text = "${stringResource(R.string.first_cap)}: ${details.firstCap}") }
            details.firstInternationalGoal?.let { Text(text = "${stringResource(R.string.first_i_goal)}: ${details.firstInternationalGoal}") }
            details.bestFoot?.let { Text(text = "${stringResource(R.string.best_foot)}: ${details.bestFoot}") }
            Spacer(modifier = Modifier.height(10.dp))
            Divider()
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                details.impactGoals?.let {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = stringResource(id = R.string.impact_goals))
                        Text(text = "${details.impactGoals}%")
                    }
                }
                details.impactAssist?.let {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = stringResource(id = R.string.impact_assist))
                        Text(text = "${details.impactAssist}%")
                    }
                }
            }
            Divider()
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                details.playedMatches?.let {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = stringResource(id = R.string.matches))
                        Text(text = "${details.playedMatches}")
                    }
                }
                details.minutesPlayed?.let {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = stringResource(id = R.string.played))
                        Text(text = "${details.minutesPlayed}min")
                    }
                }
            }
            Divider()
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                details.goals?.let {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = stringResource(id = R.string.goals))
                        Text(text = "${details.goals}")
                    }
                }
            }
            Divider()
        }
    }
}

@Preview
@Composable
private fun PlayerDetailsCardPreview() {
    SportZoneTheme {
        PlayerDetailsCard(
            details = PlayerDetailsItem(
                id = 1,
                team = Team(
                    name = "Manchester City",
                    imageUrl = "https://www.footballdatabase.eu/images/photos/resampled/clubs/50/a_0/464.webp"
                ),
                bornOn = "July 21, 2000 (22 years) at Leeds",
                nationality = "Norway",
                numsOfInternationalCaps = "23 (21 goals)",
                height = "1m94",
                weight = "87 kg",
                firstCap = "vs Malta 05/09/2019",
                bestFoot = "Left foot",
                firstInternationalGoal = "vs Austria 04/09/2020",
                name = "Erling Haaland",
                imageUrl = "https://www.footballdatabase.eu/images/photos/players/a_294/294600.jpg",
                impactGoals = 40,
                impactAssist = 33,
                goals = 53,
                playedMatches = 53,
                minutesPlayed = 4113
            ),
            onBackPressed = {  }
        )
    }
}