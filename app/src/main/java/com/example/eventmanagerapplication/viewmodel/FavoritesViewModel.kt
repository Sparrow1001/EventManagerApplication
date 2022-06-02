package com.example.eventmanagerapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eventmanagerapplication.model.Repository
import com.example.eventmanagerapplication.model.database.entity.EventDTO

class FavoritesViewModel(val eventRepository: Repository) : ViewModel() {

    var event: LiveData<List<EventDTO>> = MutableLiveData()

    fun getDataFromDB(){
        event = eventRepository.getSavedEvents()
    }
}