package com.geektech.presentation.ui.fragments

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.presentation.R
import com.geektech.presentation.base.BaseFragment
import com.geektech.presentation.databinding.FragmentHomeBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val binding by viewBinding(FragmentHomeBinding::bind)
    override val viewModel: HomeViewModel by viewModels()

    override fun initialize() {
        setupMaps()
    }

    fun setupMaps() {
        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.maps) as SupportMapFragment

        supportMapFragment.getMapAsync { map ->
            map.mapType = GoogleMap.MAP_TYPE_NORMAL
        }
    }
}