package com.example.githubapp.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.githubapp.MyApplication
import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentProfileBinding
import com.example.githubapp.viewmodels.ViewModelProvideFactory
import javax.inject.Inject

class ProfileFragment() : Fragment() {
    @Inject
    lateinit var providerFactory: ViewModelProvideFactory
    lateinit var viewModel: ProfileViewModel

    override fun onAttach(context: Context) {
        (context.applicationContext as MyApplication).appComponent
            .userComponent().create(context)
            .inject(this)
        viewModel = ViewModelProvider(this, providerFactory).get(ProfileViewModel::class.java)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding =
            FragmentProfileBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.getData()

        binding.buttonLeft.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToReposFragment())
        }

        viewModel.navigatingToLogin.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.loginFragment)
        })

        return binding.root
    }

}