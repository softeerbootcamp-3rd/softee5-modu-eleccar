package com.example.modueleccar.data

data class ChargerUseInfoDto(
    val address: String,
    val expectedChargeAmount: Int,
    val message: String,
    val reservedEndHour: Int,
    val reservedStartHour: Int,
    val totalPrice: Int
)