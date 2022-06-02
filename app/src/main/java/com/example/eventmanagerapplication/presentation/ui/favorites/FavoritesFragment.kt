package com.example.eventmanagerapplication.presentation.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventmanagerapplication.R
import com.example.eventmanagerapplication.databinding.FragmentFavoritesBinding
import com.example.eventmanagerapplication.presentation.MainActivity
import com.example.eventmanagerapplication.presentation.adapters.FavouriteAdapter
import com.example.eventmanagerapplication.viewmodel.FavoritesViewModel

class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var favouriteAdapter: FavouriteAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val favouriteViewModel =
            (activity as MainActivity).favouritesViewModel
        favouriteAdapter = FavouriteAdapter()
        favouriteViewModel.getDataFromDB()

        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.listNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        with(binding.favorRv){
            adapter = favouriteAdapter
            layoutManager = LinearLayoutManager(context)
        }

        favouriteViewModel.event.observe(viewLifecycleOwner, Observer { response ->
            favouriteAdapter.differ.submitList(response)
        })

        favouriteAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("event", it)
            }
            navController.navigate(
                R.id.action_favorites_fragment_to_eventDetailsFragment,
                bundle
            )
        }

        return binding.root
    }




}