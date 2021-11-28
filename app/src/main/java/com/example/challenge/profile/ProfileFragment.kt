package com.example.challenge.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.challenge.R
import com.example.challenge.base.ProfileViewModelFactory
import com.example.challenge.databinding.FragmentProfileBinding
import com.squareup.picasso.Picasso

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
            if (it.isEmpty())
                return@observe
            Picasso.get()
                .load(it[0].avatarUrl)
                .placeholder(R.drawable.ic_profile)
                .error(R.drawable.ic_profile)
                .into(binding!!.ivAvatar)
            binding!!.tvEmail.text = it[0].email
            binding!!.tvName.text = it[0].name
            binding!!.followerCount.text = it[0].followers.toString()
            binding!!.followingCount.text = it[0].following.toString()
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