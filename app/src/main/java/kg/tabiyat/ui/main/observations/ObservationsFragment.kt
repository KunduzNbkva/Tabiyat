package kg.tabiyat.ui.main.observations

import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kg.tabiyat.R
import kg.tabiyat.base.BaseFragment
import kg.tabiyat.base.OnCardClickListener
import kg.tabiyat.databinding.ObservationsFragmentBinding
import org.koin.android.ext.android.inject

class ObservationsFragment : BaseFragment<ObservationsFragmentBinding>(ObservationsFragmentBinding::inflate), OnCardClickListener {
    private val viewModel by inject<ObservationsViewModel>()

    override fun setUpViews() {
        super.setUpViews()
        setRecycler()
    }

    override fun observeData() {
        super.observeData()
    }


    private fun setRecycler() {
        val list =
            listOf("Наблюдение 1", "Наблюдение 1", "Наблюдение 1", "Наблюдение 1", "Наблюдение 1")
        binding.observationList.layoutManager = LinearLayoutManager(requireContext())
        binding.observationList.adapter = ObservationAdapter(list, this)
    }

    override fun onItemClicked(model: String) {
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_observationsFragment_to_detailObservationFragment)
        }
    }

}