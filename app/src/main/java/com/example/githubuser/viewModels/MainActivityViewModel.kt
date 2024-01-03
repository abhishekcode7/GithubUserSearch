package com.example.githubuser.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubuser.dataClasses.User
import com.example.githubuser.repositories.MainActivityRepo

class MainActivityViewModel(private val context:Context) : ViewModel() {

    class Factory(private val context: Context):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainActivityViewModel(context) as T
        }
    }

    private val repo = MainActivityRepo(context)

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

    val noUserFoundLD: LiveData<Boolean> = Transformations.map(repo.noUserFoundLD){
        it
    }
}