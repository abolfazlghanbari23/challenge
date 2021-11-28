package com.example.challenge.githubrepo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.challenge.R
import com.example.challenge.adapters.GitHubRepoAdapter
import com.example.challenge.base.GitHubRepoViewModelFactory
import com.example.challenge.response.GitHubRepo

class GitHubRepoFragment : Fragment() {


    private lateinit var viewModel: GitHubRepoViewModel
    private lateinit var repoAdapter: GitHubRepoAdapter
    private var repoList = emptyList<GitHubRepo>()
    private var index = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_git_hub_repo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            GitHubRepoViewModelFactory(requireActivity().application)
        ).get(GitHubRepoViewModel::class.java)
        repoAdapter = GitHubRepoAdapter(object : GitHubRepoAdapter.CallBack {
            override fun onItemClicked(gitHubRepo: GitHubRepo) {
                Toast.makeText(context, "id: ${gitHubRepo.id}", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.getRepos()

        viewModel.getReposCache().observe(viewLifecycleOwner, {
            repoAdapter.submitList(it)
        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, {

        })
    }

}