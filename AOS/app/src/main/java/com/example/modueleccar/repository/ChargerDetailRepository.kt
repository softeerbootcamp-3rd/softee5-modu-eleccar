package com.example.modueleccar.repository

import androidx.lifecycle.ViewModel
import com.example.modueleccar.data.ChargerDetail
import com.example.modueleccar.network.ApiService
import javax.inject.Inject

class ChargerDetailRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getChargerDetail(chargerInfoId: Int, distance: Double): ChargerDetail {
        return apiService.getChargerDetail(chargerInfoId, distance)
    }
}