package com.example.currencyconverter

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.edittext)
        val textView = findViewById<TextView>(R.id.textview)
        val spinner1 = findViewById<Spinner>(R.id.spinner1)
        val spinner2 = findViewById<Spinner>(R.id.spinner2)
        val btnGo = findViewById<Button>(R.id.btnGo)

        val currencies = arrayOf("USD", "EUR", "VND", "JPY")
        val exchangeRates = mapOf(
            "USD" to mapOf("EUR" to 0.85, "VND" to 24000.0, "JPY" to 110.0, "USD" to 1.0),
            "EUR" to mapOf("USD" to 1.18, "VND" to 28000.0, "JPY" to 130.0, "EUR" to 1.0),
            "VND" to mapOf("USD" to 0.000042, "EUR" to 0.000036, "JPY" to 0.005, "VND" to 1.0),
            "JPY" to mapOf("USD" to 0.0091, "EUR" to 0.0077, "VND" to 200.0, "JPY" to 1.0)
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = adapter
        spinner2.adapter = adapter

        btnGo.setOnClickListener {
            val amount = editText.text.toString().toDoubleOrNull()
            if (amount == null) {
                Toast.makeText(this, "Vui lòng nhập số tiền hợp lệ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val fromCurrency = spinner1.selectedItem.toString()
            val toCurrency = spinner2.selectedItem.toString()
            val rate = exchangeRates[fromCurrency]?.get(toCurrency) ?: 1.0
            val convertedAmount = amount * rate
            textView.text = "%.2f".format(convertedAmount)
        }
    }
}