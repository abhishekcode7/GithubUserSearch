package com.example.githubuser.retrofitService

import com.example.githubuser.dataClasses.User
import retrofit2.Call
import retrofit2.http.GET




interface ApiInterface {
    @GET("/users/{username}")
    fun getHeroes(): Call<User>?
}