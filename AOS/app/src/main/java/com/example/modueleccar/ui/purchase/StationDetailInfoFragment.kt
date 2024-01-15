package com.example.modueleccar.ui.purchase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.modueleccar.R
import com.example.modueleccar.common.convertTimeString
import com.example.modueleccar.data.ChargerDetail
import com.example.modueleccar.databinding.FragmentStationDetailInfoBinding
import com.example.modueleccar.viewmodel.ChargerStateViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class StationDetailInfoFragment : Fragment() {
    private lateinit var _binding: FragmentStationDetailInfoBinding
    private val viewModel: ChargerStateViewModel by activityViewModels()
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStationDetailInfoBinding.inflate(inflater, container, false)
        val chargerDetail = arguments?.get("chargeDetail") as ChargerDetail

        viewModel.updateChargerInfoId(chargerDetail.chargerInfoId)


        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getChargerState(chargerDetail.chargerInfoId)
        }
        binding.btnApplyReservation.setOnClickListener {
            findNavController().navigate(R.id.action_stationDetailInfoFragment_to_chooseTimeFragment)
        }

        binding.apply {
            tvElecAmount.text = "${chargerDetail.speed}kwh"
            tvStationTitle.text = "${chargerDetail.title}"
            tvDistance.text = "내 위치에서 " + String.format("%.1f", chargerDetail.distance) + "km"
            tvAdapterType.text = chargerDetail.chargerType
            tvRentalPrice.text = "${chargerDetail.pricePerHour / chargerDetail.speed}원/kwh"
            tvStationAvailTime.text =
                convertTimeString(chargerDetail.startHour, chargerDetail.endHour)
            tvInstallType.text = chargerDetail.installType
            tvStationAddr.text = chargerDetail.address
        }
        return binding.root
    }
}