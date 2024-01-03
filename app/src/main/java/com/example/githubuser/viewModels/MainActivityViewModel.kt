package com.example.githubuser.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.githubuser.dataClasses.User
import com.example.githubuser.repositories.MainActivityRepo

class MainActivityViewModel : ViewModel() {

    val repo = MainActivityRepo()

    fun fetchUser(userName: String) {
        repo.fetchUser(userName)
    }

    fun fetchUserFollowers(userName: String) {
        repo.fetchUserFollowers(userName)
    }

    fun fetchUserFollowing(userName: String) {
        repo.fetchUserFollowing(userName)
    }

    val userLiveData: LiveData<User> = Transformations.map(repo.userLiveData) {
        it
    }

    val userListLiveData: LiveData<List<User>> = Transformations.map(repo.userListLiveData) {
        it
    }
}