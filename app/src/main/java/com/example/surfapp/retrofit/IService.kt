package com.example.surfapp.retrofit

import com.example.surfapp.models.AuthInfoDto
import com.example.surfapp.models.ErrorResponseDto
import com.example.surfapp.models.LoginUserRequestDto
import com.example.surfapp.models.MemeDto
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