package com.example.modueleccar.repository

import com.example.modueleccar.data.ChargerList
import com.example.modueleccar.network.ApiService
import javax.inject.Inject

class ChargerListRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getChargerList(latitude: Double, longitude: Double): ChargerList {
        return apiService.getChargerList(latitude, longitude)
    }
}