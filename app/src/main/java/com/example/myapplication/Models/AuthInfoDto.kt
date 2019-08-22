package com.example.myapplication.Models

import com.google.gson.annotations.SerializedName

class AuthInfoDto {

    @SerializedName("accessToken")
    var accessToken = ""

    @SerializedName("userInfo")
    var userInfo: UserInfo? = null

}