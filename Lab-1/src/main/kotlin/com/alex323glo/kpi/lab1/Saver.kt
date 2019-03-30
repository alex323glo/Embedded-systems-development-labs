package com.alex323glo.kpi.lab1

import java.io.File
import java.io.PrintStream

object Saver {
    fun saveMetaResults(mx: Double, dx: Double, timeSpentMillis: Long, filePath: String): String {
        val preparedVectors: Array<Array<String>> = arrayOf(
                arrayOf("MX", "DX", "Time_Spent"),
                arrayOf(mx.toString(), dx.toString(), timeSpentMillis.toString())
        )
        return writeToCSV(preparedVectors, filePath)
    }

    fun saveCombinedHVectors(x: DoubleArray, combinedHVectors: DoubleArray, filePath: String): String =
            saveHVectors(x, arrayOf(combinedHVectors), filePath)

    fun saveHVectors(x: DoubleArray, hVectors: Array<DoubleArray>, filePath: String): String {
        val resultVectors: Array<Array<Double>> = arrayOf(x, *hVectors)
                .map { array -> array.toTypedArray() }
                .toTypedArray()
        return writeToCSV(resultVectors, filePath)
    }

    private fun <T> writeToCSV(vectors: Array<Array<T>>, filePath: String): String {
        val file = File(filePath)
        file.parentFile.mkdirs()

        PrintStream(filePath).use { printer ->
            vectors.forEach { vector ->
                printer.println(vector.toCSVRaw())
            }
        }

        return file.absolutePath
    }


    private fun <T> Array<T>.toCSVRaw(): String =
            this.joinToString(separator = ",") { t ->
                t.toString()
            }

}