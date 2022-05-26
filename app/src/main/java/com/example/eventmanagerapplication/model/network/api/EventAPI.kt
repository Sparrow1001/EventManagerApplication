package com.example.eventmanagerapplication.model.network.api

import com.example.eventmanagerapplication.utils.EXPAND_REQUEST
import com.example.eventmanagerapplication.utils.FIELDS_REQUEST
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.Date

interface EventAPI {

    @GET("public-api/v1.2/events")
    suspend fun getEventList(
        @Query("expand") expand: String = EXPAND_REQUEST,
        @Query("fields") fields: String = FIELDS_REQUEST,
        @Query("order_by") order_by: String = "-publication_date",
        @Query("page_size") page_size: Int = 50,
        @Query("categories") categories: String = "party,entertainment,cinema,concert",
        @Query("actual_since") actual_since: String = DateTimeFormatter.ISO_INSTANT.format(Instant.now())
    ): Response<EventApiResponse>

    @GET()
    suspend fun getEventDetails(

    )//: Response<List<>>

}