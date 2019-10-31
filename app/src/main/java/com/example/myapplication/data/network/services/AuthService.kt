package com.example.myapplication.data.network.services

import com.example.myapplication.data.network.models.AuthInfoDto
import com.example.myapplication.data.network.models.LoginUserRequestDto
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/auth/login")
    fun authorize(@Body authRequest: LoginUserRequestDto): Observable<AuthInfoDto>
}