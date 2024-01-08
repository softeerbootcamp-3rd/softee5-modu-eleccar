package com.example.modueleccar.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.modueleccar.R
import com.example.modueleccar.databinding.FragmentCompletePaymentBinding


class CompletePaymentFragment : Fragment() {
    private lateinit var _binding: FragmentCompletePaymentBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCompletePaymentBinding.inflate(inflater, container, false)

        binding.btnConfirmStationInfo.setOnClickListener {
            findNavController().navigate(R.id.action_completePaymentFragment_to_useDescriptionFragment)
        }
        return binding.root
    }


}