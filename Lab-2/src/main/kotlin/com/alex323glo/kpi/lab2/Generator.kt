package com.alex323glo.kpi.lab2

import kotlin.math.PI
import kotlin.math.sin
import kotlin.random.Random

object Generator {

    fun countMX(hVector: DoubleArray): Double = hVector.sum() / hVector.size

    fun generateHVectors(n: Int, N: Int, w: Int): Array<DoubleArray> =
            Array(n) { x ->
                generateSignal(N, w.toDouble() * (x + 1) / n)
            }

    fun combineHVectors(hVectors: Array<DoubleArray>): DoubleArray {
        val result = mutableListOf<Double>()
        for (j in 0 until hVectors.first().size) {
            var sum = 0.0
            for (i in 0 until hVectors.size) {
                sum += hVectors[i][j]
            }
            result += sum
        }
        return result.toDoubleArray()
    }

    fun generateFullSignal(n: Int, N: Int, w: Int): DoubleArray {
        val hVectors = generateHVectors(n, N, w)
        return combineHVectors(hVectors)
    }

    fun countCorrelation(xVector: DoubleArray, yVector: DoubleArray, tau: Int): Double {
        val mx = countMX(xVector)
        val my = countMX(yVector)
        val partialResult = DoubleArray(xVector.size) { i ->
            countPartialCorrelation(xVector[i], mx, yVector[i + tau], my)
        }
        return partialResult.sum() / (xVector.size - 1)
    }

    fun countRXY(xVector: DoubleArray, yVector: DoubleArray, N: Int): DoubleArray =
            DoubleArray(N / 2) { tau ->
                countCorrelation(xVector.copyOfRange(0, N / 2), yVector, tau)
            }

    private fun countPartialCorrelation(x: Double, mx: Double, y: Double, my: Double) =
            (x - mx) * (y - my)

    private fun generateSignal(n: Int, w: Double): DoubleArray {
        val a = generateRandomA()
        val phi = generateRandomPhi()
        return DoubleArray(n) { x ->
            a * sin(w * x + phi)
        }
    }

    private fun generateRandomA(): Double = Random.nextDouble(0.0, 2.0) + 2.0

    private fun generateRandomPhi(): Double = Random.nextDouble(0.0, 2.0 * PI)

}