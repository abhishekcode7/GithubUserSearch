package com.example.githubuser.retrofitService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private const val BASE_URL = "https://api.github.com/"
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val myApi: ApiInterface = retrofit.create(ApiInterface::class.java)

    fun getMyApi(): ApiInterface {
        return myApi
    }
}