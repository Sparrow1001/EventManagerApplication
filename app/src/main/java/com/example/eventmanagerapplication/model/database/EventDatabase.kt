package com.example.eventmanagerapplication.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.eventmanagerapplication.model.database.dao.EventDAO
import com.example.eventmanagerapplication.model.database.dao.MyEventDAO
import com.example.eventmanagerapplication.model.database.entity.EventDTO
import com.example.eventmanagerapplication.model.database.entity.MyEventDTO

@Database(
    entities = [EventDTO::class, MyEventDTO::class],
    version = 2
)
@TypeConverters(Converters::class)
abstract class EventDatabase : RoomDatabase() {
    abstract fun getEventDao(): EventDAO
    abstract fun getMyEventDao(): MyEventDAO

    companion object{
        @Volatile
        private var instance: EventDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                EventDatabase::class.java,
                "event_database.db"
            ).build()

    }
}