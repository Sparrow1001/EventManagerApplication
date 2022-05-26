package com.example.eventmanagerapplication.model.network.api

data class EventApiResponse(

    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>)


    data class Result(
        val dates: List<Date>,
        val id: Int,
        val images: List<Image>,
        val title: String
    )

    data class Date(
     //   val end_date: String,
     //   val end_time: String,
        val start_date: String,
     //   val start_time: String
    )

    data class Image(
        val image: String
    )

