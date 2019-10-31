package com.example.myapplication.data.network.models
import androidx.room.Entity

@Entity
data class LoginUserRequestDto (
    val login : String,
    val password : String
)