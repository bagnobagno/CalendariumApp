package com.example.calendarium

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calendarium.databinding.ActivityCalBinding
import android.widget.CalendarView
import android.widget.TextView
import android.widget.CalendarView.OnDateChangeListener
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import android.view.LayoutInflater
import android.widget.ListView
import android.icu.util.Calendar
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager


class CalActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView //*
    private lateinit var dateTV: TextView
    private lateinit var binding: ActivityCalBinding
    private lateinit var firebaseAuth: FirebaseAuth

    private fun note() {
        dateTV = findViewById(R.id.selectedDateTextView)
        calendarView.setOnDateChangeListener(
                OnDateChangeListener { view, year, month, dayOfMonth ->
                    val Date = (dayOfMonth.toString() + "-"
                            + (month + 1) + "-" + year)
                    dateTV.setText(Date)
                })
        //TODO: POBIERANIE Z BAZY DO LISTVIEW \"@+id/notesListView\"
        //TODO: GUZIK DODAJ MA DZIAŁAĆ ID "@+id/addNoteButton", ZROBIĆ LAYOUT EDITTEXT, GODZINA TEŻ
        //TODO:
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemViewModel(i, "Item " + i))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter


//        val layoutManager=LinearLayoutManager(this)
//        val dataset = arrayOf("January", "February", "March")
//        val customAdapter = CustomAdapter(dataset)
//        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
//        recyclerView.adapter = customAdapter
//        recyclerView.layoutManager = layoutManager



        val currentDate = Calendar.getInstance().time
//        val formattedDate = formatDate(currentDate.year, currentDate.month, currentDate.day)
//        onDateSelected(formattedDate)
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
//
        note()
    }

}

