package com.example.modueleccar.ui.sell

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.modueleccar.R
import com.example.modueleccar.databinding.FragmentConditionCheckBinding
import com.example.modueleccar.viewmodel.SellerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConditionCheckFragment : Fragment() {
    private lateinit var _binding: FragmentConditionCheckBinding
    private val viewModel: SellerViewModel by activityViewModels()
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentConditionCheckBinding.inflate(inflater, container, false)

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_conditionCheckFragment_to_registerLocationFragment)
        }

        viewModel.finalCondition.observe(viewLifecycleOwner) {
            if(it.first && it.second) {
                binding.apply {
                    btnNext.isEnabled = true
                    btnNext.backgroundTintList =
                        requireContext().getColorStateList(R.color.main_400)
                    btnNext.setTextColor(requireContext().getColor(R.color.gray_000))
                }
            }
            else {
                binding.apply {
                    btnNext.isEnabled = false
                    btnNext.backgroundTintList =
                        requireContext().getColorStateList(R.color.gray_050)
                    btnNext.setTextColor(requireContext().getColor(R.color.gray_700))
                }
            }
        }


        binding.apply {
            btnFirstConditionNo.setOnClickListener {
                viewModel.updateFirstCondition(false)
                tvWarningMessage1.visibility = View.VISIBLE
                it.backgroundTintList = requireContext().getColorStateList(R.color.main_400)
                (it as Button).setTextColor(requireContext().getColor(R.color.gray_000))

                btnFirstConditionYes.backgroundTintList = requireContext().getColorStateList(R.color.gray_050)
                btnFirstConditionYes.setTextColor(requireContext().getColor(R.color.gray_700))
            }

            btnFirstConditionYes.setOnClickListener {
                viewModel.updateFirstCondition(true)
                tvWarningMessage1.visibility = View.INVISIBLE
                it.backgroundTintList = requireContext().getColorStateList(R.color.main_400)
                (it as Button).setTextColor(requireContext().getColor(R.color.gray_000))

                btnFirstConditionNo.backgroundTintList = requireContext().getColorStateList(R.color.gray_050)
                btnFirstConditionNo.setTextColor(requireContext().getColor(R.color.gray_700))
            }

            btnSecondConditionNo.setOnClickListener {
                viewModel.updateSecondCondition(false)
                tvWarningMessage2.visibility = View.VISIBLE
                it.backgroundTintList = requireContext().getColorStateList(R.color.main_400)
                (it as Button).setTextColor(requireContext().getColor(R.color.gray_000))

                btnSecondConditionYes.backgroundTintList = requireContext().getColorStateList(R.color.gray_050)
                btnSecondConditionYes.setTextColor(requireContext().getColor(R.color.gray_700))
            }

            btnSecondConditionYes.setOnClickListener {
                viewModel.updateSecondCondition(true)
                tvWarningMessage2.visibility = View.INVISIBLE
                it.backgroundTintList = requireContext().getColorStateList(R.color.main_400)
                (it as Button).setTextColor(requireContext().getColor(R.color.gray_000))

                btnSecondConditionNo.backgroundTintList = requireContext().getColorStateList(R.color.gray_050)
                btnSecondConditionNo.setTextColor(requireContext().getColor(R.color.gray_700))
            }


        }
        return binding.root
    }

}