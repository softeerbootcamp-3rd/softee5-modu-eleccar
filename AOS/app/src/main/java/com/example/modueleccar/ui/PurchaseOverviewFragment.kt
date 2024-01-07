package com.example.modueleccar.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.modueleccar.R
import com.example.modueleccar.databinding.FragmentPurchaseOverviewBinding
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.MarkerIcons


class PurchaseOverviewFragment : Fragment(), OnMapReadyCallback {
    private lateinit var _binding: FragmentPurchaseOverviewBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPurchaseOverviewBinding.inflate(inflater, container, false)

        binding.btnShowList.setOnClickListener {
            findNavController().navigate(R.id.action_purchaseOverviewFragment_to_registerFragment)
        }

        val marker = Marker()
        marker.position = LatLng(37.5670135, 126.9783740)

        //childFragmentManager.findFragmentById(R.id.map_fragment)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as MapFragment?
            ?: MapFragment.newInstance().also {
                childFragmentManager.beginTransaction().add(R.id.map_fragment, it).commit()
            }
        mapFragment.getMapAsync(this)

        binding.fcvStationInfo.setOnClickListener {
            findNavController().navigate(R.id.action_purchaseOverviewFragment_to_stationDetailInfoFragment)
        }

        return binding.root
    }

    override fun onMapReady(naverMap: NaverMap) {
        val marker = Marker()
        marker.position = LatLng(37.5670135, 126.9783740)
        marker.icon = OverlayImage.fromResource(R.drawable.map_marker)
        //marker.iconTintColor = requireActivity().getColor(R.color.main_400)
        marker.map = naverMap
        val infoWindow = InfoWindow()
        infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(requireContext()) {
            override fun getText(p0: InfoWindow): CharSequence {
                return "100원"
            }
        }

        infoWindow.open(marker)

        val marker1 = Marker()
        marker1.position = LatLng(37.62444907132257, 127.09321109051345)
        marker1.icon = OverlayImage.fromResource(R.drawable.map_marker)
        marker1.map = naverMap

        val infoWindow1 = InfoWindow()
        infoWindow1.adapter = object : InfoWindow.DefaultTextAdapter(requireContext()) {
            override fun getText(p0: InfoWindow): CharSequence {
                return "100원"
            }
        }
        infoWindow1.open(marker1)


        marker.setOnClickListener { overlay ->
            childFragmentManager.beginTransaction().replace(
                R.id.fcv_station_info, StationInfoFragment()
            ).commit()
            true
        }
        naverMap.setOnMapClickListener {
            _, _ ->
            childFragmentManager.beginTransaction().remove(
                StationInfoFragment()
            ).commit()
        }
    }

}