package com.example.myapplication.Models

import com.google.gson.annotations.SerializedName

class LoginUserRequestDto {

    @SerializedName("login")
    var login = ""

    @SerializedName("password")
    var password = ""

}