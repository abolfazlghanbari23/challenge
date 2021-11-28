package com.example.challenge.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.challenge.MainActivity
import com.example.challenge.R
import com.example.challenge.base.GitHubRepoViewModelFactory
import com.example.challenge.base.ProfileViewModelFactory
import com.example.challenge.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel
    private var binding: FragmentProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        }
        return binding?.root

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

        viewModel.progressBarLiveData.observe(viewLifecycleOwner, {
            if (it) {
                binding?.flLoading?.visibility = View.VISIBLE
            } else {
                binding?.flLoading?.visibility = View.GONE
            }
        })
    }

}