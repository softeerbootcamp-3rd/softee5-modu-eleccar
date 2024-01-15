package com.example.modueleccar.repository

import com.example.modueleccar.data.ChargerRequestStateDto
import com.example.modueleccar.network.ApiService
import javax.inject.Inject

class RequestStateRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getRequestState(eventId: Int): ChargerRequestStateDto {
        return apiService.getRequestState(eventId)
    }
}