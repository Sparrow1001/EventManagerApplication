package com.example.eventmanagerapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventmanagerapplication.model.Repository
import com.example.eventmanagerapplication.model.database.entity.EventDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(val eventRepository: Repository) : ViewModel() {

    var event: LiveData<List<EventDTO>> = MutableLiveData()

    fun getDataFromDB(){
        event = eventRepository.getSavedEvents()
    }

    fun deleteFavourite(event: EventDTO) = viewModelScope.launch(Dispatchers.IO) {
        eventRepository.deleteFavourite(event)
    }


}