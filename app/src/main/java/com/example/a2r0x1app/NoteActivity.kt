package com.example.a2r0x1app
import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.note_main.*
import java.text.SimpleDateFormat
import java.util.*


class NoteActivity: AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note_main)

        // Sets default date by calling the dataReset function
        dateReset()

        buttonDateSelect.setOnClickListener {
            showDatePicker()
        }



            // DATABASE CHECKER & DATABASE NOTE SAVER
        var helper = MyDBHelper(applicationContext)
        var db = helper.readableDatabase
        var rs = db.rawQuery("SELECT * FROM NOTES",null)


        // THIS IS A TEST TOAST IN ORDER TO MAKE SURE THE DATABASE IS WORKING
//        if(rs.moveToNext())
//            Toast.makeText(applicationContext,rs.getString(1), Toast.LENGTH_LONG).show()


        // THIS SAVES THE NOTE TO THE DATABASE
        buttonSaveNote.setOnClickListener {
            var cv = ContentValues()
            cv.put("NNAME",editTextNote.text.toString())
            cv.put("NDATE",editTextDate.text.toString())
            db.insert("NOTES",null,cv)

            editTextNote.setText("")
            editTextDate.setText("")

            // Below will reset the focus on the notes text to quickly enter another note.
            //editTextNote.requestFocus()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager

            // HIDE THE KEYBOARD AFTER SAVE BUTTON ENTRY
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
            Toast.makeText(applicationContext, "Note saved!", Toast.LENGTH_LONG).show()

            editTextNote.clearFocus()
            dateReset()
        }

    }

   // @RequiresApi(Build.VERSION_CODES.N)
    private fun showDatePicker() {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create dialog
        val datepicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

            editTextDate.text = "" + ((month+1)) +"/" + dayOfMonth + "/" + year
        },year,month,day)

       datepicker.show()

    }

    private fun dateReset(){
        val dateTest = findViewById<TextView>(R.id.editTextDate)
        val simpleDateFormat = SimpleDateFormat("MM/DD/YYYY")
        val currentDate: String = simpleDateFormat.format(Date())
        // Set current date to the editTextDate for default date value
        dateTest.text = currentDate
    }

}