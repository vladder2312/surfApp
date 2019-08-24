package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import java.util.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        showLoginActivity()
    }

    override fun onRestart() {
        super.onRestart()
        showLoginActivity()
    }

    private fun showLoginActivity(){
        val loginIntent = Intent(this, LoginActivity::class.java)
        Timer().schedule(object : TimerTask(){
                override fun run() { startActivity(loginIntent) }
            }, 300 )
    }



}
