package com.example.myapplication.retrofit

import com.example.myapplication.models.AuthInfoDto
import com.example.myapplication.models.ErrorResponseDto
import com.example.myapplication.models.LoginUserRequestDto
import com.example.myapplication.models.MemeDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IService {

    @POST("/auth/login")
    fun authorize(@Body authRequest: LoginUserRequestDto): Call<AuthInfoDto>

    @POST("/auth/logout")
    fun logout() : Call<ErrorResponseDto>

    @GET("/memes")
    fun getMemes() : Call<List<MemeDto>>

}