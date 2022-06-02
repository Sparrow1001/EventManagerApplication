package com.example.eventmanagerapplication.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eventmanagerapplication.model.Repository

class EventDetailsViewModelProviderFactory (
    val eventRepository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EventDetailViewModel(eventRepository) as T
    }

}