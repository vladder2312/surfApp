package com.example.myapplication.data.network

import com.example.myapplication.data.network.services.AuthService
import com.example.myapplication.data.network.services.MemesService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    var gson = GsonBuilder().create()

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://demo3161256.mockable.io")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    var authService = retrofit.create(AuthService::class.java)
    var logoutService = retrofit.create(AuthService::class.java)
    var memesService = retrofit.create(MemesService::class.java)
}