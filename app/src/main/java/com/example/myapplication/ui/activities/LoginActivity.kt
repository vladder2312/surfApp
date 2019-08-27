package com.example.myapplication.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import com.example.myapplication.R
import com.example.myapplication.models.AuthInfoDto
import com.example.myapplication.models.LoginUserRequestDto
import com.example.myapplication.retrofit.RetrofitClient
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class LoginActivity : AppCompatActivity(), Callback<AuthInfoDto> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        passwordText.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                passwordLabel.text = ""
            } else {
                validateFields()
            }
        }
        loginText.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                loginLabel.text = ""
            } else {
                validateFields()
            }
        }
        loginButton.setOnClickListener {
            if (loginText.text.isNotEmpty() && passwordText.text.isNotEmpty() && passwordText.text.length == 6) {
                authorize()
            } else {
                validateFields()
            }
        }
    }

    private fun authorize() {
        loader.visibility = View.VISIBLE
        loginButton.text = ""
        val call = RetrofitClient.authService
            .authorize(
                LoginUserRequestDto(
                    loginText.text.toString(),
                    passwordText.text.toString()
                )
            )
        call.enqueue(this)
    }

    override fun onFailure(call: Call<AuthInfoDto>, t: Throwable) {
        t.printStackTrace()
        println("here")
        loader.visibility = View.INVISIBLE
        loginButton.text = "Войти"
        showSnackbar()
    }

    private fun validateFields() {
        if (loginText.text.isEmpty()) {
            loginLabel.text = "Поле не может быть пустым"
        } else {
            loginLabel.text = ""
        }
        when {
            passwordText.text.isEmpty() -> passwordLabel.text = "Поле не может быть пустым"
            passwordText.text.length != 6 -> passwordLabel.text =
                "Пароль должен содержать 6 символов"
            else -> passwordLabel.text = ""
        }
    }

    override fun onResponse(call: Call<AuthInfoDto>, response: Response<AuthInfoDto>) {
        if (response.isSuccessful) {
            var authInfo = response.body()
            println("1:" + authInfo.toString())
            loader.visibility = View.INVISIBLE
            loginButton.text = "Войти"
            var mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
        } else {
            println("2:" + response.errorBody())
        }
    }

    private fun showSnackbar() {
        val snackbar = Snackbar.make(loginLayout, R.string.login_error, Snackbar.LENGTH_SHORT)
        snackbar.view.setBackgroundColor(applicationContext.resources.getColor(R.color.snackbg))
        snackbar.setActionTextColor(Color.WHITE)
        snackbar.show()
    }
}