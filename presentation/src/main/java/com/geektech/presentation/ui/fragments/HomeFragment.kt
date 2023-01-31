package com.geektech.presentation.ui.fragments

import android.Manifest
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.presentation.R
import com.geektech.presentation.base.BaseFragment
import com.geektech.presentation.databinding.FragmentHomeBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val binding by viewBinding(FragmentHomeBinding::bind)
    override val viewModel: HomeViewModel by viewModels()

    override fun initialize() {
        setupMaps()
        requestPermission()
    }

    fun setupMaps() {
        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.maps) as SupportMapFragment

        supportMapFragment.getMapAsync { map ->
            val bishkek = LatLng(42.8142, 73.8481)
            map.mapType = GoogleMap.MAP_TYPE_NORMAL
            map.moveCamera(CameraUpdateFactory.newLatLng(bishkek))
            map.addMarker(MarkerOptions().position(bishkek).title("Кара-Балта"))
        }
    }

    fun requestPermission() {

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permission ->

            when {
                permission.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    Toast.makeText(
                        requireContext(),
                        "Доступ к местоположению предоставлен",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                permission.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    Toast.makeText(
                        requireContext(),
                        "Only approximate location access granted",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                        startActivity(
                            Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS
                            )
                        )
                    }
                }
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            locationPermissionRequest.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                )
            )
        }
    }
}