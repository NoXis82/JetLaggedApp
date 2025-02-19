package ru.noxis.jetlaggedapp.data

import ru.noxis.jetlaggedapp.R
import java.time.Duration
import java.time.LocalDateTime

data class SleepPeriod(
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val type: SleepType,
) {
    val duration: Duration by lazy {
        Duration.between(startTime, endTime)
    }
}

enum class SleepType(val title: Int) {
    Awake(R.string.sleep_type_awake),
    REM(R.string.sleep_type_rem),
    Light(R.string.sleep_type_light),
    Deep(R.string.sleep_type_deep)
}