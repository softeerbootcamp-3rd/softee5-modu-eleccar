package com.example.modueleccar.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.modueleccar.R
import com.example.modueleccar.databinding.FragmentHomeBinding
import com.example.modueleccar.ui.dialog.BottomSheetFragment
import com.example.modueleccar.ui.purchase.StationInfoFragment
import com.example.modueleccar.viewmodel.ChargerListViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.LocationOverlay
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.FusedLocationSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class HomeFragment : Fragment(), OnMapReadyCallback {
    private lateinit var _binding: FragmentHomeBinding
    private val viewModel: ChargerListViewModel by activityViewModels()
    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap


    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setAvailableChargeBtn()

        binding.apply {
            btnShowList.setOnClickListener {
                findNavController().navigate(R.id.action_purchaseOverviewFragment_to_registerFragment)
            }
            fcvStationInfo.setOnClickListener {
                findNavController().navigate(R.id.action_purchaseOverviewFragment_to_stationDetailInfoFragment)
            }

        }
        //viewModel.fetchData()
        locationSource = FusedLocationSource(this, 1000) //LOCATION_PERMSSION_REQUEST_CODE

        viewModel.fetchData()

        //childFragmentManager.findFragmentById(R.id.map_fragment)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as MapFragment?
            ?: MapFragment.newInstance().also {
                childFragmentManager.beginTransaction().add(R.id.map_fragment, it).commit()
            }

        mapFragment.getMapAsync (this)



        return binding.root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions,
                grantResults
            )
        ) {
            if (!locationSource.isActivated) { // 권한 거부됨
                Log.d("permission check", "Denied")
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    override fun onMapReady(map: NaverMap) {
        this.naverMap = map

        map.locationSource = locationSource
        map.locationTrackingMode = LocationTrackingMode.Follow
        map.locationOverlay.isVisible = true
        map.locationOverlay.icon = OverlayImage.fromResource(R.drawable.icon_marker)
        map.locationOverlay.iconWidth = LocationOverlay.SIZE_AUTO
        map.locationOverlay.iconHeight = LocationOverlay.SIZE_AUTO

//        map.locationOverlay.subIcon = OverlayImage.fromResource(R.drawable.icon_marker)
//        map.locationOverlay.icon = OverlayImage.fromResource(R.drawable.icon_marker)
        binding.btnLocation.map = map

        viewModel.chargerList.observe(this@HomeFragment) { chargerList ->
            chargerList.chargers.forEach { charger ->
                val marker = Marker()
                val infoWindow = InfoWindow()
                marker.tag = charger.chargerInfoId
                marker.position = LatLng(charger.latitude, charger.longitude)
                marker.icon = OverlayImage.fromResource(R.drawable.icon_marker)
                marker.alpha = 0f
                marker.setOnClickListener { overlay ->
//                    childFragmentManager.beginTransaction().replace(
//                        R.id.fcv_station_info, StationInfoFragment()
//                    ).commit()
//                    viewModel.fetchSelectedCharger(charger)
//                    StationInfoFragment().show(childFragmentManager, "StationInfoFragment")
                    true
                }
                marker.map = map

                infoWindow.adapter =
                    object : InfoWindow.DefaultTextAdapter(requireContext()) {
                        override fun getText(p0: InfoWindow): CharSequence {
                            return "${charger.pricePerHour / charger.speed}원"
                        }
                    }
                infoWindow.setOnClickListener {
                    viewModel.fetchSelectedCharger(charger)
                    StationInfoFragment().show(childFragmentManager, "StationInfoFragment")
                    true
                }
                infoWindow.open(marker)

            }
            binding.btnShowBottomSheet.setOnClickListener {
                BottomSheetFragment(chargerList) { chargerInfoId, distance ->
                    CoroutineScope(Dispatchers.IO).launch {

                        val chargerDetail = viewModel.getChargerDetail(chargerInfoId, distance)
                        Log.d("chargerDetail", chargerDetail.toString())

                        withContext(Dispatchers.Main) {
                            findNavController().navigate(
                                R.id.action_purchaseOverviewFragment_to_stationDetailInfoFragment,
                                bundleOf("chargeDetail" to chargerDetail)
                            )
                        }
                    }
                }.show(childFragmentManager, this@HomeFragment.tag)
            }


        }
    }

    private fun setAvailableChargeBtn() {
        binding.btnEnableList.setOnClickListener {
            viewModel.updateAvailableChargeState(!viewModel.availableChargerState.value!!)
            if (!viewModel.availableChargerState.value!!) {
                binding.btnEnableList.apply {
                    backgroundTintList = requireContext().getColorStateList(R.color.gray_000)
                    setTextColor(requireContext().getColor(R.color.gray_700))

                    setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.icon_check_disable,
                        0
                    )

                }
            } else {
                binding.btnEnableList.apply {
                    backgroundTintList = requireContext().getColorStateList(R.color.main_400)
                    setTextColor(requireContext().getColor(R.color.gray_000))

                    setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.icon_check_enable,
                        0
                    )

                }
            }
        }

        viewModel.availableChargerState.observe(viewLifecycleOwner) {
            //TODO : 가능한 충전소 리스트만 뽑아오기 or 모든 충전소 리스트 뽑아오기
        }
    }

}