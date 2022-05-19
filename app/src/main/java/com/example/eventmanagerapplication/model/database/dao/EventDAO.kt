package com.example.eventmanagerapplication.model.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.eventmanagerapplication.model.database.entity.EventDTO

@Dao
interface EventDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveEvent(event: EventDTO)

    @Query("SELECT * FROM events")
    fun getSavedEvents(): LiveData<List<EventDTO>>

    @Delete
    suspend fun deleteEvent(event: EventDTO)
}