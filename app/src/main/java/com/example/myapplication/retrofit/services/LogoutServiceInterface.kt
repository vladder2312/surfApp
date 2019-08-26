package com.example.myapplication.retrofit.services

import com.example.myapplication.models.ErrorResponseDto
import retrofit2.Call
import retrofit2.http.POST

interface LogoutServiceInterface {
    @POST("/auth/logout")
    fun logout() : Call<ErrorResponseDto>
}