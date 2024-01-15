package com.example.modueleccar.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modueleccar.data.ChargerUseInfoDto
import com.example.modueleccar.repository.ChargerUseInfoDtoRepository
import com.example.modueleccar.repository.ReservationConfirmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SellerViewModel @Inject constructor(
    private val reservationConfirmRepository: ReservationConfirmRepository,
    private val chargerUseInfoDtoRepository: ChargerUseInfoDtoRepository
) : ViewModel() {
    // 판매자 -> 1, 구매자 -> 2
    private var _firstCondition = MutableLiveData<Boolean?>(null)
    private var _secondCondition = MutableLiveData<Boolean?>(null)
    private var _finalCondition = MutableLiveData<Pair<Boolean, Boolean>>(Pair(false, false))

    private var _isAccepted = MutableLiveData(false)
    private var _eventId = MutableLiveData<Int?>(null)
    private var _chargerInfo = MutableLiveData<ChargerUseInfoDto>()

    val firstCondition: LiveData<Boolean?>
        get() = _firstCondition
    val secondCondition: LiveData<Boolean?>
        get() = _secondCondition
    val finalCondition: LiveData<Pair<Boolean, Boolean>>
        get() = _finalCondition
    val isAccepted: LiveData<Boolean>
        get() = _isAccepted
    val eventId: LiveData<Int?>
        get() = _eventId
    val chargerInfo: LiveData<ChargerUseInfoDto>
        get() = _chargerInfo

    fun updateFirstCondition(condition : Boolean) {
       _finalCondition.postValue(finalCondition.value?.copy(first = condition))
    }

    fun updateSecondCondition(condition: Boolean) {
        _finalCondition.postValue(finalCondition.value?.copy(second = condition))
    }
    fun updateIsAccepted(acceptValue: Boolean) {
        _isAccepted.value = acceptValue
    }

    fun updateEventId(eventIdValue: Int) {
        _eventId.value = eventIdValue
    }

    suspend fun postReservationConfirm() {
        if (isAccepted.value != null && eventId.value != null) {
            reservationConfirmRepository.postReservationConfirm(
                isAccepted.value!!,
                eventId.value!!,
                1 // 판매자
            )
        }
        else Log.d("null pass", "TRUE")
    }

    suspend fun getChargerUseInfo() {
        if (eventId.value != null) {
            viewModelScope.launch {
                _chargerInfo.value =
                    chargerUseInfoDtoRepository.getChargerUseInfoDto(1, eventId.value!!)
            }
        }
    }

}