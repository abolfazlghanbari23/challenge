package com.example.challenge.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge.R
import com.example.challenge.databinding.ItemGitHubRepoBinding
import com.example.challenge.response.GitHubRepo

class GitHubRepoAdapter(private val callBack: CallBack? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<GitHubRepo>() {

        override fun areItemsTheSame(oldItem: GitHubRepo, newItem: GitHubRepo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GitHubRepo, newItem: GitHubRepo): Boolean {
            return newItem.equals(oldItem)
        }

    }
    private val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding: ItemGitHubRepoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_git_hub_repo, parent, false
        )
        return RepositoryViewHolder(binding, callBack)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RepositoryViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<GitHubRepo>) {
        differ.submitList(list)
    }

    class RepositoryViewHolder
    constructor(
        private val binding: ItemGitHubRepoBinding,
        private val callBack: CallBack?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(gitHubRepo: GitHubRepo) {
            binding.listener = callBack
            binding.repository = gitHubRepo
            binding.executePendingBindings()
        }
    }

    interface CallBack {
        fun onItemClicked(gitHubRepo: GitHubRepo)
    }
}

