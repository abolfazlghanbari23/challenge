package com.example.challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.apollographql.apollo.coroutines.await
import com.example.challenge.databinding.ActivityMainBinding
import com.example.challenge.repository.Apollo

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setupNavigation()


        val apolloClient = Apollo.instance
        lifecycleScope.launchWhenResumed {
            val response = apolloClient!!.query(GithubApiQuery()).await()
            Log.d("LaunchList", "Success ${response.data}")
        }
    }

    private fun setupNavigation() {
        binding.bottomNavigation.selectedItemId = R.id.profileFragment
        navController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

        //To prevent reload currently selected fragment
        binding.bottomNavigation.setOnNavigationItemReselectedListener { }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            navController
        ) || super.onOptionsItemSelected(item)
    }


}