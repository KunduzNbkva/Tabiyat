package com.example.tabiyat.ui.main.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.tabiyat.R
import com.example.tabiyat.databinding.FragmentMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment(), View.OnClickListener, GoogleMap.OnMarkerClickListener {
    private lateinit var binding: FragmentMapsBinding
    private var clickCounter: Int = 0

    private val callback = OnMapReadyCallback { googleMap ->
        val bishkek = LatLng(42.8746, 74.5698)
        val kgtu = LatLng(42.843649, 74.586088)
        val oshBzr = LatLng(42.877608482058825, 74.59048567851843)
        googleMap.addMarker(MarkerOptions().position(bishkek))
        googleMap.addMarker(MarkerOptions().position(kgtu))
        googleMap.addMarker(MarkerOptions().position(oshBzr))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bishkek,7f))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kgtu,7f))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(oshBzr,7f))

        googleMap.setOnMarkerClickListener(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMap()
        onDataClick()
        binding.mapButtonSort.setOnClickListener(this)

    }


    private fun setMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        Log.e("Map", "callback$callback")
    }

    private fun onDataClick(){
        binding.mapCardView.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_navigation_map_to_cardObservationFragment)
        }
    }


    override fun onClick(v: View?) {
        clickCounter++
        if (clickCounter % 2 == 0) {
            binding.mapCardView.visibility = View.VISIBLE
            binding.mapSortView.sortMap.visibility = View.GONE
        } else {
            binding.mapCardView.visibility = View.GONE
            binding.mapSortView.sortMap.visibility = View.VISIBLE
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        clickCounter++
        if (clickCounter % 2 == 0) {
        binding.mapCardView.visibility = View.VISIBLE}
        else {
            binding.mapCardView.visibility = View.GONE
        }
        return false
    }
}