package com.stark.monthandyearpicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

internal class MainActivity : AppCompatActivity() {
    private lateinit var btn : Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn=findViewById(R.id.btn)
        btn.setOnClickListener { showMonthAndYearPicker() }
        showMonthAndYearPicker()
      }

    fun showMonthAndYearPicker() =  MonthYearPickerDialog().apply {
        setListener { view, year, month, dayOfMonth ->
            Toast.makeText(requireContext(), "Set date: $year/$month/$dayOfMonth", Toast.LENGTH_LONG).show()
        }
        show(supportFragmentManager, "MonthYearPickerDialog")

    }
}
