package com.example.modueleccar.ui.purchase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.modueleccar.R
import com.example.modueleccar.databinding.FragmentPaymentBinding
import com.example.modueleccar.viewmodel.ChargerStateViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaymentFragment : Fragment() {
    private lateinit var _binding: FragmentPaymentBinding
    private val viewModel: ChargerStateViewModel by activityViewModels()
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)


        CoroutineScope(Dispatchers.IO).launch {
            viewModel.updateChargerUseInfo()
        }

        binding.apply {
            binding.rgPayType.setOnCheckedChangeListener { group, checkedId ->
                binding.btnPayment.apply {
                    isEnabled = true
                    binding.btnPayment.backgroundTintList =
                        requireContext().getColorStateList(R.color.main_400)
                    setTextColor(requireContext().getColor(R.color.gray_000))

                }
                when (checkedId) {
                    binding.btnCarbabPay.id ->
                        viewModel.updatePaymentType(1)

                    binding.btnOtherPay.id ->
                        viewModel.updatePaymentType(2)

                }
            }
            btnPayment.setOnClickListener {
                findNavController().navigate(R.id.action_paymentFragment_to_completePaymentFragment)
            }
        }



        viewModel.chargerUseInfo.observe(viewLifecycleOwner) {
            binding.apply {
                btnPayment.apply {
                    isEnabled = false
                    text = "${it.totalPrice}원 결제하기"
                    setBackgroundColor(requireContext().getColor(R.color.gray_050))
                    backgroundTintList = requireContext().getColorStateList(R.color.gray_050)
                    setTextColor(requireContext().getColor(R.color.gray_600))
                    background = requireContext().getDrawable(R.drawable.radius_8dp)
                }
            }
        }
        return binding.root
    }
}