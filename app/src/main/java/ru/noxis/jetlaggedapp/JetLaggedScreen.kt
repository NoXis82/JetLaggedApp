package ru.noxis.jetlaggedapp

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.noxis.jetlaggedapp.viewmodel.JetLaggedHomeScreenViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.noxis.jetlaggedapp.heartrate.HeartRateCard

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun JetLaggedScreen(
    modifier: Modifier = Modifier,
    windowSizeClass: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
    viewModel: JetLaggedHomeScreenViewModel = viewModel(),
    onDrawerClicked: () -> Unit = {}
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
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