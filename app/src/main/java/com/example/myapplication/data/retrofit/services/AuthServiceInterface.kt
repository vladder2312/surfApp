package com.example.myapplication.data.retrofit.services

import com.example.myapplication.data.models.AuthInfoDto
import com.example.myapplication.data.models.LoginUserRequestDto
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthServiceInterface {
    @POST("/auth/login")
    fun authorize(@Body authRequest: LoginUserRequestDto): Observable<AuthInfoDto>
}