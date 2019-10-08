package com.example.myapplication.retrofit.services

import com.example.myapplication.models.AuthInfoDto
import com.example.myapplication.models.LoginUserRequestDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthServiceInterface {
    @POST("/auth/login")
    fun authorize(@Body authRequest: LoginUserRequestDto): Call<AuthInfoDto>
}