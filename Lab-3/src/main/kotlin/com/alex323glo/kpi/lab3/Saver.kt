package com.alex323glo.kpi.lab3

import java.io.File
import java.io.PrintStream

object Saver {
    fun saveMetaResults(timeSpentMillis: Long, filePath: String): String {
        val preparedVectors: Array<Array<String>> = arrayOf(
                arrayOf("Time_Spent"),
                arrayOf(timeSpentMillis.toString())
        )
        return writeToCSV(preparedVectors, filePath)
    }

    fun saveVectorWithXAxis(xAxis: DoubleArray, vector: DoubleArray, filePath: String): String =
            saveVectorsWithXAxis(xAxis, arrayOf(vector), filePath)

    private fun saveVectorsWithXAxis(xAxis: DoubleArray, vectors: Array<DoubleArray>, filePath: String): String {
        val resultVectors: Array<Array<Double>> = arrayOf(xAxis, *vectors)
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