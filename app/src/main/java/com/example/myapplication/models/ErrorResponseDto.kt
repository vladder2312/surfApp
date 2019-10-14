package com.example.myapplication.models

import com.google.gson.annotations.SerializedName

data class ErrorResponseDto (
    @SerializedName("code") val code : String,
    @SerializedName("errorMessage") val errorMessage : String
)