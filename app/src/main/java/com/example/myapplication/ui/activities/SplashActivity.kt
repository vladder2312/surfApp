package com.example.myapplication.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import java.util.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        showLoginActivity()
    }

    private fun showLoginActivity(){
        val loginIntent = Intent(this, LoginActivity::class.java)
        Timer().schedule(object : TimerTask(){
                override fun run() { startActivity(loginIntent); finish() }
            }, 300 )
    }

}
