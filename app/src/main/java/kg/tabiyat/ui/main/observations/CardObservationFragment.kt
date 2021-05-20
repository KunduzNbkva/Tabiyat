package kg.tabiyat.ui.main.observations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kg.tabiyat.databinding.CardObservationFragmentBinding
import org.koin.android.ext.android.inject

class CardObservationFragment : Fragment() {
    private lateinit var binding: CardObservationFragmentBinding
    private val viewModel by inject<CardObservationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CardObservationFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


}