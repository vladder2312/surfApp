package com.example.myapplication.data.retrofit.services

import com.example.myapplication.data.models.MemeDto
import io.reactivex.Observable
import retrofit2.http.GET

interface MemesServiceInterface {
    @GET("/memes")
    fun getMemes() : Observable<List<MemeDto>>
}