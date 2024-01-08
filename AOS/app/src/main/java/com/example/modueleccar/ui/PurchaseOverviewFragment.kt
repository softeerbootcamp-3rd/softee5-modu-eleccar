package com.example.modueleccar.ui

import android.icu.text.IDNA.Info
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.modueleccar.ChargerListViewModel
import com.example.modueleccar.R
import com.example.modueleccar.databinding.FragmentPurchaseOverviewBinding
import com.example.modueleccar.ui.dialog.BottomSheetFragment
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PurchaseOverviewFragment : Fragment(), OnMapReadyCallback {
    private lateinit var _binding: FragmentPurchaseOverviewBinding
    private val viewModel: ChargerListViewModel by viewModels()

    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPurchaseOverviewBinding.inflate(inflater, container, false)

        binding.apply {
            btnShowList.setOnClickListener {
                findNavController().navigate(R.id.action_purchaseOverviewFragment_to_registerFragment)
            }
            fcvStationInfo.setOnClickListener {
                findNavController().navigate(R.id.action_purchaseOverviewFragment_to_stationDetailInfoFragment)
            }

        }
        viewModel.fetchData()




        //childFragmentManager.findFragmentById(R.id.map_fragment)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as MapFragment?
            ?: MapFragment.newInstance().also {
                childFragmentManager.beginTransaction().add(R.id.map_fragment, it).commit()
            }
        mapFragment.getMapAsync(this)


        return binding.root
    }

    override fun onMapReady(naverMap: NaverMap) {

        viewModel.chargerList.observe(this@PurchaseOverviewFragment) { chargerList ->
            binding.btnShowBottomSheet.setOnClickListener {
                BottomSheetFragment(chargerList).show(childFragmentManager, this@PurchaseOverviewFragment.tag)
            }
            chargerList.chargers.forEach { charger ->
                val marker = Marker()
                val infoWindow = InfoWindow()
                marker.tag = charger.chargerInfoId
                marker.position = LatLng(charger.latitude, charger.longitude)
                marker.icon = OverlayImage.fromResource(R.drawable.map_marker)

                marker.setOnClickListener { overlay ->
                    childFragmentManager.beginTransaction().replace(
                        R.id.fcv_station_info, StationInfoFragment()
                    ).commit()
                    true
                }
                marker.map = naverMap

                infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(requireContext()) {
                    override fun getText(p0: InfoWindow): CharSequence {
                        return "${charger.pricePerHour}원"
                    }
                }
                infoWindow.open(marker)

            }
        }
    }

}