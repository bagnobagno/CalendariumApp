package com.example.calendarium

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calendarium.databinding.ActivityCalBinding
import android.widget.CalendarView
import android.widget.TextView
import android.widget.CalendarView.OnDateChangeListener
import com.google.firebase.auth.FirebaseAuth
import android.icu.util.Calendar
import android.text.Editable
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.calendarium.databinding.ActivityPopUpBinding

class CalActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView //*
    private lateinit var dateTV: TextView
    private lateinit var binding: ActivityCalBinding
    private lateinit var bindingPop: ActivityPopUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    private fun viewNote() {
        //TODO: POBIERANIE Z BAZY DO LISTVIEW \"@+id/notesListView\"
        //TODO: GUZIK DODAJ MA DZIAŁAĆ ID "@+id/addNoteButton", , GODZINA TEŻ
        //TODO:
        //ZROBIĆ LAYOUT EDITTEXT-> pop_up
    }
    private fun addNoteView(){
        bindingPop = ActivityPopUpBinding.inflate(layoutInflater)
        setContentView(bindingPop.root)
    }
    private fun addNoteText(): Editable? {
        val editTextNote = bindingPop.addNoteEditText
        val note = editTextNote.text
        return note
    }
//    private fun addNoteTime(): Editable? {
//        val timePickerNote = bindingPop.addNoteTimePicker
//        // val time = $timePickerNote.hour +":"+ timePickerNote.minute
////        timePickerNote.setOnTimeChangedListener(timePickerNote.OnTimeChangedListener { _ , hour, minute ->
////            time = hour + ":" + minute
////        })
//        // return time
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Calendar.getInstance().time

        val data = ArrayList<ItemViewModel>()
        val recyclerview = binding.recyclerView //findViewById<RecyclerView>(R.id.recyclerview)


        binding.addNoteButton.setOnClickListener{
            addNoteView()
            val note = addNoteText()
            //val time = addNoteTime()
            bindingPop.addNoteSubmit.setOnClickListener{
                if(note != null){
                    recyclerview.layoutManager = LinearLayoutManager(this)
                    data.add(ItemViewModel(R.drawable.ic_baseline_folder_24, note))
                    val adapter = CustomAdapter(data)
                    recyclerview.adapter = adapter
                    setContentView(binding.root)

                }
            }
            bindingPop.addNoteBack.setOnClickListener{
                setContentView(binding.root)
            }
        }

        calendarView = findViewById(R.id.calendarView)
        dateTV = findViewById(R.id.selectedDateTextView)
        calendarView.setOnDateChangeListener(
            OnDateChangeListener { view, year, month, dayOfMonth ->
                val Date = (dayOfMonth.toString() + "-"
                        + (month + 1) + "-" + year)
                dateTV.setText(Date)
            })
        //viewNote(Date) - i wtedy z bazy danych pobiera notatki dla danej daty


//        val formattedDate = formatDate(currentDate.year, currentDate.month, currentDate.day)
//        onDateSelected(formattedDate)


        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnLogout.setOnClickListener{
            firebaseAuth.signOut()
            val logoutIntent = Intent(this, LogInActivity::class.java)
            logoutIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(logoutIntent)
            finish()
        }
    }

}

