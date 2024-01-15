package com.example.modueleccar.ui.purchase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.modueleccar.R
import com.example.modueleccar.common.convertTimeString
import com.example.modueleccar.databinding.FragmentCompletePaymentBinding
import com.example.modueleccar.viewmodel.ChargerStateViewModel


class CompletePaymentFragment : Fragment() {
    private lateinit var _binding: FragmentCompletePaymentBinding
    private val viewModel: ChargerStateViewModel by activityViewModels()
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCompletePaymentBinding.inflate(inflater, container, false)

        viewModel.chargerUseInfo.observe(viewLifecycleOwner) {
            binding.tvCompletePaymentPhrase.text = "${it.totalPrice}원 결제 완료"
            binding.tvStationLocation.text = "${it.address}"
            binding.tvReserveTime.text = "${
                convertTimeString(
                    it.reservedStartHour,
                    it.reservedEndHour
                )
            }, 총 ${it.reservedEndHour - it.reservedStartHour}시간"
            binding.tvExpectedCharge.text = "${it.expectedChargeAmount}kwh"
            binding.tvRentalPrice.text = "${it.totalPrice}원"
        }

        binding.btnConfirmStationInfo.setOnClickListener {
            findNavController().navigate(R.id.action_completePaymentFragment_to_useDescriptionFragment)
        }
        return binding.root
    }

}