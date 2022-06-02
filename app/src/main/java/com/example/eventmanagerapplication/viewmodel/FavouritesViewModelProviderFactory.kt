package com.example.eventmanagerapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eventmanagerapplication.model.Repository

class FavouritesViewModelProviderFactory (
    val eventRepository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoritesViewModel(eventRepository) as T
    }

}