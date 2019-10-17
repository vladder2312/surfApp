package com.example.myapplication

import android.app.Application
import android.util.Log
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler RxJavaPlugins@{ e ->
            if (e is UndeliverableException) {
                Log.e("RxJava", "Global Error")
            }
        }
    }
}
