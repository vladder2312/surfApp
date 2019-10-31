package com.example.myapplication.data.network.services

import com.example.myapplication.data.network.models.ErrorResponseDto
import io.reactivex.Observable
import retrofit2.http.POST

interface LogoutService {
    @POST("/auth/logout")
    fun logout() : Observable<ErrorResponseDto>
}