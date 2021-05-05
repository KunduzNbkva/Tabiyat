package com.example.tabiyat.ui.main.map

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tabiyat.R
import com.example.tabiyat.databinding.FragmentMapsBinding
import com.example.tabiyat.ui.main.map.adapter.ExpandableAdapter

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment(),View.OnClickListener{
    private lateinit var binding:FragmentMapsBinding
    private var clickCounter: Int = 0

    private val callback = OnMapReadyCallback { googleMap ->
        val bishkek = LatLng(42.8746, 74.5698)
        googleMap.addMarker(MarkerOptions().position(bishkek).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bishkek,10f))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMap()
       binding.mapButtonSort.setOnClickListener(this)
    }


    private fun setMap(){
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        Log.e("Map", "callback$callback")
    }

    override fun onClick(v: View?) {
        clickCounter++
        if (clickCounter % 2 == 0) {
            binding.mapCardView.visibility = View.VISIBLE
            binding.mapSortView.sortMap.visibility = View.GONE
        } else {
        binding.mapCardView.visibility = View.GONE
        binding.mapSortView.sortMap.visibility = View.VISIBLE } }
}