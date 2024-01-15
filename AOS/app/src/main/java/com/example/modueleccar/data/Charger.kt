package com.example.modueleccar.data

data class Charger(
    val chargerInfoId: Int,
    val distance: Double,
    val endHour: Int,
    val imageUrl: String,
    val pricePerHour: Int,
    val startHour: Int,
    val speed : Int,
    val latitude: Double,
    val longitude: Double,
    val title: String
)