package com.example.githubapp.repos

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.githubapp.MyApplication
import com.example.githubapp.databinding.FragmentReposBinding
import com.example.githubapp.viewmodels.ViewModelProvideFactory
import javax.inject.Inject

const val REPOS_ALL = ""

class ReposFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvideFactory
    lateinit var viewModel: ReposViewModel
    lateinit var binding: FragmentReposBinding
    lateinit var adapter: ReposRecycleView

    override fun onAttach(context: Context) {
        (context.applicationContext as MyApplication).appComponent.userComponent().create(context)
            .inject(this)
        viewModel = ViewModelProvider(this, factory).get(ReposViewModel::class.java)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReposBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initRecycleView()

        binding.searchRepos.doOnTextChanged { text, start, before, count ->
            viewModel.searchRepo(text.toString().trim())
        }
        viewModel.data.observe(viewLifecycleOwner, {
            adapter.submitData(lifecycle, it)
        })


        return binding.root
    }

    private fun initRecycleView() {
        adapter = ReposRecycleView(ReposListener {
            findNavController().navigate(
                ReposFragmentDirections.actionReposFragmentToRepoFragment(
                    it.owner.login, it.name
                )
            )
        })
        binding.list.adapter = adapter
    }

}