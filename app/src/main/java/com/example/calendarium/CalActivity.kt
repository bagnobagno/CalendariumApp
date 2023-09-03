package com.example.calendarium

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calendarium.databinding.ActivityCalBinding
import com.example.calendarium.databinding.ActivityPopUpBinding
import android.widget.CalendarView
import android.widget.TextView
import android.widget.CalendarView.OnDateChangeListener
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import android.view.LayoutInflater
import android.widget.ListView


class CalActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView //*
    private lateinit var dateTV: TextView
    private lateinit var binding: ActivityCalBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var bindingPop: ActivityPopUpBinding

    private fun note() {
        calendarView = findViewById(R.id.calendarView)
        dateTV = findViewById(R.id.selectedDateTextView)
        bindingPop = ActivityPopUpBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(this)
        builder.setView(bindingPop.root)
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog,_ ->
            dialog.dismiss()
        })

        val alertDialog: AlertDialog = builder.create()

        calendarView.setOnDateChangeListener {_ , year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth-${month + 1}-$year"
            dateTV.text = selectedDate
            alertDialog.show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        calendarView = findViewById(R.id.calendarView)

        binding.btnLogout.setOnClickListener{
            firebaseAuth.signOut()

            val logoutIntent = Intent(this, LogInActivity::class.java)
            logoutIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(logoutIntent)
            finish()
        }

        note()
    }

}

