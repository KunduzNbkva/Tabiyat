package kg.tabiyat.ui.main.addAnimalsObservation

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
import kg.tabiyat.databinding.FragmentChooseAnimalBinding
import kg.tabiyat.data.local.db.entity.PlantsEntity
import kg.tabiyat.data.model.Datum
import kg.tabiyat.ui.main.addAnimalsObservation.adapter.ChooseAnimalsAdapter
import kg.tabiyat.ui.main.addAnimalsObservation.viewModel.ChooseAnimalViewModel
import org.koin.android.ext.android.inject

class ChooseAnimalFragment : Fragment(), OnDataClickListener {
    private lateinit var binding: FragmentChooseAnimalBinding
    private lateinit var adapter: ChooseAnimalsAdapter
    private val viewModel by inject<ChooseAnimalViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseAnimalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observePlants()
        viewModel.resetPage()
      //  viewModel.getPlantsList()
        setRecycler()
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
    //    viewModel.getPlantsList()
    }

    private fun observePlants() {
//        viewModel.getLocalPlantsList().observe(viewLifecycleOwner){
//            adapter.addItems(it)
//        }
//        viewModel.plantsList.observe(viewLifecycleOwner, {
//            adapter.addItems(it)
//        })
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