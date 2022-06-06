package com.example.eventmanagerapplication.model.network.api

import java.io.Serializable

data class EventApiResponse(

    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
    ): Serializable


    data class Result(
        val dates: List<Date>,
        val id: Int,
        val images: List<Image>,
        val title: String
    )

    data class Date(
        val start_date: String,
    )

    data class Image(
        val image: String
    )

