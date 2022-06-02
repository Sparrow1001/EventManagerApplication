package com.example.eventmanagerapplication.model

import com.example.eventmanagerapplication.model.database.EventDatabase
import com.example.eventmanagerapplication.model.database.entity.EventDTO
import com.example.eventmanagerapplication.model.database.entity.MyEventDTO
import com.example.eventmanagerapplication.model.network.api.RetrofitInstance

class Repository(val db: EventDatabase) {
    suspend fun getEventsList() = RetrofitInstance.api.getEventList()

    suspend fun getEventDetails(id: Int) = RetrofitInstance.api.getEventDetails(id)

    suspend fun upsert(event: EventDTO) = db.getEventDao().saveEvent(event)

    fun getSavedEvents() = db.getEventDao().getSavedEvents()

    suspend fun myUpsert(event: MyEventDTO) = db.getMyEventDao().saveEvent(event)

    fun getMyEvents() = db.getMyEventDao().getSavedEvents()
}