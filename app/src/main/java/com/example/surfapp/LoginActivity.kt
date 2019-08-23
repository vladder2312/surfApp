package com.example.surfapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.example.surfapp.models.AuthInfoDto
import com.example.surfapp.models.ErrorResponseDto
import com.example.surfapp.models.LoginUserRequestDto
import com.example.surfapp.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class LoginActivity : AppCompatActivity(), Callback<AuthInfoDto> {

    override fun onFailure(call: Call<AuthInfoDto>, t: Throwable) {
        t.printStackTrace()
        loader.visibility = View.INVISIBLE
        loginButton.text = "Войти"
    }

    override fun onResponse(call: Call<AuthInfoDto>, response: Response<AuthInfoDto>) {
        if (response.isSuccessful){
            var authInfo = response.body()
            println(authInfo.toString())
            var mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
        } else {
            println(response.errorBody())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
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
            if (loginText.text.isNotEmpty() && passwordText.text.isNotEmpty() && passwordText.text.length==6){
                loader.visibility = View.VISIBLE
                loginButton.text = ""
                val call = RetrofitClient.service
                    .authorize(LoginUserRequestDto(loginText.text.toString(), passwordText.text.toString()))
                call.enqueue(this)
            }
        }


    }
}