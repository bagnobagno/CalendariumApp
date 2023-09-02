package com.example.calendarium

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calendarium.databinding.ActivityCalBinding
import android.widget.CalendarView
import android.widget.TextView
import android.widget.CalendarView.OnDateChangeListener

class CalActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView //*
    private lateinit var dateTV: TextView
    private lateinit var binding: ActivityCalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dateTV = findViewById(R.id.testtest)
        calendarView = findViewById(R.id.calendarView)

        binding.btnLogout.setOnClickListener{
            val logoutIntent = Intent(this,LogInActivity::class.java)
            startActivity(logoutIntent)
        }
        calendarView.setOnDateChangeListener(
            OnDateChangeListener { view, year, month, dayOfMonth ->
                // In this Listener we are getting values
                // such as year, month and day of month
                // on below line we are creating a variable
                // in which we are adding all the variables in it.
                val Date = (dayOfMonth.toString() + "-" + (month + 1) + "-" + year)
                // set this date in TextView for Display
                dateTV.setText(Date)
            })
    }
}

