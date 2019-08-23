package com.example.myapplication.Retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface IService {
    @GET("/auth/login")
    fun getUserByLogin(@Query("login") id: Integer)
}