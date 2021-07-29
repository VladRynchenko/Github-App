package com.example.githubapp.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.githubapp.MainActivity
import com.example.githubapp.MyApplication
import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentLoginBinding
import com.example.githubapp.di.AppComponent
import com.example.githubapp.di.DaggerAppComponent
import javax.inject.Inject

class LoginFragment : Fragment() {
    @Inject
    lateinit var loginViewModel: LoginViewModel

    override fun onAttach(context: Context) {
        (context.applicationContext as MyApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.viewModel = loginViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

}