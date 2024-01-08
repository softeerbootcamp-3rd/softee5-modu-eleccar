package com.example.modueleccar.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.modueleccar.R
import com.example.modueleccar.databinding.FragmentRegisterDetailBinding


class RegisterDetailFragment : Fragment() {
    private lateinit var _binding : FragmentRegisterDetailBinding
    private val binding
        get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterDetailBinding.inflate(inflater, container, false)

        return binding.root
    }


}