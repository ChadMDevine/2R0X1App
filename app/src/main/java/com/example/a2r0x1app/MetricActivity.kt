package com.example.a2r0x1app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.metrics_main.*

class MetricActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.metrics_main)




        mcBtn.setOnClickListener {
            startActivity(Intent(this,McRateActivity::class.java))
        }
    }
}