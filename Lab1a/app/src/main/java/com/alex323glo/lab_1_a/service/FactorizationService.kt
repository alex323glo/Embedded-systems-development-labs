package com.alex323glo.lab_1_a.service

import com.alex323glo.lab_1_a.data.FactorizationResult
import com.alex323glo.lab_1_a.exception.FactorizationException
import kotlin.math.pow

object FactorizationService {

    fun countAB(number: Double): FactorizationResult {
        val startTime = System.currentTimeMillis()
        var a = number + 1
        var b = 3.0
        var count = 0.0
        while (count < 100) {
            while (b < a) {
                if ((a.pow(2) - number.pow(2)) == b.pow(2)) {
                    val endTime = System.currentTimeMillis()
                    return FactorizationResult(a, b, endTime - startTime)
                } else {
                    b++
                }
            }
            a++
            b = 3.0
            count++
        }
        throw FactorizationException("Can't count!")
    }

}