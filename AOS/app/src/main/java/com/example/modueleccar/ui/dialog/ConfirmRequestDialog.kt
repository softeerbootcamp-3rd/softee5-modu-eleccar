package com.example.modueleccar.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.modueleccar.common.convertTimeString
import com.example.modueleccar.common.dialogResize
import com.example.modueleccar.databinding.DialogConfirmRequestBinding
import com.example.modueleccar.viewmodel.ChargerStateViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConfirmRequestDialog(

    private val navigationCallback: () -> Unit
) :
    DialogFragment() {
    private lateinit var _binding: DialogConfirmRequestBinding
    private val viewModel: ChargerStateViewModel by activityViewModels()
    private val binding
        get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogConfirmRequestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val mDialog = checkNotNull(dialog)
        requireContext().dialogResize(mDialog, 0.8f, 0.22f)

        binding.tvReserveTime.text = convertTimeString(
            viewModel.reservationTimeState.value!!.startHour,
            viewModel.reservationTimeState.value!!.startHour + viewModel.reservationTimeState.value!!.chargeHour,
        )
        binding.tvChargeTime.text = "${viewModel.reservationTimeState.value!!.chargeHour}시간"


        binding.btnBack.setOnClickListener {
            dismiss()
        }

        binding.btnApplyReservation.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.updateEventId()
            }
            navigationCallback()
            dismiss()
        }
        super.onViewCreated(view, savedInstanceState)
    }

}