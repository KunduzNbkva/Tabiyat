package com.example.tabiyat.ui.main.tabiyat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tabiyat.databinding.CardDetailFragmentBinding
import com.example.tabiyat.databinding.CardObservationFragmentBinding
import com.example.tabiyat.ui.main.tabiyat.viewModels.CardObservationViewModel
import org.koin.android.ext.android.inject

class CardObservationFragment : Fragment() {
    private lateinit var binding:CardObservationFragmentBinding
    private val viewModel by inject<CardObservationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = CardObservationFragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}