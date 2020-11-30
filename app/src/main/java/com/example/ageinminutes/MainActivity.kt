package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener { view ->
            clickDataPicker(view)
        }


    }

    fun clickDataPicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedyear, selectedmonth, selecteddayOfMonth ->

                val selectedDate = "$selecteddayOfMonth/${selectedmonth + 1}/$selectedyear"
                tvSelectedDate.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)

                val selectedDateInHours = theDate!!.time / 3600000
                val selectedDateInMinutes = theDate!!.time / 60000
                val selectedDateInSeconds = theDate!!.time / 1000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateToHours = currentDate!!.time / 3600000
                val currentDateToMinutes = currentDate!!.time / 60000
                val currentDateToSeconds = currentDate!!.time / 1000

                val differenceInHours = currentDateToHours - selectedDateInHours
                val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes
                val differenceInSeconds = currentDateToSeconds - selectedDateInSeconds
                tvSelectedDateInMinutes.setText(differenceInMinutes.toString())
                tvSelectedDateInHours.setText(differenceInHours.toString())
                tvSelectedDateInSeconds.setText(differenceInSeconds.toString())
            },
            year,
            month,
            day
        )
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}

