package com.example.githubapp.ui.repos

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.databinding.RepoViewItemBinding
import com.example.githubapp.models.Repos


class RepoViewHolder(private val binding: RepoViewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private var repos: Repos? = null

    fun bind(repos: Repos?, clickListener: ReposListener?) {
        this.repos = repos
        val resources = itemView.resources
        binding.apply {
            if (repos != null) {
                this.repos = repos
                repoLanguage.text = resources.getString(R.string.language, repos.language)
                this.clickListener = clickListener
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

class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind() {

    }

    companion object {
        fun create(parent: ViewGroup): TextViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.repo_view_item_no_res, parent, false)
            return TextViewHolder(view)
        }
    }
}