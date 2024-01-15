package com.example.modueleccar.repository

import com.example.modueleccar.model.ReservationInfo
import com.example.modueleccar.network.ApiService
import javax.inject.Inject


class EventRepository @Inject constructor(
    private val apiService: ApiService
){
    suspend fun postReservation(reservationInfo: ReservationInfo): Int{
        return apiService.postReservation(
            reservationInfo.startHour,
            reservationInfo.chargerInfoId,
            reservationInfo.chargeDuration,
            reservationInfo.userId,
        )
    }
}