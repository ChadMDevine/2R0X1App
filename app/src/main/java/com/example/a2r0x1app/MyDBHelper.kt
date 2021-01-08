package com.example.a2r0x1app

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context: Context): SQLiteOpenHelper(context,"NOTEDB",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE NOTES(NOTEID INTEGER PRIMARY KEY AUTOINCREMENT,NNAME TEXT, NDATE DATE)")
        db?.execSQL("INSERT INTO NOTES(NNAME,NDATE) VALUES('THIS IS MY FIRST NOTE','12/14/1987')")
        db?.execSQL("INSERT INTO NOTES(NNAME,NDATE) VALUES('THIS IS MY SECOND NOTE','11/12/1990')")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}