package com.example.modueleccar.network

import com.example.modueleccar.data.ChargerDetail
import com.example.modueleccar.data.ChargerList
import com.example.modueleccar.data.ChargerState
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("charger/list")
    suspend fun getChargerList(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): ChargerList

    @GET("charger/detail")
    suspend fun getChargerDetail(
        @Query("chargerInfoId") chargerInfoId: Int,
        @Query("distance") distance: Double
    ): ChargerDetail

    @GET("charger/state")
    suspend fun getChargerState(
        @Query("chargerInfoId") chargerInfoId: Int
    ): ChargerState

    @POST("charger/reservation")
    suspend fun postReservation(
        @Query("startHour") startHour: Int,
        @Query("chargerInfoId") chargerInfoId: Int,
        @Query("chargeDuration") chargeDuration: Int,
        @Query("userId") userId: Int,

    ): Int

}
