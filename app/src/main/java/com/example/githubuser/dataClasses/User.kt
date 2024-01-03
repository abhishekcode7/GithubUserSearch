package com.example.githubuser.dataClasses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @Expose
    @SerializedName("name")
    val name: String?,
    @Expose
    @SerializedName("login")
    val username: String,
    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @Expose
    @SerializedName("bio")
    val description: String,
    @Expose
    @SerializedName("followers")
    val followers: Int,
    @Expose
    @SerializedName("following")
    val following: Int
) : Serializable
