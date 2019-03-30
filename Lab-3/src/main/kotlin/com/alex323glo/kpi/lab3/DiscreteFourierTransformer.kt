package com.alex323glo.kpi.lab3

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.pow

object DiscreteFourierTransformer {

    fun transform(xVector: DoubleArray, N: Int): DoubleArray = (0 until N).let { range ->
        range.map { p ->
            var real = 0.0
            var unreal = 0.0
            range.map { k ->
                val temp = 2 * PI * p * k / N
                real += xVector[k] * cos(temp)
                unreal += xVector[k] * sin(temp)
                        Pair(real, unreal)
            }.let {
                sqrt(real.pow(2) + unreal.pow(2))
            }
        }
    }.toDoubleArray()

}