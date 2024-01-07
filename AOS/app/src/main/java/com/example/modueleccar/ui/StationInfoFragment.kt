package com.example.modueleccar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.modueleccar.R
import com.example.modueleccar.databinding.FragmentStationInfoBinding


class StationInfoFragment : Fragment() {
    private lateinit var _binding: FragmentStationInfoBinding
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


}