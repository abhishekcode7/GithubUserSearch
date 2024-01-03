package com.example.githubuser.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.githubuser.dataClasses.User
import com.example.githubuser.retrofitService.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityRepo {
    private var call:Call<User>? = null
    val userLiveData = MutableLiveData<User>()
    fun fetchUser(userName:String){
        call = RetrofitClient.getMyApi().getUserInfo(userName)

        call?.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                userLiveData.value = response.body()
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("xxxxxxxxx",t.message.toString())
            }
        })
    }
}