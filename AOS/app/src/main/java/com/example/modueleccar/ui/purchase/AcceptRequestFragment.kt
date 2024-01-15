package com.example.modueleccar.ui.purchase

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.modueleccar.common.convertTimeString
import com.example.modueleccar.databinding.FragmentAcceptRequestBinding
import com.example.modueleccar.ui.HomeFragment
import com.example.modueleccar.viewmodel.SellerViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AcceptRequestFragment : Fragment() {
    private lateinit var _binding: FragmentAcceptRequestBinding
    private val binding
        get() = _binding

    private val viewModel: SellerViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAcceptRequestBinding.inflate(inflater, container, false)

        CoroutineScope(Dispatchers.Default).launch {
            viewModel.getChargerUseInfo()
        }
        Log.d("accept request eventId", "${viewModel.eventId.value}")
        viewModel.chargerInfo.observe(viewLifecycleOwner) {
            binding.tvReserveTime.text = "${
                convertTimeString(
                    it.reservedStartHour,
                    it.reservedEndHour
                )
            }, 총 ${it.reservedEndHour - it.reservedStartHour}시간"
            binding.tvStationLocation.text = it.address
            binding.tvExpectedCharge.text = "${it.expectedChargeAmount.toString()}kwh"
            binding.tvRentalPrice.text = "${it.totalPrice.toString()}원"
        }

        binding.btnAccept.setOnClickListener {
            viewModel.updateIsAccepted(true)
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.postReservationConfirm()
            }
            childFragmentManager.beginTransaction().replace(binding.flAcceptRequest.id,
                HomeFragment()
            ).commit()

        }

        return binding.root
    }

}