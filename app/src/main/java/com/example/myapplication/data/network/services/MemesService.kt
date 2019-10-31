package com.example.myapplication.data.network.services

import com.example.myapplication.data.network.models.MemeDto
import io.reactivex.Observable
import retrofit2.http.GET

interface MemesService {
    @GET("/memes")
    fun getMemes() : Observable<List<MemeDto>>
}