package ru.noxis.jetlaggedapp.heartrate

import android.graphics.PointF
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import ru.noxis.jetlaggedapp.data.HeartRateData
import ru.noxis.jetlaggedapp.ui.theme.JetLaggedAppTheme
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import ru.noxis.jetlaggedapp.data.bracketInSeconds
import ru.noxis.jetlaggedapp.data.heartRateGraphData
import ru.noxis.jetlaggedapp.data.numberEntries
import kotlin.math.roundToInt

@Composable
fun HeartRateGraph(listData: List<HeartRateData>) {
    Box(Modifier.size(width = 400.dp, height = 100.dp)) {
        Graph(
            listData = listData,
            modifier = Modifier.padding(16.dp)
        )
    }
}

sealed class DataPoint {
    object NoMeasurement : DataPoint()
    data class Measurement(
        val averageMeasurementTime: Int,
        val minHeartRate: Int,
        val maxHeartRate: Int,
        val averageHeartRate: Int,
    ) : DataPoint()
}

@Composable
private fun Graph(
    listData: List<HeartRateData>,
    modifier: Modifier = Modifier,
    waveLineColors: List<Color> = JetLaggedAppTheme.extraColors.heartWave,
    pathBackground: Color = JetLaggedAppTheme.extraColors.heartWaveBackground,
) {
    if (waveLineColors.size < 2) {
        throw IllegalArgumentException("waveLineColors requires 2+ colors; $waveLineColors")
    }
    Box(
        modifier
            .fillMaxSize()
            .drawWithCache {
                val paths = generateSmoothPath(listData, size)
                val lineBrush = Brush.verticalGradient(waveLineColors)
                onDrawBehind {
                    drawPath(
                        paths.second,
                        pathBackground,
                        style = Fill
                    )
                    drawPath(
                        paths.first,
                        lineBrush,
                        style = Stroke(2.dp.toPx())
                    )
                }
            }
    )
}

fun generateSmoothPath(data: List<HeartRateData>, size: Size): Pair<Path, Path> {
    val path = Path()
    val variancePath = Path()

    val totalSeconds = 60 * 60 * 24 // total seconds in a day
    val widthPerSecond = size.width / totalSeconds
    val maxValue = data.maxBy { it.amount }.amount
    val minValue = data.minBy { it.amount }.amount
    val graphTop = ((maxValue + 5) / 10f).roundToInt() * 10
    val graphBottom = (minValue / 10f).toInt() * 10
    val range = graphTop - graphBottom
    val heightPxPerAmount = size.height / range.toFloat()

    var previousX = 0f
    var previousY = size.height
    var previousMaxX = 0f
    var previousMaxY = size.height

    val groupedMeasurements = (0..numberEntries).map { bracketStart ->
        heartRateGraphData.filter {
            (bracketStart * bracketInSeconds..(bracketStart + 1) * bracketInSeconds)
                .contains(it.date.toSecondOfDay())
        }
    }.map { heartRates ->
        if (heartRates.isEmpty()) DataPoint.NoMeasurement else
            DataPoint.Measurement(
                averageMeasurementTime = heartRates.map { it.date.toSecondOfDay() }.average()
                    .roundToInt(),
                minHeartRate = heartRates.minBy { it.amount }.amount,
                maxHeartRate = heartRates.maxBy { it.amount }.amount,
                averageHeartRate = heartRates.map { it.amount }.average().roundToInt()
            )
    }

    groupedMeasurements.forEachIndexed { i, dataPoint ->
        if (i == 0 && dataPoint is DataPoint.Measurement) {
            path.moveTo(
                0f,
                size.height - (dataPoint.averageHeartRate - graphBottom).toFloat() *
                        heightPxPerAmount
            )
            variancePath.moveTo(
                0f,
                size.height - (dataPoint.maxHeartRate - graphBottom).toFloat() *
                        heightPxPerAmount
            )
        }

        if (dataPoint is DataPoint.Measurement) {
            val x = dataPoint.averageMeasurementTime * widthPerSecond
            val y = size.height - (dataPoint.averageHeartRate - graphBottom).toFloat() *
                    heightPxPerAmount

            // to do smooth curve graph - we use cubicTo, uncomment section below for non-curve
            val controlPoint1 = PointF((x + previousX) / 2f, previousY)
            val controlPoint2 = PointF((x + previousX) / 2f, y)
            path.cubicTo(
                controlPoint1.x, controlPoint1.y, controlPoint2.x, controlPoint2.y,
                x, y
            )
            previousX = x
            previousY = y

            val maxX = dataPoint.averageMeasurementTime * widthPerSecond
            val maxY = size.height - (dataPoint.maxHeartRate - graphBottom).toFloat() *
                    heightPxPerAmount
            val maxControlPoint1 = PointF((maxX + previousMaxX) / 2f, previousMaxY)
            val maxControlPoint2 = PointF((maxX + previousMaxX) / 2f, maxY)
            variancePath.cubicTo(
                maxControlPoint1.x, maxControlPoint1.y, maxControlPoint2.x, maxControlPoint2.y,
                maxX, maxY
            )

            previousMaxX = maxX
            previousMaxY = maxY
        }
    }

    var previousMinX = size.width
    var previousMinY = size.height

    groupedMeasurements.reversed().forEachIndexed { index, dataPoint ->

        val i = 47 - index
        if (i == 47 && dataPoint is DataPoint.Measurement) {
            variancePath.moveTo(
                size.width,
                size.height - (dataPoint.minHeartRate - graphBottom).toFloat() *
                        heightPxPerAmount
            )
        }

        if (dataPoint is DataPoint.Measurement) {
            val minX = dataPoint.averageMeasurementTime * widthPerSecond
            val minY = size.height - (dataPoint.minHeartRate - graphBottom).toFloat() *
                    heightPxPerAmount
            val minControlPoint1 = PointF((minX + previousMinX) / 2f, previousMinY)
            val minControlPoint2 = PointF((minX + previousMinX) / 2f, minY)
            variancePath.cubicTo(
                minControlPoint1.x, minControlPoint1.y, minControlPoint2.x, minControlPoint2.y,
                minX, minY
            )

            previousMinX = minX
            previousMinY = minY
        }

    }

    return path to variancePath
}