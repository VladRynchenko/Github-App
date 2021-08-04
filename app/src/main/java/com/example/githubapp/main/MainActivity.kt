package com.example.githubapp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.githubapp.MyApplication
import com.example.githubapp.R
import com.example.githubapp.viewmodels.ViewModelProvideFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelProvider: ViewModelProvideFactory
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MyApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelProvider).get(MainViewModel::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!viewModel.IsUserLogin()) {
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.myNavHostFragment) as NavHostFragment
            navHostFragment.navController.navigate(R.id.loginFragment)
        }

    }
}