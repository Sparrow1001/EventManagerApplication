package com.example.eventmanagerapplication.model.database

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromImagesList(value: List<String>): String {
        return value.joinToString { "," }
    }

    @TypeConverter
    fun toImagesList(value: String): List<String> {
        return value.split(",")
    }
}