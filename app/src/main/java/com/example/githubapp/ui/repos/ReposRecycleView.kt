package com.example.githubapp.ui.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.databinding.RepoViewItemBinding
import com.example.githubapp.models.DataItem
import com.example.githubapp.models.NoResult
import com.example.githubapp.models.Repos

class ReposRecycleView(val clickListener: ReposListener) :
    PagingDataAdapter<DataItem, RecyclerView.ViewHolder>(diffUtil) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        item.let {
            when (item) {
                is Repos -> (holder as RepoViewHolder).bind(item, clickListener)
                is NoResult -> (holder as TextViewHolder).bind()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == R.layout.repo_view_item) {
            val binding = RepoViewItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            RepoViewHolder(binding)
        } else {
            TextViewHolder.create(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is NoResult -> R.layout.repo_view_item_no_res
            is Repos -> R.layout.repo_view_item
            else -> throw UnsupportedOperationException("Unknown view")
        }
    }
}

val diffUtil = object : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.full_name == newItem.full_name
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}

class ReposListener(val clickListener: (repoId: Repos) -> Unit) {
    fun onClick(repo: Repos) = clickListener(repo)
}
