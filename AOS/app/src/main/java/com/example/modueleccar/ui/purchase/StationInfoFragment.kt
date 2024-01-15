package com.example.modueleccar.ui.purchase

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.modueleccar.common.convertTimeString
import com.example.modueleccar.databinding.FragmentStationInfoBinding
import com.example.modueleccar.viewmodel.ChargerListViewModel


class StationInfoFragment : DialogFragment() {
    private lateinit var _binding: FragmentStationInfoBinding
    private val viewModel: ChargerListViewModel by activityViewModels()
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStationInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.selectedCharger.observe(viewLifecycleOwner) {
            binding.apply {
                tvAvailableTime.text = convertTimeString(it.startHour, it.endHour)
                tvStationTitle.text = it.title
                tvElecAmount.text = "${it.speed}kwh"
                tvPerHourPrice.text = "${it.pricePerHour / it.speed}원"
            }
        }
        setDialogSize()
    }

    private fun setDialogSize() {
        val params: WindowManager.LayoutParams = requireDialog().window?.attributes!!
        params.apply {
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.WRAP_CONTENT
        }
        requireDialog().window?.apply {
            attributes = params
            setGravity(Gravity.BOTTOM)
        }


    }

}