package com.alex323glo.kpi.lab3

import kotlin.math.PI
import kotlin.math.sin
import kotlin.random.Random

object Generator {

    fun generateFullSignal(n: Int, N: Int, w: Int): DoubleArray =
            generateHVectors(n, N, w)
                    .let(this::combineHVectors)

    private fun generateHVectors(n: Int, N: Int, w: Int): Array<DoubleArray> =
            Array(n) { x ->
                generateSignal(N, w * (x + 1) / n)
            }

    private fun combineHVectors(hVectors: Array<DoubleArray>): DoubleArray {
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

    private fun generateSignal(n: Int, w: Int): DoubleArray {
        val a = generateRandomA()
        val phi = generateRandomPhi()
        return DoubleArray(n) { x ->
            a * sin(w * x + phi)
        }
    }

    private fun generateRandomA(): Double = Random.nextDouble(0.0, 2.0) + 2.0

    private fun generateRandomPhi(): Double = Random.nextDouble(0.0, 2.0 * PI)

}