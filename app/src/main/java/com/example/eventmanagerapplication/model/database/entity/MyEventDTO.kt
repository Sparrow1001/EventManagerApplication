package com.example.eventmanagerapplication.model.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "my_events",
    indices = [Index(value = ["title"], unique = true)]
)
data class MyEventDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val start_date: String?,
    val imagesUrl: List<String>?,
    val title: String,
    val event_id: Int
) : Serializable