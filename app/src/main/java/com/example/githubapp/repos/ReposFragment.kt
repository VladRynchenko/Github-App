package com.example.githubapp.repos

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.githubapp.MyApplication
import com.example.githubapp.databinding.FragmentReposBinding
import com.example.githubapp.viewmodels.ViewModelProvideFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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
        viewModel.searchRepo(REPOS_ALL)

        binding.searchRepos.doOnTextChanged { text, start, before, count ->
                viewModel.searchRepo(text.toString().trim())
        }

        lifecycleScope.launchWhenStarted {
            viewModel.response?.collectLatest {
                adapter.submitData(lifecycle, it)
            }
        }

        return binding.root
    }

    private fun initRecycleView() {
        adapter = ReposRecycleView()
        binding.list.adapter = adapter
    }

}