package ru.noxis.jetlaggedapp.state

import ru.noxis.jetlaggedapp.data.HeartRateData
import ru.noxis.jetlaggedapp.data.heartRateGraphData


data class JetLaggedHomeScreenState(
   // val sleepGraphData: SleepGraphData = sleepData,
   // val wellnessData: WellnessData = WellnessData(10, 4, 5),
    val heartRateData: HeartRateOverallData = HeartRateOverallData()
)

data class HeartRateOverallData(
    val averageBpm: Int = 65,
    val listData: List<HeartRateData> = heartRateGraphData
)