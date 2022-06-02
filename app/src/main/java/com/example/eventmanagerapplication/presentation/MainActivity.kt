package com.example.eventmanagerapplication.presentation

import android.os.Bundle
import androidx.navigation.ui.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.eventmanagerapplication.R
import com.example.eventmanagerapplication.databinding.ActivityMainBinding
import com.example.eventmanagerapplication.model.Repository
import com.example.eventmanagerapplication.model.database.EventDatabase
import com.example.eventmanagerapplication.viewmodel.EventDetailViewModel
import com.example.eventmanagerapplication.viewmodel.EventDetailsViewModelProviderFactory
import com.example.eventmanagerapplication.viewmodel.EventViewModelProviderFactory
import com.example.eventmanagerapplication.viewmodel.HomeViewModel

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    lateinit var homeViewModel: HomeViewModel
    lateinit var detailViewModel: EventDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.listNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)


        val repository = Repository(EventDatabase(this))
        val viewModelProviderFactory = EventViewModelProviderFactory(application, repository)
        val detailsViewModelProviderFactory = EventDetailsViewModelProviderFactory(repository)
        homeViewModel = ViewModelProvider(this, viewModelProviderFactory)[HomeViewModel::class.java]
        detailViewModel = ViewModelProvider(this, detailsViewModelProviderFactory)[EventDetailViewModel::class.java]





    }




}