package com.example.modueleccar.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modueleccar.data.ChargerState
import com.example.modueleccar.data.ChargerUseInfoDto
import com.example.modueleccar.model.ReservationInfo
import com.example.modueleccar.model.ReservationTime
import com.example.modueleccar.repository.ChargerStateRepository
import com.example.modueleccar.repository.ChargerUseInfoDtoRepository
import com.example.modueleccar.repository.EventRepository
import com.example.modueleccar.repository.RequestStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

@HiltViewModel
class ChargerStateViewModel @Inject constructor(
    private val chargerStatRepository: ChargerStateRepository,
    private val eventRepository: EventRepository,
    private val requestStateRepository: RequestStateRepository,
    private val chargerUseInfoDtoRepository : ChargerUseInfoDtoRepository
) : ViewModel() {
    private val _chargerInfoId = MutableLiveData<Int?>(null)
    private val _reservationTimeState = MutableLiveData(ReservationTime(0, 0))
    private val _chargerState = MutableLiveData<ChargerState>()
    private val _eventId = MutableLiveData<Int?>(null)
    private val _isAccepted = MutableLiveData(false)
    private val _chargerUseInfo = MutableLiveData<ChargerUseInfoDto>()
    private val _payment_type = MutableLiveData(0)
    val reservationTimeState: LiveData<ReservationTime>
        get() = _reservationTimeState
    val chargerState: LiveData<ChargerState>
        get() = _chargerState
    val eventId: LiveData<Int?>
        get() = _eventId
    val isAccepted: LiveData<Boolean>
        get() = _isAccepted

    val chargerUseInfo: LiveData<ChargerUseInfoDto>
        get() = _chargerUseInfo
    fun registerPeriodicJob() {
        Log.d("init", "Start")
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                CoroutineScope(Dispatchers.IO).launch {
                    if (eventId.value != null) {
                        val result = requestStateRepository.getRequestState(eventId.value!!)
                        Log.d("result", result.chargerRequestState)
                        _isAccepted.postValue(result.chargerRequestState == "ACCEPTED")
                    } else Log.d("chargerstateviewmodel result", "NULL")
                }
            }
        }, 0, 1000)
    }

    fun updatePaymentType(type: Int) {
        _payment_type.postValue(type)
    }

    fun updateChargerInfoId(chargerInfoIdValue: Int) {
        _chargerInfoId.value = chargerInfoIdValue
    }

    fun updateStartTime(time: Int) {
        _reservationTimeState.value = reservationTimeState.value?.copy(startHour = time)
    }

    fun updateChargeTime(time: Int) {
        _reservationTimeState.value = reservationTimeState.value?.copy(chargeHour = time)
    }
    suspend fun updateChargerUseInfo() {
        viewModelScope.launch {
            _chargerUseInfo.value =
            chargerUseInfoDtoRepository.getChargerUseInfoDto(
                2,
                eventId.value!!
            )
        }
    }
    suspend fun updateEventId() {
        Log.d(
            "_reservationTimeState.value!!.startHour",
            _reservationTimeState.value!!.startHour.toString()
        )
        Log.d(" _chargerInfoId.value!!", _chargerInfoId.value!!.toString())
        Log.d(
            "_reservationTimeState.value!!.chargeHour",
            _reservationTimeState.value!!.chargeHour.toString()
        )
        viewModelScope.launch {
            _eventId.value = eventRepository.postReservation(
                ReservationInfo(
                    _reservationTimeState.value!!.startHour,
                    _chargerInfoId.value!!,
                    _reservationTimeState.value!!.chargeHour,
                    2   //구매자
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