package com.example.modueleccar.ui.purchase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.modueleccar.R
import com.example.modueleccar.common.convertTimeString
import com.example.modueleccar.databinding.FragmentReceiveRequestBinding
import com.example.modueleccar.viewmodel.ChargerStateViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ReceiveRequestFragment : Fragment() {
    private lateinit var _binding: FragmentReceiveRequestBinding
    private val viewModel: ChargerStateViewModel by activityViewModels()
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentReceiveRequestBinding.inflate(inflater, container, false)

        binding.apply {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.updateChargerUseInfo()
            }
            viewModel.chargerUseInfo.observe(viewLifecycleOwner) {
                binding.tvReserveTime.text = "${
                    convertTimeString(
                        it.reservedStartHour,
                        it.reservedEndHour
                    )
                }, 총 ${it.reservedEndHour - it.reservedStartHour}시간"
                binding.tvStationLocation.text = it.address
                binding.tvExpectedCharge.text = "${it.expectedChargeAmount.toString()}kwh"
                binding.tvRentalPrice.text = "${it.totalPrice.toString()}원"
                binding.btnPayment.text = "${it.totalPrice}원 결제하기"
            }
            btnPayment.setOnClickListener {
                findNavController().navigate(R.id.action_receiveRequestFragment_to_paymentFragment)
            }
            btnCancelRental.setOnClickListener {
                Toast.makeText(requireContext(), "대여 취소", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

}