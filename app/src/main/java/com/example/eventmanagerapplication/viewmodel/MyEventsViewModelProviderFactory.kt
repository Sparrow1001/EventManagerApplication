package com.example.eventmanagerapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eventmanagerapplication.model.Repository

class MyEventsViewModelProviderFactory (
    val eventRepository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyEventsViewModel(eventRepository) as T
    }

}