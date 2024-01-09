package com.example.modueleccar.ui.dialog

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modueleccar.data.ChargerList
import com.example.modueleccar.databinding.BottomSheetBinding
import com.example.modueleccar.ui.adapter.ChargerAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment(
    private val chargerList: ChargerList,
    private val navigationCallback: (Int, Double) -> Unit
) : BottomSheetDialogFragment() {
    private lateinit var _binding: BottomSheetBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = BottomSheetBinding.inflate(inflater, container, false)

        binding.root.setBackgroundColor(Color.WHITE)
        binding.rvChargerList.adapter = ChargerAdapter(chargerList, navigationCallback)
        binding.rvChargerList.layoutManager = LinearLayoutManager(context)
        return binding.root
    }
}