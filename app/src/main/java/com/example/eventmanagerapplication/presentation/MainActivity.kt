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
import com.example.eventmanagerapplication.viewmodel.*

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    lateinit var homeViewModel: HomeViewModel
    lateinit var detailViewModel: EventDetailViewModel
    lateinit var favouritesViewModel: FavoritesViewModel
    lateinit var myEventsViewModel: MyEventsViewModel

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
        val favoritesViewModelProviderFactory = FavouritesViewModelProviderFactory(repository)
        val myEventsViewModelProviderFactory = MyEventsViewModelProviderFactory(repository)

        homeViewModel = ViewModelProvider(this, viewModelProviderFactory)[HomeViewModel::class.java]
        detailViewModel = ViewModelProvider(this, detailsViewModelProviderFactory)[EventDetailViewModel::class.java]
        favouritesViewModel = ViewModelProvider(this, favoritesViewModelProviderFactory)[FavoritesViewModel::class.java]
        myEventsViewModel = ViewModelProvider(this, myEventsViewModelProviderFactory)[MyEventsViewModel::class.java]





    }




}