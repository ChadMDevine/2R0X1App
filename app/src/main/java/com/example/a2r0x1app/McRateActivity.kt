package com.example.a2r0x1app

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.mc_rate_main.*

class McRateActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mc_rate_main)


        mcCalcBtn.setOnClickListener {

            mcRate.text = "Result: " + (mcHrs.text.toString().toFloat() / pssdHrs.text.toString().toFloat()*100).toString().toDouble() + "%"
        }


    }


}