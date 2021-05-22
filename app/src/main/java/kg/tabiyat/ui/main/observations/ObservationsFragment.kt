package kg.tabiyat.ui.main.observations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kg.tabiyat.R
import kg.tabiyat.base.OnCardClickListener
import kg.tabiyat.databinding.ObservationsFragmentBinding
import org.koin.android.ext.android.inject

class ObservationsFragment : Fragment(), OnCardClickListener {
    private lateinit var binding: ObservationsFragmentBinding
    private val viewModel by inject<ObservationsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ObservationsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
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