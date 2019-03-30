package com.alex323glo.kpi.lab1

import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sin
import kotlin.random.Random

object Generator {
    fun createHVectors(resultSize: Int, w: Int, xVector: DoubleArray): Array<DoubleArray> =
            Array(resultSize) { x ->
                generateSignal(w.toDouble() * (x + 1) / resultSize, xVector)
            }

    fun combineHVectors(hVectors: Array<DoubleArray>): DoubleArray {
        val result = ArrayList<Double>()
        for (j in 0 until hVectors.first().size) {
            var sum = 0.0
            for (i in 0 until hVectors.size) {
                sum += hVectors[i][j]
            }
            result.add(sum)
        }
        return result.toDoubleArray()
    }

    fun countMX(hVector: DoubleArray): Double = hVector.sum() / hVector.size

    fun countDX(xVector: DoubleArray, mx: Double) = xVector
            .map { x ->
                (x - mx).pow(2)
            }
            .let { vector ->
                vector.sum() / vector.size
            }

    fun generateX(size: Int, step: Double): DoubleArray =
            DoubleArray(size) { x ->
                x * step
            }

    private fun generateSignal(w: Double, xVector: DoubleArray): DoubleArray {
        val a: Double = getRandomA()
        val phi: Double = getRandomPhi()
        return DoubleArray(xVector.size) { x ->
            a * sin(w * x + phi)
        }
    }

    private fun getRandomA(): Double = Random.nextDouble(0.0, 2.0) + 2.0

    private fun getRandomPhi(): Double = Random.nextDouble(0.0, 2.0 * PI)
}