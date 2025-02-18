package ru.noxis.jetlaggedapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.noxis.jetlaggedapp.state.JetLaggedHomeScreenState

class JetLaggedHomeScreenViewModel: ViewModel() {

    val uiState: StateFlow<JetLaggedHomeScreenState> = MutableStateFlow(JetLaggedHomeScreenState())
}