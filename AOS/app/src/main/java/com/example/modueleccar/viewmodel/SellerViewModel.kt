package com.example.modueleccar.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.modueleccar.repository.ReservationConfirmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SellerViewModel @Inject constructor(
    private val reservationConfirmRepository: ReservationConfirmRepository
): ViewModel() {
    // Seller -> 1, 구매자 -> 2
    private var _isAccepted = MutableLiveData(false)
    val isAccepted
        get() = _isAccepted

    private var _eventId = MutableLiveData<Int?>(null)
    val eventId
        get() = _eventId


    fun updateIsAccepted(acceptValue: Boolean) {
        _isAccepted.value = acceptValue
    }
    fun updateEventId(eventIdValue: Int) {
        _eventId.value = eventIdValue
    }

    suspend fun postReservationConfirm() {
        reservationConfirmRepository.postReservationConfirm(
            _isAccepted.value!!,
            _eventId.value!!,
            1 // 판매자
        )
    }

}