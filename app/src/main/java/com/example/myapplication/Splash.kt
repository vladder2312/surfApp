package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        setVisible(true)
        supportActionBar?.hide()
        showNextActivity()
    }

    private fun showNextActivity(){
        val loginIntent = Intent(this, Login::class.java)
        Timer().schedule(object : TimerTask(){
                override fun run() { startActivity(loginIntent) }
            }, 300 )
    }

}
