package kg.tabiyat.ui.main.addAnimalsObservation

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.tabiyat.R
import kg.tabiyat.base.BaseFragment
import kg.tabiyat.base.OnDataClickListener
import kg.tabiyat.data.model.Datum
import kg.tabiyat.databinding.FragmentChooseAnimalBinding
import kg.tabiyat.ui.main.addAnimalsObservation.adapter.ChooseAnimalsAdapter
import kg.tabiyat.ui.main.addAnimalsObservation.viewModel.ChooseAnimalViewModel
import org.koin.android.ext.android.inject

class ChooseAnimalFragment :
    BaseFragment<FragmentChooseAnimalBinding>(FragmentChooseAnimalBinding::inflate),
    OnDataClickListener {
    private lateinit var adapter: ChooseAnimalsAdapter
    private val viewModel by inject<ChooseAnimalViewModel>()

    override fun setUpViews() {
        super.setUpViews()
        viewModel.resetPage()
        loadData()
        setRecycler()
    }

    override fun observeData() {
        super.observeData()
        observePlants()
    }

    private fun setRecycler() {
        val manager = LinearLayoutManager(requireContext())
        binding.listAnimals.layoutManager = manager
        adapter = ChooseAnimalsAdapter(this)
        binding.listAnimals.adapter = adapter
        binding.listAnimals.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (manager.findLastCompletelyVisibleItemPosition() == adapter.list.size - 1) {
                    loadData()
                }
            }
        })
    }

    private fun loadData() {
        viewModel.getAnimalsList()
    }

    private fun observePlants() {
//        viewModel.getLocalPlantsList().observe(viewLifecycleOwner){
//            adapter.addItems(it)
//        }
        viewModel.animalsList.observe(viewLifecycleOwner, {
            adapter.addItems(it)
        })
    }

    override fun onItemClicked(model: Datum) {
        val bundle = Bundle()
        model.let {
            bundle.putString("model_name", it.name!!.ru.toString())
        }
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_addAnimalObsrvFragment_to_chooseAnimalFragment, bundle)
        }
    }
}