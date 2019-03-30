package com.alex323glo.kpi.lab3

import com.alex323glo.kpi.lab3.Config.N
import com.alex323glo.kpi.lab3.Config.n
import com.alex323glo.kpi.lab3.Config.w
import com.alex323glo.kpi.lab3.DiscreteFourierTransformer.transform
import com.alex323glo.kpi.lab3.Generator.generateFullSignal
import com.alex323glo.kpi.lab3.Saver.saveMetaResults
import com.alex323glo.kpi.lab3.Saver.saveVectorWithXAxis
import com.alex323glo.kpi.lab3.Stopwatch.checkMillis
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {
    var x = emptyArray<Double>().toDoubleArray()
    var y = emptyArray<Double>().toDoubleArray()

    val timeSpentMillis = checkMillis {
        x = generateFullSignal(n, N, w)
        y = transform(x, N)
    }

    val xAxis = (0 until N)
            .map(Int::toDouble)
            .toDoubleArray()

    val nowString = LocalDateTime
            .now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS"))
    val pathToRoot = "results/lab3/$nowString/"

    val pathToMetaResults = saveMetaResults(timeSpentMillis, "${pathToRoot}meta_results.csv")
    val pathToX = saveVectorWithXAxis(xAxis, x, "${pathToRoot}x.csv")
    val pathToY = saveVectorWithXAxis(xAxis, y, "${pathToRoot}y.csv")

    println("""
        Results were successfully saved to CSV!

        X:              $pathToX
        Y:              $pathToY
        Meta Results:   $pathToMetaResults
    """.trimIndent())
}