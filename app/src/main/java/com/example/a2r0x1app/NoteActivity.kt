package com.example.a2r0x1app
import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.database.getStringOrNull
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.note_main.*
import java.lang.NullPointerException
import java.text.SimpleDateFormat
import java.util.*


class NoteActivity: AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note_main)

        //Set dropdown string array and place in variable
        val languages = resources.getStringArray(R.array.Languages)


        // access the spinner
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, languages
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
/*
                    Toast.makeText(
                        this@NoteActivity,
                        getString(R.string.selected_item) + " " +
                                "" + languages[position], Toast.LENGTH_SHORT
                    ).show()


 */

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }



                // Sets default date by calling the dateReset function
        dateReset()


        // Date selection picker open
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

        // Count to know if it's initial save or an update (else statement)
        var countSave = 0

        // THIS SAVES THE NOTE TO THE DATABASE
        buttonSaveNote.setOnClickListener {

            if (countSave == 0) {

                var newNote = editTextNote.text.toString()
                var newDate = editTextDate.text.toString()
                //var newSpin = findViewById<Spinner>(R.id.spinner)

                var spinnerSelect = spinner.selectedItem.toString()
                MyDBHelper(applicationContext).addNote(newDate, newNote, spinnerSelect)

                // Below will reset the focus on the notes text to quickly enter another note.
                //editTextNote.requestFocus()

                // HIDES THE KEYBOARD BY CALLING "hideKeyboard" FUNCTION
                hideKeyboard(it)


                // MESSAGE VERIFYING THAT NOTE IS SAVED
                Toast.makeText(applicationContext, "Note saved!", Toast.LENGTH_LONG).show()

                editTextNote.clearFocus()
                editTextDate.clearFocus()
                spinner.clearFocus()
                //dateReset()

                /*
                // BRINGS BACK LAST SAVED NOTE AND DATE FROM DATABASE
                var helper = MyDBHelper(applicationContext)
                var db = helper.readableDatabase
                var dq = db.rawQuery("SELECT * FROM NOTES", null)

                if (dq.moveToLast())
                    editTextNote.setText(dq.getString(dq.getColumnIndex("NNAME")))
                if (dq.moveToLast())
                    editTextDate.setText(dq.getString(dq.getColumnIndex("NDATE")))
                if (dq.moveToLast())
                    newSpin.setSelection(dq.getColumnIndex("NPICK"))
                 */

                setPreviousRecord()


                // ADDS 1 TO SIGNIFY NOTES INITIAL SAVE. ANY OTHER SAVES WILL BE UPDATES BELOW
                countSave = +1

            }

            else{

                // BRINGS BACK LAST RECORD IN ORDER TO SAVE THE UPDATES MADE
                var helper = MyDBHelper(applicationContext)
                var db = helper.readableDatabase
                var upDate = db.rawQuery("SELECT * FROM NOTES", null)

                if (upDate.moveToLast()) {

                    var noteUpdate = editTextNote.text.toString()
                    var dateUpdate = editTextDate.text.toString()
                    var idUpdate = upDate.getString(upDate.getColumnIndex("NOTEID"))
                    //var updateSpin = findViewById<Spinner>(R.id.spinner)

                    var spinnerUpdate = spinner.selectedItem.toString()

                    // SAVES THE UPDATED VALUES INTO THE DATABASE BASED ON RECORD ID
                    MyDBHelper(applicationContext).updateById(idUpdate, dateUpdate, noteUpdate, spinnerUpdate)

                    // HIDES THE KEYBOARD BY CALLING "hideKeyboard" FUNCTION
                    hideKeyboard(it)

                    // TOAST TO DISPLAY SUCCESSFUL UPDATE
                    Toast.makeText(applicationContext,"Updated!",Toast.LENGTH_LONG).show()

                }

            }

        }

    }

   // @RequiresApi(Build.VERSION_CODES.N)
    // DISPLAYS THE DATE PICKER TO SELECT DATE
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

    // RESETS THE DATE ON THE SCREEN TO CURRENT DATE
    private fun dateReset(){
        val dateTest = findViewById<TextView>(R.id.editTextDate)
        val simpleDateFormat = SimpleDateFormat("MM/DD/YYYY")
        val currentDate: String = simpleDateFormat.format(Date())
        // Set current date to the editTextDate for default date value
        dateTest.text = currentDate
    }

    // HIDES THE KEYBOARD
    private fun hideKeyboard(view: View){
        // HIDE THE KEYBOARD AFTER SAVE BUTTON ENTRY
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun setPreviousRecord(){

        // BRINGS BACK LAST SAVED NOTE AND DATE FROM DATABASE
        var newSpin = findViewById<Spinner>(R.id.spinner)

        var helper = MyDBHelper(applicationContext)
        var db = helper.readableDatabase
        var dq = db.rawQuery("SELECT * FROM NOTES", null)


        if (dq.moveToLast())
            editTextNote.setText(dq.getString(dq.getColumnIndex("NNAME")))
        if (dq.moveToLast())
            editTextDate.text = dq.getString(dq.getColumnIndex("NDATE"))
        if (dq.moveToLast())
            newSpin.getItemAtPosition(dq.getColumnIndex("NPICK"))


    }

}