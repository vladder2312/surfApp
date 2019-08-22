package com.example.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        passwordText.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                passwordLabel.setTextColor(Color.WHITE)
                passwordLabel.text = "Пароль должен содержать 6 символов"
            } else {
                passwordLabel.setTextColor(Color.RED)
                if(passwordText.text.isEmpty()){
                    passwordLabel.text = "Поле не может быть пустым"
                }
                if (passwordText.text.length==6){
                    passwordLabel.text = ""
                    passwordLabel.setTextColor(Color.WHITE)
                }
            }
        }

        loginText.onFocusChangeListener = View.OnFocusChangeListener{ v, hasFocus ->
            if (hasFocus){
                loginLabel.text=""
            } else {
                if (loginText.text.isEmpty()){
                    loginLabel.setTextColor(Color.RED)
                    loginLabel.text = "Поле не может быть пустым"
                } else {
                    loginLabel.text=""
                    loginLabel.setTextColor(Color.WHITE)
                }
            }
        }

        loginButton.setOnClickListener {
            loader.visibility = View.VISIBLE
            loginButton.text = ""
            Timer().schedule(object : TimerTask(){
                override fun run(){
                    loader.visibility = View.INVISIBLE
                    loginButton.text = "Войти"
                }
            }, 2000)

        }

        
    }
}