package com.example.eventmanagerapplication.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.eventmanagerapplication.EventApplication
import com.example.eventmanagerapplication.model.Repository
import com.example.eventmanagerapplication.model.database.entity.EventDTO
import com.example.eventmanagerapplication.model.network.api.EventApiResponse
import com.example.eventmanagerapplication.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class HomeViewModel(
    app: Application,
    val eventRepository: Repository
) : AndroidViewModel(app) {

    val events: MutableLiveData<Resource<EventApiResponse>> = MutableLiveData()

    init {
        getEventList()
    }

    fun getEventList() = viewModelScope.launch {
        safeEventListCall()
    }

    private suspend fun safeEventListCall() {
        events.postValue(Resource.Loading())
        try {

            val response = eventRepository.getEventsList()
            events.postValue(handleAstroPictureResponse(response))

        } catch (t: Throwable) {
            when (t) {
                is IOException -> events.postValue(Resource.Error("Network Failure"))
                else -> events.postValue(Resource.Error("Conversion Error: ${t.message}"))
            }
        }
    }

    private fun handleAstroPictureResponse(response: Response<EventApiResponse>): Resource<EventApiResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun savePicture(picture: EventDTO) = viewModelScope.launch {
        eventRepository.upsert(picture)
    }

    fun getSavedPictures() = eventRepository.getSavedEvents()


}