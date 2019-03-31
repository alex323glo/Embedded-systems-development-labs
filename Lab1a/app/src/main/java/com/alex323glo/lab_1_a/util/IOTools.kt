package com.alex323glo.lab_1_a.util

import android.widget.EditText

fun EditText.getDouble(): Double = this.text
    .toString()
    .toDouble()

fun formatElapsedTime(timeSpentMillis: Long): String =
    if (timeSpentMillis < 1) {
        "< 0 (ms)"
    } else {
        "$timeSpentMillis (ms)"
    }