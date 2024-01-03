package com.example.githubuser.dataClasses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @Expose
    @SerializedName("name")
    val name:String
):Serializable
