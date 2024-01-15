package com.example.modueleccar.ui.purchase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.modueleccar.common.convertTimeString
import com.example.modueleccar.databinding.FragmentUseDescriptionBinding
import com.example.modueleccar.viewmodel.ChargerStateViewModel


class UseDescriptionFragment : Fragment() {
    private lateinit var _binding: FragmentUseDescriptionBinding
    private val viewModel: ChargerStateViewModel by activityViewModels()
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUseDescriptionBinding.inflate(inflater, container, false)

        viewModel.chargerUseInfo.observe(viewLifecycleOwner) {
            binding.tvStationLocation.text = "${it.address}"
            binding.tvReserveTime.text = "${
                convertTimeString(
                    it.reservedStartHour,
                    it.reservedEndHour
                )
            }, 총 ${it.reservedEndHour - it.reservedStartHour}시간"
            binding.tvExpectedCharge.text = "${it.expectedChargeAmount}kwh"
            binding.tvRentalPrice.text = "${it.totalPrice}원"
            binding.tvSellerMessage.text  = "${it.message}"
        }
        return binding.root
    }

}