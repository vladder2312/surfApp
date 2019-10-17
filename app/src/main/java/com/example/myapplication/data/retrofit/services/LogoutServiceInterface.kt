package com.example.myapplication.data.retrofit.services

import com.example.myapplication.data.models.ErrorResponseDto
import io.reactivex.Observable
import retrofit2.http.POST

interface LogoutServiceInterface {
    @POST("/auth/logout")
    fun logout() : Observable<ErrorResponseDto>
}