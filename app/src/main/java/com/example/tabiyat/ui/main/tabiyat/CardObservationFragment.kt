package com.example.tabiyat.ui.main.tabiyat

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tabiyat.R
import com.example.tabiyat.ui.main.tabiyat.viewModels.CardObservationViewModel

class CardObservationFragment : Fragment() {

    companion object {
        fun newInstance() = CardObservationFragment()
    }

    private lateinit var viewModel: CardObservationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.card_observation_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CardObservationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}