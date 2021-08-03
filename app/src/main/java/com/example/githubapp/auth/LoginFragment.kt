package com.example.githubapp.auth

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.githubapp.MyApplication
import com.example.githubapp.api.authPath
import com.example.githubapp.databinding.FragmentLoginBinding
import com.example.githubapp.di.ViewModelProvideFactory
import com.example.githubapp.login.CLIENT_ID
import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvideFactory
    lateinit var viewModel: LoginViewModel

    override fun onAttach(context: Context) {
        val loginComponent =
            (context.applicationContext as MyApplication).appComponent.loginComponent()
                .create(context)
        loginComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        val token = viewModel.getSavedToken()
        if (token != null)
            Log.e(LoginViewModel::class.simpleName, token.accessToken)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.loginButton.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("$authPath?client_id=$CLIENT_ID")
            )
            startActivity(intent)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val uri = activity?.intent?.data
        if (uri != null && uri.toString().startsWith("gitapp://callback")) {
            uri.getQueryParameter("code")?.let { viewModel.getAccessToken(it) }
        }
    }
}