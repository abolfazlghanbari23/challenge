package com.example.challenge.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.challenge.MainActivity
import com.example.challenge.R
import com.example.challenge.base.GitHubRepoViewModelFactory
import com.example.challenge.base.ProfileViewModelFactory

class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ProfileViewModelFactory(requireActivity().application)
        ).get(ProfileViewModel::class.java)

        viewModel.getUser()

        viewModel.getUserCache().observe(viewLifecycleOwner, {

        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, {

        })
    }

}