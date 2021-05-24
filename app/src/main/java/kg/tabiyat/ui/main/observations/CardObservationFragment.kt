package kg.tabiyat.ui.main.observations

import kg.tabiyat.base.BaseFragment
import kg.tabiyat.databinding.CardObservationFragmentBinding
import org.koin.android.ext.android.inject

class CardObservationFragment : BaseFragment<CardObservationFragmentBinding>(CardObservationFragmentBinding::inflate) {
    private val viewModel by inject<CardObservationViewModel>()

    override fun setUpViews() {
        super.setUpViews()
    }

    override fun observeData() {
        super.observeData()
    }

}