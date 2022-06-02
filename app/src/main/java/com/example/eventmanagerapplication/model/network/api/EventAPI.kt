package com.example.eventmanagerapplication.model.network.api

import com.example.eventmanagerapplication.utils.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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
        @Query("order_by") order_by: String = ORDER_BY_REQUEST,
        @Query("page_size") page_size: Int = PAGE_SIZE_REQUEST,
        @Query("categories") categories: String = CATEGORIES_REQUEST,
        @Query("actual_since") actual_since: String = DateTimeFormatter.ISO_INSTANT.format(Instant.now())
    ): Response<EventApiResponse>

    @GET("public-api/v1.3/events/{id}")
    suspend fun getEventDetails(
        @Path("id") id: Int
    ): Response<EventDetailsApiResponse>

}