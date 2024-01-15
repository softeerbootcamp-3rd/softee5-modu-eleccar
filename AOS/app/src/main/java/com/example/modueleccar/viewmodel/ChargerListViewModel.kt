package com.example.modueleccar.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modueleccar.data.Charger
import com.example.modueleccar.data.ChargerDetail
import com.example.modueleccar.data.ChargerList
import com.example.modueleccar.repository.ChargerDetailRepository
import com.example.modueleccar.repository.ChargerListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChargerListViewModel @Inject constructor(
    private val chargerListRepository: ChargerListRepository,
    private val chargerDetailRepository: ChargerDetailRepository
) : ViewModel() {

    private val _chargerList = MutableLiveData<ChargerList>()
    private var _availableChargeState = MutableLiveData(false)
    private val _selectedCharger = MutableLiveData<Charger>()
    val chargerList
        get() = _chargerList
    val availableChargerState
        get() = _availableChargeState
    val selectedCharger
        get() = _selectedCharger

    fun fetchData() {
        val latitude = 1.3
        val longitude = 1.4
        viewModelScope.launch {
            _chargerList
                .value = chargerListRepository.getChargerList(latitude, longitude)
            Log.d("chargerList", chargerList.value.toString())
        }
    }

    fun updateAvailableChargeState(state: Boolean) {
        _availableChargeState.postValue(state)
    }

    fun fetchSelectedCharger(charger: Charger ) {
        _selectedCharger.postValue(charger)
    }
    suspend fun getChargerDetail(chargerInfoId: Int, distance: Double): ChargerDetail {
        return chargerDetailRepository.getChargerDetail(chargerInfoId, distance)
    }
}