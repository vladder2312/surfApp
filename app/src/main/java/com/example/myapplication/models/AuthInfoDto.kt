package com.example.myapplication.models

import com.google.gson.annotations.SerializedName

data class AuthInfoDto (
    @SerializedName("accessToken") val accessToken : String,
    @SerializedName("userInfo") val userInfo: UserInfo
)