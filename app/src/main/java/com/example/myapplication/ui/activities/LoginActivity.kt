package com.example.myapplication.ui.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.models.AuthInfoDto
import com.example.myapplication.models.LoginUserRequestDto
import com.example.myapplication.retrofit.RetrofitClient
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), Callback<AuthInfoDto> {

    private lateinit var userData : SharedPreferences
    private val USERFILENAME = "UserData"

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
            saveUser(authInfo)

            loader.visibility = View.INVISIBLE
            loginButton.text = "Войти"
            var mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
        } else {
            println("2:" + response.errorBody())
        }
    }

    private fun saveUser(authInfo: AuthInfoDto?){
        userData = getSharedPreferences(USERFILENAME, Context.MODE_PRIVATE)
        val editor = userData.edit()
        editor.putString("AccessToken",authInfo!!.accessToken)
        editor.putInt("UserID", authInfo.userInfo.id.toInt())
        editor.putString("UserName", authInfo.userInfo.username)
        editor.putString("UserFirstName", authInfo.userInfo.firstName)
        editor.putString("UserLastName", authInfo.userInfo.lastName)
        editor.putString("UserDescription",authInfo.userInfo.userDescription)
        editor.apply()
    }

    private fun showSnackbar() {
        val snackbar = Snackbar.make(loginLayout, R.string.login_error, Snackbar.LENGTH_SHORT)
        snackbar.view.setBackgroundColor(applicationContext.resources.getColor(R.color.snackbg))
        snackbar.setActionTextColor(Color.WHITE)
        snackbar.show()
    }
}