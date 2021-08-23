package com.example.githubapp.ui.repo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.githubapp.MyApplication
import com.example.githubapp.databinding.FragmentRepoBinding
import com.example.githubapp.viewmodelsfactory.ViewModelProvideFactory
import javax.inject.Inject

class DetailFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvideFactory
    lateinit var viewModel: DetailViewModel
    private val args: DetailFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        (context.applicationContext as MyApplication).appComponent.userComponent().create(context)
            .inject(this)
        viewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRepoBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        args.let {
            viewModel.getRepo(args.owner, args.name)
        }

        return binding.root
    }


}