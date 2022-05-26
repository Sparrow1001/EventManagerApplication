package com.example.eventmanagerapplication.model

import com.example.eventmanagerapplication.model.database.EventDatabase
import com.example.eventmanagerapplication.model.database.entity.EventDTO
import com.example.eventmanagerapplication.model.network.api.RetrofitInstance

class Repository(val db: EventDatabase) {
    suspend fun getEventsList() = RetrofitInstance.api.getEventList()

    suspend fun upsert(event: EventDTO) = db.getEventDao().saveEvent(event)

    fun getSavedEvents() = db.getEventDao().getSavedEvents()
}