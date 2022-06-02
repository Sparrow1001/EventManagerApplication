package com.example.eventmanagerapplication.model.network.api

data class EventDetailsApiResponse(
    val id: Int,
    val title: String,
    val description: String,
    val body_text: String,
    val age_restriction: String,
    val price: String,
    val images: List<Image>,
    val tags: List<String>
)