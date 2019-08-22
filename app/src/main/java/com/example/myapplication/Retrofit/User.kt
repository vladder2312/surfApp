package com.example.myapplication.Retrofit

import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("login")
    var login: String = ""

    @SerializedName("password")
    var password: String = ""

}

