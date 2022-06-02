package com.example.eventmanagerapplication.model.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.eventmanagerapplication.model.database.entity.MyEventDTO

@Dao
interface MyEventDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveEvent(event: MyEventDTO)

    @Query("SELECT * FROM my_events")
    fun getSavedEvents(): LiveData<List<MyEventDTO>>

    @Delete
    suspend fun deleteEvent(event: MyEventDTO)

}