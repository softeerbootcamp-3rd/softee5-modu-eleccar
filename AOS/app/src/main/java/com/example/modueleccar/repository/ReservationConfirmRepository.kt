package com.example.modueleccar.repository

import com.example.modueleccar.network.ApiService
import javax.inject.Inject

class ReservationConfirmRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun postReservationConfirm(accept: Boolean, reservationId: Int, userId: Int): Boolean {
        return apiService.postReservationConfirm(accept, reservationId, userId)
    }
}