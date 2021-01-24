package com.example.a2r0x1app

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context: Context): SQLiteOpenHelper(context,"NOTEDB",null,2) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE NOTES(NOTEID INTEGER PRIMARY KEY AUTOINCREMENT,NNAME TEXT, NDATE DATE, NPICK TEXT)")
//        db?.execSQL("INSERT INTO NOTES(NNAME,NDATE) VALUES('THIS IS MY FIRST NOTE','12/14/1987')")
//        db?.execSQL("INSERT INTO NOTES(NNAME,NDATE) VALUES('THIS IS MY SECOND NOTE','11/12/1990')")

    }

    fun addNote(date: String, note: String, pick: String): Long {
        val cv = ContentValues()
        cv.put("NDATE", date)
        cv.put("NNAME", note)
        cv.put("NPICK", pick)
        return this.writableDatabase.insert("NOTES", null, cv)
    }

    fun updateById(id: String, date: String, note: String, pick: String): Int {
        val cv = ContentValues()
        cv.put("NDATE",date)
        cv.put("NNAME", note)
        cv.put("NPICK", pick)
        val whereclause = "${"NOTEID"}=?"
        val whereargs = arrayOf(id.toString())
        return this.writableDatabase.update("NOTES", cv, whereclause, whereargs)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}