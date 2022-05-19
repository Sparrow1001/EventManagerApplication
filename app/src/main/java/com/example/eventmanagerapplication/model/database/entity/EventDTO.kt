package com.example.eventmanagerapplication.model.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventDTO (
    @PrimaryKey
    val id: Int?,
    val end_date: String,
    val end_time: String,
    val start_date: String,
    val start_time: String,
    val imagesUrl: List<String>?,
    val title: String,
    val event_id: Int
)