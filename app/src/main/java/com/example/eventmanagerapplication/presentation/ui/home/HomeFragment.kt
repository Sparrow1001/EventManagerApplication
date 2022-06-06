package com.example.eventmanagerapplication.presentation.ui.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventmanagerapplication.R
import com.example.eventmanagerapplication.databinding.FragmentHomeBinding
import com.example.eventmanagerapplication.model.mappers.EventResponseMapper
import com.example.eventmanagerapplication.presentation.MainActivity
import com.example.eventmanagerapplication.presentation.adapters.HomeAdapter
import com.example.eventmanagerapplication.utils.Resource
import com.example.eventmanagerapplication.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var homeViewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            (activity as MainActivity).homeViewModel

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val mapper = EventResponseMapper()
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.listNavHostFragment) as NavHostFragment
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
        setFilters()

        homeViewModel.events.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data.let { eventsResponse ->
                        homeAdapter.differ.submitList(eventsResponse?.let { mapper.toEventDTO(it) })
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
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

    private fun showProgressBar() {
        binding.progressBar.visibility = ProgressBar.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = ProgressBar.INVISIBLE
    }

    private fun setFilters(){

        binding.cinemaChip.setOnClickListener {
            homeViewModel.getEventList("cinema")
        }

        binding.concertChip.setOnClickListener {
            homeViewModel.getEventList("concert")
        }

        binding.exhibitionChip.setOnClickListener {
            homeViewModel.getEventList("exhibition")
        }

        binding.excursionsChip.setOnClickListener {
            homeViewModel.getEventList("tour")
        }

        binding.festChip.setOnClickListener {
            homeViewModel.getEventList("festival")
        }

        binding.holidayChip.setOnClickListener {
            homeViewModel.getEventList("holiday")
        }

    }


}