package com.example.githubuser.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.githubuser.dataClasses.User
import com.example.githubuser.repositories.MainActivityRepo

class MainActivityViewModel:ViewModel() {

    val repo = MainActivityRepo()

    fun fetchUser(userName:String){
        repo.fetchUser(userName)
    }
    val userLiveData:LiveData<User> = Transformations.map(repo.userLiveData) {
        it
    }
}