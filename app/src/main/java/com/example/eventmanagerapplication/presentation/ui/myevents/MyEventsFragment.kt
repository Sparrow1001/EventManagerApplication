package com.example.eventmanagerapplication.presentation.ui.myevents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventmanagerapplication.databinding.FragmentMyEventsBinding
import com.example.eventmanagerapplication.presentation.MainActivity
import com.example.eventmanagerapplication.presentation.adapters.MyEventsAdapter
import com.example.eventmanagerapplication.viewmodel.MyEventsViewModel

class MyEventsFragment : Fragment() {

    private lateinit var binding: FragmentMyEventsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyEventsBinding.inflate(inflater, container, false)
        val viewModel = (activity as MainActivity).myEventsViewModel
        val myEventsAdapter = MyEventsAdapter()

        viewModel.getEventsFromDB()

        with(binding.myEventsRv){
            adapter = myEventsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.myEvent.observe(viewLifecycleOwner, Observer { response ->

            myEventsAdapter.differ.submitList(response)

        })


        return binding.root
    }


}