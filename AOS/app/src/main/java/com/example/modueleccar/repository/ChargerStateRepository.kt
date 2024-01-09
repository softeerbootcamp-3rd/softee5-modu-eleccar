package com.example.modueleccar.repository

import com.example.modueleccar.data.ChargerState
import com.example.modueleccar.network.ApiService
import javax.inject.Inject

class ChargerStateRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getChargerState(chargerInfoId: Int): ChargerState {
        return apiService.getChargerState(chargerInfoId)
    }
}