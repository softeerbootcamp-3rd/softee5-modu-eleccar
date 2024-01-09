package com.example.modueleccar.model

data class ReservationInfo(
    val startHour : Int,
    val chargerInfoId: Int,
    val chargeDuration : Int,
    val userId: Int
)
