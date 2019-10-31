package com.example.myapplication.ui.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.data.network.models.AuthInfoDto
import com.example.myapplication.data.network.models.LoginUserRequestDto
import com.example.myapplication.data.network.RetrofitClient
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {

    private lateinit var userData: SharedPreferences

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
            .authorize(LoginUserRequestDto(loginText.text.toString(), passwordText.text.toString()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(4,TimeUnit.SECONDS)
            .subscribe({
                var authInfo = it
                loader.visibility = View.INVISIBLE
                loginButton.text = "Войти"
                saveUser(authInfo)
                var mainIntent = Intent(this, MainActivity::class.java)
                startActivity(mainIntent)
            },{
                it.printStackTrace()
                println("here")
                loader.visibility = View.INVISIBLE
                loginButton.text = "Войти"
                showSnackbar()
            })
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

    private fun saveUser(authInfo: AuthInfoDto?) {
        userData = getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val editor = userData.edit()
        editor.putString("AccessToken", authInfo!!.accessToken)
        editor.putInt("UserID", authInfo.userInfo.id.toInt())
        editor.putString("UserName", authInfo.userInfo.username)
        editor.putString("UserFirstName", authInfo.userInfo.firstName)
        editor.putString("UserLastName", authInfo.userInfo.lastName)
        editor.putString("UserDescription", authInfo.userInfo.userDescription)
        editor.apply()
    }

    //TODO("Не показывается SnackBar")
    private fun showSnackbar() {
        val snackbar = Snackbar.make(loginLayout, R.string.login_error, Snackbar.LENGTH_SHORT)
        snackbar.view.setBackgroundColor(applicationContext.resources.getColor(R.color.colorSnackBar))
        snackbar.setActionTextColor(Color.WHITE)
        snackbar.show()
    }
}