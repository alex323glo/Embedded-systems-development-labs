package com.alex323glo.kpi.lab2

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

object Stopwatch {
    fun checkMillis(action: () -> Unit): Long {
        val start = LocalDateTime.now()
        action()
        val end = LocalDateTime.now()
        return ChronoUnit.MILLIS.between(start, end)
    }
}