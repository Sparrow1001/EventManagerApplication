package com.example.eventmanagerapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventmanagerapplication.model.Repository
import com.example.eventmanagerapplication.model.database.entity.EventDTO
import com.example.eventmanagerapplication.model.network.api.EventApiResponse
import com.example.eventmanagerapplication.model.network.api.EventDetailsApiResponse
import com.example.eventmanagerapplication.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class EventDetailViewModel(
    val eventRepository: Repository
) : ViewModel() {

    val eventDetails: MutableLiveData<Resource<EventDetailsApiResponse>> = MutableLiveData()

    fun getEventDetails(id: Int) = viewModelScope.launch {
        safeEventDetailsCall(id)
    }

    private suspend fun safeEventDetailsCall(id: Int) {
        eventDetails.postValue(Resource.Loading())
        try {

            val response = eventRepository.getEventDetails(id)
            eventDetails.postValue(handleAstroPictureResponse(response))

        } catch (t: Throwable) {
            when (t) {
                is IOException -> eventDetails.postValue(Resource.Error("Network Failure"))
                else -> eventDetails.postValue(Resource.Error("Conversion Error: ${t.message}"))
            }
        }
    }

    private fun handleAstroPictureResponse(response: Response<EventDetailsApiResponse>): Resource<EventDetailsApiResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveEvent(event: EventDTO) = viewModelScope.launch {
        eventRepository.upsert(event)
    }
}