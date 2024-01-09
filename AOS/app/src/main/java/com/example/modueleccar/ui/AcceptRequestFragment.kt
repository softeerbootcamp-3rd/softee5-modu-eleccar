package com.example.modueleccar.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.modueleccar.R
import com.example.modueleccar.databinding.FragmentAcceptRequestBinding
import com.example.modueleccar.viewmodel.SellerViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AcceptRequestFragment : Fragment() {
    private lateinit var _binding : FragmentAcceptRequestBinding
    private val binding
        get() = _binding

    private val viewModel: SellerViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAcceptRequestBinding.inflate(inflater, container, false)



        binding.btnAccept.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.postReservationConfirm()
            }
        }
        return binding.root
    }

}