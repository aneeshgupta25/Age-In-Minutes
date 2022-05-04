package com.aneesh.ageinminutes

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

        toolBar.title = "WELCOME"

        btnDatePicker.setOnClickListener{view->
            clickDatePicker(view)
        }
    }

    private fun clickDatePicker(view: View?) {
        //the below three variables are the ones which will be auto selected by the compiler on the
        //basis of current date
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            //the below three variables are the ones which we will select from the calender
            DatePickerDialog.OnDateSetListener { view,
                                                 sel_year,
                                                 sel_month,
                                                 sel_dayOfMonth ->
                val selectedDate = "$sel_dayOfMonth/${sel_month + 1}/$sel_year"
                tvEnteredDate.text = selectedDate

                Toast.makeText(this@MainActivity,
                    "The chosen year is ${sel_year}, with month ${sel_month+1}, and date $sel_dayOfMonth",
                    Toast.LENGTH_LONG).show()

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val selectedDateObject = sdf.parse(selectedDate)
                val selectedDateInMinutes = selectedDateObject!!.time/60000
                val currentDateObject = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDateObject!!.time/60000

                val ageInMinutes = currentDateInMinutes - selectedDateInMinutes
                tvAgeInMin.text = ageInMinutes.toString()

            },year,month,day)
        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}