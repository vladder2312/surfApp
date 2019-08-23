package com.example.myapplication.Models

import com.google.gson.annotations.SerializedName

class ErrorResponseDto {

    @SerializedName("code")
    var code = ""

    @SerializedName("errorMessage")
    var errorMessage = ""

}