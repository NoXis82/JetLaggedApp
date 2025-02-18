package ru.noxis.jetlaggedapp.heartrate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.noxis.jetlaggedapp.R
import ru.noxis.jetlaggedapp.components.BasicInformationalCard
import ru.noxis.jetlaggedapp.components.HomeScreenCardHeading
import ru.noxis.jetlaggedapp.state.HeartRateOverallData
import ru.noxis.jetlaggedapp.ui.theme.JetLaggedAppTheme
import ru.noxis.jetlaggedapp.ui.theme.SmallHeadingStyle
import ru.noxis.jetlaggedapp.ui.theme.TitleStyle

@Preview
@Composable
fun HeartRateCard(
    modifier: Modifier = Modifier,
    heartRateData: HeartRateOverallData = HeartRateOverallData()
) {

    BasicInformationalCard(
        borderColor = JetLaggedAppTheme.extraColors.heart,
        modifier = modifier.height(260.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            HomeScreenCardHeading(text = stringResource(R.string.heart_rate_heading))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    heartRateData.averageBpm.toString(),
                    style = TitleStyle,
                    modifier = Modifier.alignByBaseline(),
                    textAlign = TextAlign.Center
                )
                Text(
                    "bpm",
                    modifier = Modifier.alignByBaseline(),
                    style = SmallHeadingStyle
                )
            }
            HeartRateGraph(heartRateData.listData)
        }
    }
}
