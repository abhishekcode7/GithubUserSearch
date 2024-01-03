package com.example.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubuser.viewModels.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private var editText: EditText? = null
    private var searchButton: Button? = null
    private var usernameView: TextView? = null
    private var descView: TextView? = null
    private var nameView: TextView? = null
    private var followerButton: Button? = null
    private var followingButton: Button? = null
    private var viewModel: MainActivityViewModel? = null
    private var avatarView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.search_text)
        searchButton = findViewById(R.id.search_button)
        usernameView = findViewById(R.id.username)
        nameView = findViewById(R.id.name)
        followerButton = findViewById(R.id.followers)
        followingButton = findViewById(R.id.following)
        avatarView = findViewById(R.id.user_avatar)
        descView = findViewById(R.id.desc)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        setOnClickListeners()
        setupObservers()
    }

    private fun setOnClickListeners() {
        searchButton?.setOnClickListener {
            viewModel?.fetchUser(editText?.text.toString())
        }
    }

    private fun setupObservers() {
        viewModel?.userLiveData?.observe(this) {
            usernameView?.text = it.username
            it.name?.let { name ->
                nameView?.visibility = View.VISIBLE
                nameView?.text = name
            } ?: run {
                nameView?.visibility = View.GONE
            }
            descView?.text = it.description
            followerButton?.text = it.followers.toString() + " followers"
            followingButton?.text = "following " + it.following.toString()
            avatarView?.let { it1 -> Glide.with(this).load(it.avatarUrl).into(it1) }
        }
    }
}