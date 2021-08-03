package com.example.githubapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.githubapp.di.ViewModelProvideFactory
import com.example.githubapp.repository.LoginRepository
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelProvider: ViewModelProvideFactory
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var loginRepos: LoginRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MyApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelProvider).get(MainViewModel::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (loginRepos.getToken() == null) {
            findNavController(R.id.myNavHostFragment)
                .navigate(R.id.loginFragment)
        }


    }
}