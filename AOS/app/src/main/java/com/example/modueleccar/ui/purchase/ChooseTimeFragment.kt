package com.example.modueleccar.ui.purchase

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.modueleccar.R
import com.example.modueleccar.databinding.FragmentChooseTimeBinding
import com.example.modueleccar.ui.dialog.ConfirmRequestDialog
import com.example.modueleccar.viewmodel.ChargerStateViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.min


@AndroidEntryPoint
class ChooseTimeFragment : Fragment() {
    private lateinit var _binding: FragmentChooseTimeBinding
    private val viewModel: ChargerStateViewModel by activityViewModels()
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChooseTimeBinding.inflate(inflater, container, false)

        binding.btnApplyReserve.setOnClickListener {
            viewModel.reservationTimeState.value?.let { value ->
                ConfirmRequestDialog()
                 {
                    findNavController().navigate(R.id.action_chooseTimeFragment_to_sendRequestFragment)
                }.show(childFragmentManager, null)
            }

        }
        init()

        viewModel.chargerState.observe(viewLifecycleOwner) { chargerState ->
            Log.d("chargerState", chargerState.toString())
            for (i in 0 until chargerState.size) {
                var maxPossibleTime = 0
                if (chargerState[i].available) {
                    for (j in i + 1 until chargerState.size) {
                        if (chargerState[j].available) {
                            maxPossibleTime += 1
                        } else break
                    }
                }
                maxPossibleTime = min(maxPossibleTime, 6)
                when (i) {
                    0 -> binding.btnClock9.text =
                        binding.btnClock9.text.toString() + "($maxPossibleTime)"

                    1 -> binding.btnClock10.text =
                        binding.btnClock10.text.toString() + "($maxPossibleTime)"

                    2 -> binding.btnClock11.text =
                        binding.btnClock11.text.toString() + "($maxPossibleTime)"

                    3 -> binding.btnClock12.text =
                        binding.btnClock12.text.toString() + "($maxPossibleTime)"

                    4 -> binding.btnClock13.text =
                        binding.btnClock13.text.toString() + "($maxPossibleTime)"

                    5 -> binding.btnClock14.text =
                        binding.btnClock14.text.toString() + "($maxPossibleTime)"

                    6 -> binding.btnClock15.text =
                        binding.btnClock15.text.toString() + "($maxPossibleTime)"

                    7 -> binding.btnClock16.text =
                        binding.btnClock16.text.toString() + "($maxPossibleTime)"

                    8 -> binding.btnClock17.text =
                        binding.btnClock17.text.toString() + "($maxPossibleTime)"

                    9 -> binding.btnClock18.text =
                        binding.btnClock18.text.toString() + "($maxPossibleTime)"
                }
            }
        }

        viewModel.reservationTimeState.observe(viewLifecycleOwner) { result ->
            Log.d("isAllSelected", result.toString())
            if (result.startHour != 0 && result.chargeHour != 0) {
                binding.btnApplyReserve.apply {
                    isEnabled = true
                    backgroundTintList = requireContext().getColorStateList(R.color.main_400)
                    setTextColor(requireContext().getColor(R.color.gray_000))
                }
            } else {
                binding.btnApplyReserve.apply {
                    isEnabled = false
                    backgroundTintList = requireContext().getColorStateList(R.color.gray_100)
                    setTextColor(requireContext().getColor(R.color.gray_300))
                }
            }

        }
        return binding.root
    }

    private fun init() {
        val startTimeBtnList = listOf<Button>(
            binding.btnClock9,
            binding.btnClock10,
            binding.btnClock11,
            binding.btnClock12,
            binding.btnClock13,
            binding.btnClock14,
            binding.btnClock15,
            binding.btnClock16,
            binding.btnClock17,
            binding.btnClock18,
        )
        val chargeTimeBtnList = listOf<Button>(
            binding.btnChargeTime1,
            binding.btnChargeTime2,
            binding.btnChargeTime3,
            binding.btnChargeTime4,
            binding.btnChargeTime5,
            binding.btnChargeTime6,
        )
        setAllBtnOnClickListener(startTimeBtnList, 0)
        setAllBtnOnClickListener(chargeTimeBtnList, 1)

    }

    private fun setAllBtnOnClickListener(btnList: List<Button>, type: Int) {
        btnList.forEach { btn ->
            btn.setOnClickListener {
                btnList.forEach { otherBtn ->
                    otherBtn.apply {
                        setBackgroundColor(requireContext().getColor(R.color.gray_050))
                        backgroundTintList = requireContext().getColorStateList(R.color.gray_050)
                        setTextColor(requireContext().getColor(R.color.gray_600))
                        background = requireContext().getDrawable(R.drawable.radius_8dp)
                    }
                }

                it.setBackgroundColor(requireContext().getColor(R.color.main_400))
                it.backgroundTintList = requireContext().getColorStateList(R.color.main_400)
                (it as Button).setTextColor(requireContext().getColor(R.color.gray_000))
                it.background = requireContext().getDrawable(R.drawable.radius_8dp)

                when (type) {
                    0 ->
                        when (it) {
                            binding.btnClock9 -> viewModel.updateStartTime(9)
                            binding.btnClock10 -> viewModel.updateStartTime(10)
                            binding.btnClock11 -> viewModel.updateStartTime(11)
                            binding.btnClock12 -> viewModel.updateStartTime(12)
                            binding.btnClock13 -> viewModel.updateStartTime(13)
                            binding.btnClock14 -> viewModel.updateStartTime(14)
                            binding.btnClock15 -> viewModel.updateStartTime(15)
                            binding.btnClock16 -> viewModel.updateStartTime(16)
                            binding.btnClock17 -> viewModel.updateStartTime(17)
                            binding.btnClock18 -> viewModel.updateStartTime(18)
                        }

                    1 ->
                        when (it) {
                            binding.btnChargeTime1 -> viewModel.updateChargeTime(1)
                            binding.btnChargeTime2 -> viewModel.updateChargeTime(2)
                            binding.btnChargeTime3 -> viewModel.updateChargeTime(3)
                            binding.btnChargeTime4 -> viewModel.updateChargeTime(4)
                            binding.btnChargeTime5 -> viewModel.updateChargeTime(5)
                            binding.btnChargeTime6 -> viewModel.updateChargeTime(6)
                        }
                }

            }
        }
    }


}