package ru.noxis.jetlaggedapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.noxis.jetlaggedapp.viewmodel.JetLaggedHomeScreenViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.noxis.jetlaggedapp.backgrounds.movingStripesBackground
import ru.noxis.jetlaggedapp.heartrate.HeartRateCard
import ru.noxis.jetlaggedapp.sleep.AverageTimeAsleepCard
import ru.noxis.jetlaggedapp.sleep.AverageTimeInBedCard
import ru.noxis.jetlaggedapp.sleep.JetLaggedHeader
import ru.noxis.jetlaggedapp.sleep.JetLaggedSleepGraphCard
import ru.noxis.jetlaggedapp.ui.theme.JetLaggedAppTheme
import ru.noxis.jetlaggedapp.util.MultiDevicePreview

@OptIn(ExperimentalLayoutApi::class)
@Composable
@MultiDevicePreview
fun JetLaggedScreen(
    modifier: Modifier = Modifier,
    windowSizeClass: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
    viewModel: JetLaggedHomeScreenViewModel = viewModel(),
    onDrawerClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.movingStripesBackground(
                stripeColor = JetLaggedAppTheme.extraColors.header,
                backgroundColor = MaterialTheme.colorScheme.background,
            )
        ) {
            JetLaggedHeader(
                modifier = Modifier.fillMaxWidth(),
                onDrawerClicked = onDrawerClicked
            )
        }

        val uiState = viewModel.uiState.collectAsStateWithLifecycle()
        val insets = WindowInsets.safeDrawing.only(
            WindowInsetsSides.Bottom + WindowInsetsSides.Horizontal
        )
        FlowRow(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(insets),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Center,
            maxItemsInEachRow = 3
        ) {


            if (windowSizeClass == WindowWidthSizeClass.Compact) {
                AverageTimeInBedCard()
                AverageTimeAsleepCard()
            } else {
                FlowColumn {
                    AverageTimeInBedCard()
                    AverageTimeAsleepCard()
                }
            }

            JetLaggedSleepGraphCard(uiState.value.sleepGraphData, Modifier.widthIn(max = 600.dp))

            if (windowSizeClass == WindowWidthSizeClass.Compact) {
                HeartRateCard(
                    modifier = modifier.widthIn(max = 400.dp, min = 200.dp),
                    uiState.value.heartRateData
                )
            } else {
                FlowColumn {
                    HeartRateCard(
                        modifier = modifier.widthIn(max = 400.dp, min = 200.dp),
                        uiState.value.heartRateData
                    )
                }
            }
        }

    }

}