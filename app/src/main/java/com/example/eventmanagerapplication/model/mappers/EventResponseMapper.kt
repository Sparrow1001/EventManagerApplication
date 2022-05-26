package com.example.eventmanagerapplication.model.mappers

import com.example.eventmanagerapplication.model.database.entity.EventDTO
import com.example.eventmanagerapplication.model.network.api.EventApiResponse

class EventResponseMapper {
    fun toEventDTO(response: EventApiResponse): List<EventDTO>{
        return response.results.map { it ->
            EventDTO(
                id = null,
             //   it.dates[0].end_date,
             //   it.dates[1].end_time,
                it.dates[0].start_date,
              //  it.dates[3].start_time,
                it.images.map {
                    it.image
                },
                it.title,
                it.id
            )
        }
    }
}