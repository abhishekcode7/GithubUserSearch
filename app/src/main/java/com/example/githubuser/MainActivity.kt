package com.example.githubuser

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.dataClasses.User
import com.example.githubuser.recyclerView.rvAdapter
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
    private var username: String? = null
    private var userLinearLayout: LinearLayout? = null
    private var noContentView: TextView? = null

    companion object {
        const val SEARCH_USER = "SEARCH_USER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        username = intent?.getStringExtra(SEARCH_USER)

        editText = findViewById(R.id.search_text)
        searchButton = findViewById(R.id.search_button)
        usernameView = findViewById(R.id.username)
        nameView = findViewById(R.id.name)
        followerButton = findViewById(R.id.followers)
        followingButton = findViewById(R.id.following)
        avatarView = findViewById(R.id.user_avatar)
        descView = findViewById(R.id.desc)
        userLinearLayout = findViewById(R.id.user_ll)
        noContentView = findViewById(R.id.no_user_found_text)

        viewModel = ViewModelProvider(
            this,
            MainActivityViewModel.Factory(context = this)
        )[MainActivityViewModel::class.java]
        setupSearchBar()
        setOnClickListeners()
        setupObservers()
    }

    private fun setupSearchBar() {
        username?.let {
            editText?.visibility = View.GONE
            searchButton?.visibility = View.GONE
            viewModel?.fetchUser(it)
        } ?: run {
            editText?.visibility = View.VISIBLE
            searchButton?.visibility = View.VISIBLE
        }
    }

    private fun setOnClickListeners() {
        searchButton?.setOnClickListener {
            username = editText?.text.toString()
            username?.let {
                if (it.isEmpty()) Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT)
                    .show()
                else
                    viewModel?.fetchUser(it)
            }
        }

        followerButton?.setOnClickListener {
            username?.let {
                viewModel?.fetchUserFollowers(it)
            }
        }

        followingButton?.setOnClickListener {
            username?.let {
                viewModel?.fetchUserFollowing(it)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupObservers() {
        viewModel?.userLiveData?.observe(this) {
            if (it != null) {
                userLinearLayout?.visibility = View.VISIBLE
                noContentView?.visibility = View.GONE
            } else {
                userLinearLayout?.visibility = View.GONE
                noContentView?.visibility = View.VISIBLE
                return@observe
            }
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

        viewModel?.userListLiveData?.observe(this) {
            showDialog(it)
        }

        viewModel?.noUserFoundLD?.observe(this) {
            if (it == true) {
                noContentView?.visibility = View.VISIBLE
                userLinearLayout?.visibility = View.GONE
            } else {
                noContentView?.visibility = View.GONE
                userLinearLayout?.visibility = View.VISIBLE
            }
        }
    }

    private fun showDialog(list: List<User>) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_layout)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)

        val rv = dialog.findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = rvAdapter(list)
        dialog.show()
    }
}