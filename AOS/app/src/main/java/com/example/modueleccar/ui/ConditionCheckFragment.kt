package com.example.modueleccar.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.modueleccar.R
import com.example.modueleccar.databinding.FragmentConditionCheckBinding


class ConditionCheckFragment : Fragment() {
    private lateinit var _binding: FragmentConditionCheckBinding
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
        return binding.root
    }

}