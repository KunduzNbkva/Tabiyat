package com.example.tabiyat.ui.main.tabiyat.plants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tabiyat.R
import com.example.tabiyat.base.Plant
import com.example.tabiyat.databinding.PlantsFragmentBinding
import com.example.tabiyat.base.OnPlantsClickListener
import com.example.tabiyat.data.model.Datum
import com.example.tabiyat.ui.main.tabiyat.adapters.PlantsAdapter
import org.koin.android.ext.android.inject

class PlantsFragment : Fragment(), OnPlantsClickListener, View.OnClickListener {
    private lateinit var binding: PlantsFragmentBinding
    private lateinit var adapter: PlantsAdapter
    private val viewModel by inject<PlantsViewModel>()
    private var clickCounter: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PlantsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        observePlants()
//        viewModel.getPlantsList()
        setRecycler()
        binding.searchLayout.buttonSort.setOnClickListener(this)
    }


    private fun setRecycler() {
        val list = arrayListOf(
            Plant(R.drawable.plants_1, "Астрагал Шангина", "Отдел"),
            Plant(R.drawable.plants_2, "Астрагал Шангина", "Отдел"),
            Plant(R.drawable.plants_3, "Астрагал Шангина", "Отдел"),
            Plant(R.drawable.card_img, "Астрагал Шангина", "Отдел"),
        )
        adapter = PlantsAdapter(list, this)
        binding.plantRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.plantRecycler.adapter = adapter
    }

    override fun onItemClicked(model: Plant) {
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_plantsFragment_to_cardDetailFragment)
        }
    }

    override fun onClick(v: View?) {
        clickCounter++
        if (clickCounter % 2 == 0) {
            binding.searchCardView.visibility = View.GONE
        } else binding.searchCardView.visibility = View.VISIBLE
    }

//    private fun observePlants(){
//        viewModel.plantsList.observe(viewLifecycleOwner, Observer {
//             setRecycler(it)
//        })
//    }

}