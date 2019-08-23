package com.example.surfapp.models

import com.google.gson.annotations.SerializedName

class AuthInfoDto (

    @SerializedName("accessToken")
    val accessToken : String,

    @SerializedName("userInfo")
    val userInfo: UserInfo

)