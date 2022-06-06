package com.example.eventmanagerapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventmanagerapplication.model.Repository
import com.example.eventmanagerapplication.model.database.entity.EventDTO
import com.example.eventmanagerapplication.model.database.entity.MyEventDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class MyEventsViewModel(val eventRepository: Repository) : ViewModel() {

    var myEvent: LiveData<List<MyEventDTO>> = MutableLiveData()

    fun getEventsFromDB() = viewModelScope.launch {
        myEvent = eventRepository.getMyEvents()
    }

    fun deleteMyEvent(event: MyEventDTO) = viewModelScope.launch(Dispatchers.IO) {
        eventRepository.deleteMyEvent(event)
    }


}