package com.example.a2r0x1app

import android.content.ContentValues
import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.history_notes_main.*
import java.util.ArrayList

class HistoryNotesActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_notes_main)


        // QUERIES DATABASE AND CAPTURES ALL RECORDS
        var helper = MyDBHelper(applicationContext)
        var db = helper.readableDatabase
        var rs = db.rawQuery("SELECT * FROM NOTES",null)

        val list = ArrayList<ExampleItem>()

        while (rs.moveToNext()){

            val item = ExampleItem(rs.getString(rs.getColumnIndex("NDATE")),
                rs.getString(rs.getColumnIndex("NNAME")),
                rs.getString(rs.getColumnIndex("NPICK")))
            list += item
        }

        recycler_view.adapter = ExampleAdapter(list)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)

    }

}