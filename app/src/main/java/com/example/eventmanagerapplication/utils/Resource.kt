package com.example.eventmanagerapplication.utils

import androidx.lifecycle.LiveData

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val liveData: LiveData<T>? = null
) {

    class Success<T>(data: T) : Resource<T>(data)
    class NoInternet<T>(liveData: LiveData<T>? = null) : Resource<T>(null, null, liveData)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()

}