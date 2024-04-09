package com.example.newsapp.api

import android.util.Log
import com.example.newsapp.CacheInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

object ApiManager {
    private var retrofit: Retrofit

    init {
        val logging =
            HttpLoggingInterceptor { message -> Log.e("api ->", message) }
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client =
            OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        retrofit =
            Retrofit.Builder()
                .client(client)
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getServices(): WebServices {
        return retrofit.create(WebServices::class.java)
    }


}
