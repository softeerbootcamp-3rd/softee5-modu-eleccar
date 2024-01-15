package com.example.modueleccar.repository

import com.example.modueleccar.data.ChargerUseInfoDto
import com.example.modueleccar.network.ApiService
import javax.inject.Inject

class ChargerUseInfoDtoRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getChargerUseInfoDto(userId: Int, eventId : Int): ChargerUseInfoDto {
        return apiService.getChargerInformation(userId, eventId)
    }
}