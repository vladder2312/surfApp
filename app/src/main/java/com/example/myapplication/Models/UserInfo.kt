package com.example.myapplication.Models

import com.google.gson.annotations.SerializedName

class UserInfo {

    @SerializedName("id")
    var id = 0

    @SerializedName("username")
    var username = ""

    @SerializedName("firstName")
    var firstName = ""

    @SerializedName("lastName")
    var lastName = ""

    @SerializedName("userDescription")
    var userDescription = ""

}