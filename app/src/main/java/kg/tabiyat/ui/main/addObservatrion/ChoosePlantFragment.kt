package kg.tabiyat.ui.main.addObservatrion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.tabiyat.R
import kg.tabiyat.base.OnDataClickListener
import kg.tabiyat.data.model.Datum
import kg.tabiyat.databinding.FragmentChoosePlantBinding
import kg.tabiyat.db.entity.PlantsEntity
import kg.tabiyat.ui.main.addObservatrion.adapter.ChoosePlantsAdapter
import kg.tabiyat.ui.main.addObservatrion.viewModel.ChoosePlantViewModel
import org.koin.android.ext.android.inject

class ChoosePlantFragment : Fragment(), OnDataClickListener {
    private lateinit var binding: FragmentChoosePlantBinding
    private lateinit var adapter: ChoosePlantsAdapter
    private val viewModel by inject<ChoosePlantViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChoosePlantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observePlants()
        viewModel.resetPage()
        viewModel.getPlantsList()
        setRecycler()
    }

    private fun setRecycler() {
        var manager = LinearLayoutManager(requireContext())
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
        viewModel.getLocalPlantsList().observe(viewLifecycleOwner){
            adapter.addItems(it)
        }
//        viewModel.plantsList.observe(viewLifecycleOwner, {
//            adapter.addItems(it)
//        })
    }

    override fun onItemClicked(model: PlantsEntity) {
        val bundle = Bundle()
        model.let {
            bundle.putString("model_name", it.name!!.ru.toString())
        }
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_choosePlantFragment_to_addObservationFragment, bundle)
        }
    }
}