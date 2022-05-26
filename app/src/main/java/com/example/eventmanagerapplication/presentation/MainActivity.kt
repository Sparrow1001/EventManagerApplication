package com.example.eventmanagerapplication.presentation

import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.eventmanagerapplication.R
import com.example.eventmanagerapplication.databinding.ActivityMainBinding
import com.example.eventmanagerapplication.model.Repository
import com.example.eventmanagerapplication.model.database.EventDatabase
import com.example.eventmanagerapplication.viewmodel.EventViewModelProviderFactory
import com.example.eventmanagerapplication.viewmodel.HomeViewModel

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository(EventDatabase(this))
        val viewModelProviderFactory = EventViewModelProviderFactory(application, repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[HomeViewModel::class.java]
    }




}