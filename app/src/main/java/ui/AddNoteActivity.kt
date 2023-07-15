package ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import com.google.android.material.snackbar.Snackbar
import ir.dunijet.myapplication.R
import ir.dunijet.myapplication.database.Notedbadapter
import ir.dunijet.myapplication.model.Note
import java.util.*

class AddNoteActivity : AppCompatActivity() {
    lateinit var dbadapter:Notedbadapter
    var time:String=""
    var date:String=""
    lateinit var calender:Calendar
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        val editTitle=findViewById<AppCompatEditText>(R.id.edt_title)
        val editDescription=findViewById<AppCompatEditText>(R.id.edt_description)
        val btnTime=findViewById<AppCompatButton>(R.id.btn_time)
        val btnDate=findViewById<AppCompatButton>(R.id.btn_date)
        val btnSave=findViewById<AppCompatButton>(R.id.btn_save)
        val imgBack=findViewById<AppCompatImageView>(R.id.img_back)
        dbadapter= Notedbadapter(applicationContext)
        calender=Calendar.getInstance()
        imgBack.setOnClickListener {
            finish()
        }
        btnSave.setOnClickListener ({
            val note = Note(0, editTitle.text.toString(), editDescription.text.toString(), time, date)
            val result = dbadapter.insertNote(note)
            if (result > 0) {
                Snackbar.make(it, "Note added successfully", Snackbar.LENGTH_LONG).show()
            } else {
                Snackbar.make(it, "Note error", Snackbar.LENGTH_LONG).show()


            }

        })

        val year=calender.get(Calendar.YEAR)
        val month=calender.get(Calendar.MONTH)
        val day=calender.get(Calendar.DAY_OF_MONTH)
        val hour=calender.get(Calendar.HOUR_OF_DAY)
        val minute=calender.get(Calendar.MINUTE)

        btnDate.setOnClickListener({
            var dialog=DatePickerDialog(this@AddNoteActivity,object:DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    date="${year}/${month+1}/${dayOfMonth}"
                    btnDate.setText(date)
                    Log.e("","")
                }

            },year,month,day)
            dialog.show()
        })
        btnTime.setOnClickListener({
            val dialog=TimePickerDialog(this@AddNoteActivity,
            object :TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    time="${hourOfDay}:${minute}"
                    btnTime.setText(time)

                }

            },hour,minute,true)
            dialog.show()
        })
    }
}

