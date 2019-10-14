package com.example.myapplication.retrofit.services

import com.example.myapplication.models.MemeDto
import retrofit2.Call
import retrofit2.http.GET

interface MemesServiceInterface {
    @GET("/memes")
    fun getMemes() : Call<List<MemeDto>>
}