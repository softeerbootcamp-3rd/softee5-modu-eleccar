package com.example.modueleccar.network

import com.example.modueleccar.data.ChargerList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("charger/list")
    suspend fun getChargerList(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): ChargerList

}
