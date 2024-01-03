package com.example.githubuser.retrofitService

import com.example.githubuser.dataClasses.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiInterface {
    @GET("/users/{username}")
    fun getUserInfo(@Path("username") username: String): Call<User>?

    @GET("/users/{username}/followers")
    fun getUserFollowers(@Path("username") username: String): Call<List<User>>?

    @GET("/users/{username}/following")
    fun getUserFollowing(@Path("username") username: String): Call<List<User>>?
}