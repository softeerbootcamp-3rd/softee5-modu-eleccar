package com.example.modueleccar.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.modueleccar.R
import com.example.modueleccar.databinding.FragmentChooseTimeBinding
import com.example.modueleccar.databinding.FragmentPurchaseOverviewBinding
import com.example.modueleccar.ui.dialog.ConfirmRequestDialog


class ChooseTimeFragment : Fragment() {
    private lateinit var _binding: FragmentChooseTimeBinding
    private val binding
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChooseTimeBinding.inflate(inflater, container, false)

        binding.btnApplyReserve.setOnClickListener {
            ConfirmRequestDialog(
                requireContext()
            ) {
                findNavController().navigate(R.id.action_chooseTimeFragment_to_sendRequestFragment)
            }.show()
        }
        return binding.root
    }


}