package com.alex323glo.kpi.lab1

import com.alex323glo.kpi.lab1.Config.N
import com.alex323glo.kpi.lab1.Config.n
import com.alex323glo.kpi.lab1.Config.w
import com.alex323glo.kpi.lab1.Generator.combineHVectors
import com.alex323glo.kpi.lab1.Generator.countDX
import com.alex323glo.kpi.lab1.Generator.countMX
import com.alex323glo.kpi.lab1.Generator.createHVectors
import com.alex323glo.kpi.lab1.Generator.generateX
import com.alex323glo.kpi.lab1.Saver.saveCombinedHVectors
import com.alex323glo.kpi.lab1.Saver.saveHVectors
import com.alex323glo.kpi.lab1.Saver.saveMetaResults
import com.alex323glo.kpi.lab1.Stopwatch.checkMillis
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {
    val step: Double = 1 / N.toDouble()

    val x = generateX(N, step)

    var hVectors: Array<DoubleArray> = emptyArray()
    var combinedHVectors: DoubleArray = emptyArray<Double>().toDoubleArray()
    var mx: Double = 0.0
    var dx: Double = 0.0

    val timeSpentMillis = checkMillis {
        hVectors = createHVectors(n, w, x)
        combinedHVectors = combineHVectors(hVectors)
        mx = countMX(combinedHVectors)
        dx = countDX(combinedHVectors, mx)
    }

    val nowString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS"))
    val rootPath = "results/lab1/$nowString/"

    val pathToMetaResults = saveMetaResults(mx, dx, timeSpentMillis, "${rootPath}meta_results.csv")
    val pathToHVectors = saveHVectors(x, hVectors, "${rootPath}h_vectors.csv")
    val pathToCombinedHVectors = saveCombinedHVectors(x, combinedHVectors, "${rootPath}combined_h_vectors.csv")

    println("""
        MX = $mx
        DX = $dx
        ------------------------${Array(pathToCombinedHVectors.length) {"-"}.joinToString(separator = "")}
        Results were successfully saved!

        Meta results:           $pathToMetaResults
        H Vectors:              $pathToHVectors
        Combined H Vectors:     $pathToCombinedHVectors
    """.trimIndent())
}
