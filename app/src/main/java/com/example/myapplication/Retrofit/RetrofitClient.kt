package com.example.myapplication.Retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var ourInstance: Retrofit?=null

    val instance: Retrofit?
        get() {
            if (ourInstance==null){
                ourInstance = Retrofit.Builder()
                    .baseUrl("virtserver.swaggerhub.com/AndroidSchool2019/SurfAndroidSchool2019/1.0.0")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
            return ourInstance
        }
}