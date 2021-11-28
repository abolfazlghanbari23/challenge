package com.example.challenge.githubrepo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge.R
import com.example.challenge.adapters.GitHubRepoAdapter
import com.example.challenge.base.GitHubRepoViewModelFactory
import com.example.challenge.databinding.FragmentGitHubRepoBinding
import com.example.challenge.response.GitHubRepo

class GitHubRepoFragment : Fragment() {


    private lateinit var viewModel: GitHubRepoViewModel
    private lateinit var repoAdapter: GitHubRepoAdapter
    private var index = 0
    private var isListFinished = false
    private var endOfListReached = false
    private var reposList = emptyList<GitHubRepo>()
    private lateinit var binding: FragmentGitHubRepoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_git_hub_repo, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            GitHubRepoViewModelFactory(requireActivity().application)
        ).get(GitHubRepoViewModel::class.java)

        initRecyclerView()


        viewModel.getRepos()

        viewModel.getReposCache().observe(viewLifecycleOwner, {
            repoAdapter.submitList(it)
        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, {

        })

        viewModel.progressBarLiveData.observe(viewLifecycleOwner, {
            if (it) {
                binding.flLoading.visibility = View.VISIBLE
            } else {
                binding.flLoading.visibility = View.GONE
            }
        })
    }

    private fun initRecyclerView() {
        repoAdapter = GitHubRepoAdapter(object : GitHubRepoAdapter.CallBack {
            override fun onItemClicked(gitHubRepo: GitHubRepo) {
                Toast.makeText(context, "id: ${gitHubRepo.id}", Toast.LENGTH_SHORT).show()
            }
        })

        binding.rvRepos.layoutManager = LinearLayoutManager(context)
        binding.rvRepos.adapter = repoAdapter

        binding.rvRepos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isLoadingAllowed() && isBottomOfListReached()) {
                    loadMoreFromList()
                }

            }
        })

    }

    private fun isLoadingAllowed(): Boolean {
        return (!isListFinished
                && viewModel.progressBarLiveData
            .value != null && !viewModel.progressBarLiveData.value!!)
    }

    private fun isBottomOfListReached(): Boolean {
        val linearLayoutManager = binding.rvRepos.layoutManager as LinearLayoutManager
        return (linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1) == viewModel.getReposCache().value?.size
    }

    private fun loadMoreFromList() {
        viewModel.getRepos()
    }


}