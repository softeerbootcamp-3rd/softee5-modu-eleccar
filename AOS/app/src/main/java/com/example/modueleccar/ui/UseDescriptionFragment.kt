package com.example.modueleccar.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.modueleccar.R
import com.example.modueleccar.databinding.FragmentCompletePaymentBinding
import com.example.modueleccar.databinding.FragmentUseDescriptionBinding


class UseDescriptionFragment : Fragment() {
    private lateinit var _binding: FragmentUseDescriptionBinding
    private val binding
        get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUseDescriptionBinding.inflate(inflater,container,false)
        return binding.root
    }

}