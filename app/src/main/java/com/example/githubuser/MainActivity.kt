package com.example.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.githubuser.viewModels.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private var editText:EditText? = null
    private var searchButton:Button? = null
    private var viewModel:MainActivityViewModel ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.search_text)
        searchButton = findViewById(R.id.search_button)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        setOnClickListeners()
    }

    private fun setOnClickListeners(){
        searchButton?.setOnClickListener {

        }
    }
}