package com.example.eventmanagerapplication.model.network.api

import com.example.eventmanagerapplication.utils.EXPAND_REQUEST
import com.example.eventmanagerapplication.utils.FIELDS_REQUEST
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EventAPI {

    @GET("public-api/v1.2/events")
    suspend fun getEventList(
        @Query("expand") expand: String = EXPAND_REQUEST,
        @Query("fields") fields: String = FIELDS_REQUEST
    ): Response<EventApiResponse>

    @GET()
    suspend fun getEventDetails(

    )//: Response<List<>>

}