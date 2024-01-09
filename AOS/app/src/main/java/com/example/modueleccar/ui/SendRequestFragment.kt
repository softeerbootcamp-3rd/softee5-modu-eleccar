package com.example.modueleccar.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.modueleccar.R
import com.example.modueleccar.databinding.FragmentSendRequestBinding
import com.example.modueleccar.viewmodel.ChargerStateViewModel

class SendRequestFragment : Fragment() {
    private lateinit var _binding: FragmentSendRequestBinding
    private val viewModel : ChargerStateViewModel by activityViewModels()
    private val binding
        get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSendRequestBinding.inflate(inflater,container,false)

        binding.ivSendRequest.setOnClickListener {
            findNavController().navigate(R.id.action_sendRequestFragment_to_receiveRequestFragment)
        }

        viewModel.eventId.observe(viewLifecycleOwner) {
            Log.d("EventId", it.toString())
        }

        return binding.root
    }


}