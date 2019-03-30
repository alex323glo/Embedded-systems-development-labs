package com.alex323glo.kpi.lab2

import com.alex323glo.kpi.lab2.Config.N
import com.alex323glo.kpi.lab2.Config.n
import com.alex323glo.kpi.lab2.Config.w
import com.alex323glo.kpi.lab2.Generator.countRXY
import com.alex323glo.kpi.lab2.Generator.generateFullSignal
import com.alex323glo.kpi.lab2.Saver.saveMetaResults
import com.alex323glo.kpi.lab2.Saver.saveVectorWithXAxis
import com.alex323glo.kpi.lab2.Stopwatch.checkMillis
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {

    var x = emptyArray<Double>().toDoubleArray()
    var y = emptyArray<Double>().toDoubleArray()
    var rxx = emptyArray<Double>().toDoubleArray()
    var ryy = emptyArray<Double>().toDoubleArray()
    var rxy = emptyArray<Double>().toDoubleArray()

    val timeSpentMillis = checkMillis {
        x = generateFullSignal(n, N, w)
        y = generateFullSignal(n, N, w)
        rxx = countRXY(x, x, N)
        ryy = countRXY(y, y, N)
        rxy = countRXY(x, y, N)
    }

    val xAxis = (0 until (N / 2))
            .map(Int::toDouble)
            .toDoubleArray()

    val nowString = LocalDateTime
            .now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS"))

    val pathToRoot = "results/lab2/$nowString/"

    val pathToMetaResults = saveMetaResults(timeSpentMillis, "${pathToRoot}meta_results.csv")
    val pathToRXX = saveVectorWithXAxis(xAxis, rxx, "${pathToRoot}rxx.csv")
    val pathToRYY = saveVectorWithXAxis(xAxis, ryy, "${pathToRoot}ryy.csv")
    val pathToRXY = saveVectorWithXAxis(xAxis, rxy, "${pathToRoot}rxy.csv")

    println("""
        Results were successfully saved to CSV!
        RXX:            $pathToRXX
        RYY:            $pathToRYY
        RXY:            $pathToRXY
        Meta Results:   $pathToMetaResults
    """.trimIndent())
}

