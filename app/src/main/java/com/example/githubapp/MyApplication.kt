package com.example.githubapp

import android.app.Application
import com.example.githubapp.di.DaggerAppComponent

open class MyApplication : Application() {
    val appComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}