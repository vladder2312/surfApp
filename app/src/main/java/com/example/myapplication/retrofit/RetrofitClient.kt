package com.example.myapplication.retrofit

import com.example.myapplication.retrofit.services.AuthServiceInterface
import com.example.myapplication.retrofit.services.MemesServiceInterface
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    var gson = GsonBuilder().create()

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://demo3161256.mockable.io")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    var authService = retrofit.create(AuthServiceInterface::class.java)
    var logoutService = retrofit.create(AuthServiceInterface::class.java)
    var memesService = retrofit.create(MemesServiceInterface::class.java)
}