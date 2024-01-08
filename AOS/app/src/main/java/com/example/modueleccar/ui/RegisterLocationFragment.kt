package com.example.modueleccar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.modueleccar.databinding.FragmentRegisterLocationBinding


class RegisterLocationFragment : Fragment() {
    private lateinit var _binding: FragmentRegisterLocationBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterLocationBinding.inflate(inflater, container, false)
        return binding.root
    }
}