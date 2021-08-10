package com.example.githubapp.repos

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.githubapp.MyApplication
import com.example.githubapp.databinding.FragmentReposBinding
import com.example.githubapp.models.Repos
import com.example.githubapp.viewmodels.ViewModelProvideFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
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
    var search: String = REPOS_ALL
    var job: Job? = null

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
        searchRepo(REPOS_ALL)

        binding.searchRepos.doOnTextChanged { text, start, before, count ->
            searchRepo(text.toString().trim())
        }

        return binding.root
    }

    private fun initRecycleView() {
        adapter = ReposRecycleView()
        binding.list.adapter = adapter
    }

    private fun searchRepo(search: String) {
        lifecycleScope.launchWhenStarted {
            viewModel.searchRepo(search).collect {
                adapter.submitData(lifecycle, it)
            }
        }
    }

}