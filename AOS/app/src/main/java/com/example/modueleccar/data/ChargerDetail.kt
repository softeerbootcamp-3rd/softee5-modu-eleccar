package com.example.modueleccar.data

import java.io.Serializable

data class ChargerDetail(
    val chargerInfoId: Int,
    val chargerType: String,
    val distance: Double,
    val endHour: Int,
    val housingType: String,
    val imageUrl: String,
    val installType: String,
    val pricePerHour: Int,
    val speed: Int,
    val startHour: Int,
    val useType: String,
    val address: String,
    val title: String
) : Serializable