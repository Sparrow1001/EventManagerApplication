package com.example.eventmanagerapplication.model.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "events",
    indices = [Index(value = ["title"], unique = true)]
)
data class EventDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    //  val end_date: String,
    //  val end_time: String,
    val start_date: String?,
    //   val start_time: String,
    val imagesUrl: List<String>?,
    val title: String,
    val event_id: Int
) : Serializable