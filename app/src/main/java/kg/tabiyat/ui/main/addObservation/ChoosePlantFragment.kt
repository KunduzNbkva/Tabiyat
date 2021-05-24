package kg.tabiyat.ui.main.addObservation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.tabiyat.R
import kg.tabiyat.base.BaseFragment
import kg.tabiyat.base.OnDataClickListener
import kg.tabiyat.data.model.Datum
import kg.tabiyat.databinding.FragmentChoosePlantBinding
import kg.tabiyat.ui.main.addObservation.adapter.ChoosePlantsAdapter
import kg.tabiyat.ui.main.addObservation.viewModel.ChoosePlantViewModel
import org.koin.android.ext.android.inject

class ChoosePlantFragment : BaseFragment<FragmentChoosePlantBinding>(FragmentChoosePlantBinding::inflate), OnDataClickListener {
    private lateinit var adapter: ChoosePlantsAdapter
    private val viewModel by inject<ChoosePlantViewModel>()

    override fun setUpViews() {
        super.setUpViews()
        viewModel.resetPage()
        viewModel.getPlantsList()
        setRecycler()
    }

    override fun observeData() {
        super.observeData()
        observePlants()
    }


    private fun setRecycler() {
        val manager = LinearLayoutManager(requireContext())
        binding.listPlants.layoutManager = manager
        adapter = ChoosePlantsAdapter(this)
        binding.listPlants.adapter = adapter
        binding.listPlants.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (manager.findLastCompletelyVisibleItemPosition() == adapter.list.size - 1) {
                    loadData()
                }
            }
        })
    }

    private fun loadData() {
        viewModel.getPlantsList()
    }

    private fun observePlants() {
//        viewModel.getLocalPlantsList().observe(viewLifecycleOwner){
//            adapter.addItems(it)

        viewModel.plantsList.observe(viewLifecycleOwner, {
            adapter.addItems(it)
        })
    }


    override fun onItemClicked(model: Datum) {
//        val bundle = Bundle()
//        model.let {
//            bundle.putSerializable("model_name", it)
//        }
        // Use the Kotlin extension in the fragment-ktx artifact
        setFragmentResult("plantData_key", bundleOf("model_name" to model))
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_choosePlantFragment_to_addObservationFragment)
        }
    }

}