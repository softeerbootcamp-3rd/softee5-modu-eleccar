package com.example.modueleccar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.modueleccar.R
import com.example.modueleccar.databinding.ActivityMainBinding
import com.example.modueleccar.ui.purchase.AcceptRequestFragment
import com.example.modueleccar.viewmodel.SellerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val viewModel: SellerViewModel by viewModels()

    private val binding
        get() = _binding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val eventId = intent.getIntExtra("eventId", -1)
        Log.d("extras", intent.extras.toString())
        Log.d("received eventId", eventId.toString())
        if (eventId != -1) {
            viewModel.updateEventId(eventId)
            supportFragmentManager.beginTransaction()
                .replace(binding.fcvMain.id, AcceptRequestFragment())
                .commit()
        }

    }

//    private fun isPushIntent(pushIntent: Intent?) {
//
//        val eventId = pushIntent?.getIntExtra("eventId", -1) ?: let {
//            intent.getIntExtra("eventId", -1)
//        }
//
//        if (eventId != -1) {
//            Log.d("main eventId", eventId.toString())
//            viewModel.updateEventId(eventId)
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fcv_main, AcceptRequestFragment())
//                .addToBackStack(null)
//                .commit()
//        }
//    }
}