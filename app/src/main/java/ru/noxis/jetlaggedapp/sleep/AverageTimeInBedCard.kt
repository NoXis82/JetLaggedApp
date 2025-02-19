package ru.noxis.jetlaggedapp.sleep

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.noxis.jetlaggedapp.R
import ru.noxis.jetlaggedapp.components.BasicInformationalCard
import ru.noxis.jetlaggedapp.ui.theme.HeadingStyle
import ru.noxis.jetlaggedapp.ui.theme.JetLaggedAppTheme
import ru.noxis.jetlaggedapp.ui.theme.SmallHeadingStyle

@Preview
@Preview(widthDp = 500, name = "larger screen")
@Composable
fun AverageTimeInBedCard(modifier: Modifier = Modifier) {
    TwoLineInfoCard(
        borderColor = JetLaggedAppTheme.extraColors.bed,
        firstLineText = stringResource(R.string.ave_time_in_bed_heading),
        secondLineText = "8h42min",
        icon = Icons.Default.Warning,
        modifier = modifier
            .wrapContentWidth()
            .heightIn(min = 156.dp)
    )
}

@Preview
@Preview(widthDp = 500, name = "larger screen")
@Composable
fun AverageTimeAsleepCard(modifier: Modifier = Modifier) {
    TwoLineInfoCard(
        borderColor = JetLaggedAppTheme.extraColors.sleep,
        firstLineText = stringResource(R.string.ave_time_sleep_heading),
        secondLineText = "7h42min",
        icon = Icons.Default.Warning,
        modifier = modifier
            .wrapContentWidth()
            .heightIn(min = 156.dp)
    )
}

@Composable
fun TwoLineInfoCard(
    borderColor: Color,
    firstLineText: String,
    secondLineText: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    BasicInformationalCard(
        borderColor = borderColor,
        modifier = modifier.size(200.dp)
    ) {
//        BubbleBackground(
//            modifier = Modifier.fillMaxSize(),
//            numberBubbles = 3, bubbleColor = borderColor.copy(0.25f)
//        )
        BoxWithConstraints(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
        ) {
            if (maxWidth > 400.dp) {
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(CenterStart)
                ) {
                    Icon(
                        icon, contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .align(CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier
                            .align(CenterVertically)
                            .wrapContentSize()
                    ) {
                        Text(
                            firstLineText,
                            style = SmallHeadingStyle
                        )
                        Text(
                            secondLineText,
                            style = HeadingStyle,
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Center)
                ) {
                    Icon(
                        icon, contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .align(CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Column(modifier = Modifier.align(CenterHorizontally)) {
                        Text(
                            firstLineText,
                            style = SmallHeadingStyle,
                            modifier = Modifier.align(CenterHorizontally)
                        )
                        Text(
                            secondLineText,
                            style = HeadingStyle,
                            modifier = Modifier.align(CenterHorizontally)
                        )
                    }
                }
            }
        }
    }
}