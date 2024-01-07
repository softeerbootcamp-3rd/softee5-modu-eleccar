package com.example.modueleccar.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.modueleccar.common.dialogResize
import com.example.modueleccar.databinding.DialogConfirmRequestBinding

class ConfirmRequestDialog(context: Context, private val navigationCallback: () -> Unit) :
    Dialog(context) {
    private lateinit var _binding: DialogConfirmRequestBinding
    private val binding
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DialogConfirmRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)


        context.dialogResize(this, 0.8f, 0.3f)

        binding.btnBack.setOnClickListener {
            dismiss()
        }

        binding.btnApplyReservation.setOnClickListener {
            navigationCallback()
            dismiss()
        }
    }
}