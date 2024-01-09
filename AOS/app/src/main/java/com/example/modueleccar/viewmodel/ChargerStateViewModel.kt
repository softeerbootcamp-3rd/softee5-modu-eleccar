package com.example.modueleccar.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modueleccar.data.ChargerState
import com.example.modueleccar.model.ReservationInfo
import com.example.modueleccar.model.ReservationTime
import com.example.modueleccar.repository.ChargerStateRepository
import com.example.modueleccar.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChargerStateViewModel @Inject constructor(
    private val chargerStatRepository: ChargerStateRepository,
    private val eventRepository: EventRepository
) : ViewModel() {
    private val _chargerInfoId = MutableLiveData<Int?>(null)
    private val _reservationTimeState = MutableLiveData(ReservationTime(0, 0))
    private val _chargerState = MutableLiveData<ChargerState>()
    private val _eventId = MutableLiveData<Int?>(null)
    val reservationTimeState: LiveData<ReservationTime>
        get() = _reservationTimeState
    val chargerState: LiveData<ChargerState>
        get() = _chargerState

    val eventId: LiveData<Int?>
        get() = _eventId

    fun updateChargerInfoId(chargerInfoIdValue : Int) {
        _chargerInfoId.value = chargerInfoIdValue
    }
    fun updateStartTime(time: Int) {
        _reservationTimeState.value = reservationTimeState.value?.copy(startHour = time)
    }

    fun updateChargeTime(time: Int) {
        _reservationTimeState.value = reservationTimeState.value?.copy(chargeHour = time)
    }

    suspend fun updateEventId() {
        viewModelScope.launch {
            _eventId.value = eventRepository.getEventId(
                ReservationInfo(
                    _reservationTimeState.value!!.startHour,
                    _chargerInfoId.value!!,
                    _reservationTimeState.value!!.chargeHour,
                    2
                )
            )
        }
    }

    suspend fun getChargerState(chargerInfoId: Int) {
        viewModelScope.launch {
            _chargerState.value = chargerStatRepository.getChargerState(chargerInfoId)
            Log.d("_chargerState", chargerState.value.toString())
        }
    }


}