package com.example.githubapp.repos

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.databinding.RepoViewItemBinding
import com.example.githubapp.models.Repos

class RepoViewHolder(private val binding: RepoViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private var repos: Repos? = null

    init {
        binding.root.setOnClickListener {
            repos?.url?.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                binding.root.context.startActivity(intent)
            }
        }
    }

    fun bind(repos: Repos?) {

        this.repos = repos
        val resources = itemView.resources
        binding.apply {
            if (repos != null) {
                this.repos = repos
                repoLanguage.text = resources.getString(R.string.language, repos.language)
            } else {
                repoName.text = resources.getString(R.string.loading)
                repoDescription.visibility = View.GONE
                repoLanguage.visibility = View.GONE
                repoStars.text = resources.getString(R.string.unknown)
                repoForks.text = resources.getString(R.string.unknown)
            }
        }

    }


}