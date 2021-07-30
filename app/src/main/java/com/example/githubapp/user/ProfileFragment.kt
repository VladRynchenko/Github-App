package com.example.githubapp.user

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.githubapp.MyApplication
import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentProfileBinding
import javax.inject.Inject

class ProfileFragment() : Fragment() {
    @Inject
    lateinit var viewModel: ProfileViewModel

    override fun onAttach(context: Context) {
        (context.applicationContext as MyApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            FragmentProfileBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.getData("AlexGyver")

        return binding.root
    }

}