package com.alex323glo.lab_1_a

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import com.alex323glo.lab_1_a.service.FactorizationService.countAB
import com.alex323glo.lab_1_a.exception.FactorizationException
import com.alex323glo.lab_1_a.util.formatElapsedTime
import com.alex323glo.lab_1_a.util.getDouble

class MainActivity : AppCompatActivity() {

    private lateinit var numberInput: EditText
    private lateinit var aOutput: EditText
    private lateinit var bOutput: EditText
    private lateinit var resultTimeOutput: TextView
    private lateinit var goButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initElements()

        goButton.setOnClickListener(this::onGoButtonClicked)
    }

    private fun initElements() {
        numberInput = findViewById(R.id.number_text_input)
        aOutput = findViewById(R.id.a_text_output)
        bOutput = findViewById(R.id.b_text_output)
        resultTimeOutput = findViewById(R.id.result_time_output)
        goButton = findViewById(R.id.go_button)
    }

    private fun onGoButtonClicked(view: View) {
        if (numberInput.text == null || numberInput.text.toString().isBlank()) {
            makeText(this, "Please, input Number!", LENGTH_LONG)
                .show()
            return
        }

        val number = numberInput.getDouble()
        if (number < 4) {
            makeText(this, "Please, input number > 3 !", LENGTH_LONG)
                .show()
            return
        }

        val (a, b, timeSpentMillis) = try {
            countAB(number)
        } catch (e: FactorizationException) {
            makeText(this, "Cannot count a, b!", LENGTH_LONG)
                .show()
            return
        }

        aOutput.setText(a.toString())
        bOutput.setText(b.toString())
        resultTimeOutput.text = formatElapsedTime(timeSpentMillis)
    }
}

