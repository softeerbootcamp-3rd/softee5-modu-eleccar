package com.example.modueleccar.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.WindowManager.LayoutParams
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.modueleccar.R
import com.example.modueleccar.common.convertTimeString
import com.example.modueleccar.databinding.DialogConfirmRequestBinding
import com.example.modueleccar.viewmodel.ChargerStateViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
//        requireContext().dialogResize(requireDialog(), 0.8f, 0.22f)

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

    override fun onResume() {
        super.onResume()
        setDialogSize()
    }

    private fun setDialogSize() {
        requireDialog().window?.addFlags(LayoutParams.FLAG_DIM_BEHIND)
        val params: WindowManager.LayoutParams = requireDialog().window?.attributes!!
        params.apply {
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.WRAP_CONTENT
            dimAmount = 0.5f
        }

        requireDialog().window?.attributes = params
        requireDialog().window?.setBackgroundDrawableResource(R.drawable.radius_12dp)
    }
}
