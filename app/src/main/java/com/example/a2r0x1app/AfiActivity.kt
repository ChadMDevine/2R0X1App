package com.example.a2r0x1app


import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.afi_main.*

class AfiActivity: AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.afi_main)



        //Below allows links to AFIs to function properly
        textView_link.movementMethod = LinkMovementMethod.getInstance()
        textView1_link.movementMethod = LinkMovementMethod.getInstance()
        textView2_link.movementMethod = LinkMovementMethod.getInstance()

    }

}