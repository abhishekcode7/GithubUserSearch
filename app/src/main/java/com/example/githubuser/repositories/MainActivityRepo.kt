package com.example.githubuser.repositories

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.githubuser.dataClasses.User
import com.example.githubuser.retrofitService.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityRepo(private val context:Context) {
    private var callUser:Call<User>? = null
    private var callUserList:Call<List<User>>? = null
    val userLiveData = MutableLiveData<User>()
    val userListLiveData = MutableLiveData<List<User>>()
    val noUserFoundLD = MutableLiveData<Boolean>()

    fun fetchUser(userName:String){
        callUser?.cancel()
        callUser = RetrofitClient.getMyApi().getUserInfo(userName)

        callUser?.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful) userLiveData.value = response.body()
                else if(response.code() == 404 ) {
                    noUserFoundLD.value = true
                }else showErrorToast()
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                showErrorToast()
            }
        })
    }

    fun fetchUserFollowers(userName:String){
        callUserList?.cancel()
        callUserList = RetrofitClient.getMyApi().getUserFollowers(userName)

        callUserList?.enqueue(object :Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if(response.isSuccessful) userListLiveData.value = response.body()
                else showErrorToast()
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                showErrorToast()
            }
        })
    }

    fun fetchUserFollowing(userName:String){
        callUserList?.cancel()
        callUserList = RetrofitClient.getMyApi().getUserFollowing(userName)

        callUserList?.enqueue(object :Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if(response.isSuccessful) userListLiveData.value = response.body()
                else showErrorToast()
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                showErrorToast()
            }
        })
    }

    fun showErrorToast(){
        Toast.makeText(context,"Something went wrong !",Toast.LENGTH_SHORT).show()
    }
}