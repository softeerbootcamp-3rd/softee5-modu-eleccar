package com.example.modueleccar.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.modueleccar.R
import com.example.modueleccar.databinding.FragmentReceiveRequestBinding


class ReceiveRequestFragment : Fragment() {
    private lateinit var _binding: FragmentReceiveRequestBinding
    private val binding
        get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentReceiveRequestBinding.inflate(inflater, container, false)

        binding.btnPayment.setOnClickListener {
            findNavController().navigate(R.id.action_receiveRequestFragment_to_paymentFragment)
        }
        binding.btnCancelRental.setOnClickListener {
            Toast.makeText(requireContext(), "대여 취소", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

}