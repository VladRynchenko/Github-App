package com.example.githubapp.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.githubapp.databinding.RepoViewItemBinding
import com.example.githubapp.models.Repos

class ReposRecycleView : ListAdapter<Repos, RepoViewHolder>(diffUtil) {
    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = RepoViewItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }
}

val diffUtil = object : DiffUtil.ItemCallback<Repos>() {
    override fun areItemsTheSame(oldItem: Repos, newItem: Repos): Boolean {
        return oldItem.full_name == newItem.full_name
    }

    override fun areContentsTheSame(oldItem: Repos, newItem: Repos): Boolean {
        return oldItem == newItem
    }
}
