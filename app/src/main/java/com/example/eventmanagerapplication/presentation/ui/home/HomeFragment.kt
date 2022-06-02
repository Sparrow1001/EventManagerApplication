package com.example.eventmanagerapplication.presentation.ui.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventmanagerapplication.R
import com.example.eventmanagerapplication.databinding.FragmentHomeBinding
import com.example.eventmanagerapplication.model.mappers.EventResponseMapper
import com.example.eventmanagerapplication.presentation.MainActivity
import com.example.eventmanagerapplication.presentation.adapters.HomeAdapter
import com.example.eventmanagerapplication.utils.Resource

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            (activity as MainActivity).homeViewModel

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val mapper = EventResponseMapper()
        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.listNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        homeAdapter = HomeAdapter()
        homeAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("event", it)
            }
            navController.navigate(
                R.id.action_home_fragment_to_eventDetailsFragment,
                bundle
            )
        }

        setupRecyclerView()

        homeViewModel.events.observe(viewLifecycleOwner, Observer { response ->
            when (response){
                is Resource.Success -> {
                    response.data.let { eventsResponse ->
                        homeAdapter.differ.submitList(eventsResponse?.let { mapper.toEventDTO(it) })
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG).show()
                    }
                }

                is Resource.Loading -> {
                }
            }
        })

        return root
    }


    private fun setupRecyclerView() {
        binding.homeRecyclerView.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}