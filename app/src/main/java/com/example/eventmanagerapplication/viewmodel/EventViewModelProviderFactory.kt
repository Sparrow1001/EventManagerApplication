package com.example.eventmanagerapplication.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eventmanagerapplication.model.Repository

class EventViewModelProviderFactory(
    val app: Application,
    val eventRepository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(app, eventRepository) as T
    }

}