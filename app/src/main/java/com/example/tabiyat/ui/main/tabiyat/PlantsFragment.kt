package com.example.tabiyat.ui.main.tabiyat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tabiyat.R
import com.example.tabiyat.base.uiModels.PlantsModel
import com.example.tabiyat.databinding.PlantsFragmentBinding
import com.example.tabiyat.ui.main.tabiyat.adapters.PlantsAdapter
import com.example.tabiyat.ui.main.tabiyat.viewModels.PlantsViewModel
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
        setRecycler()
        binding.searchLayout.buttonSort.setOnClickListener(this)
    }


    private fun setRecycler() {
        val list = listOf<PlantsModel>(
            PlantsModel(R.drawable.plants_1, "Астрагал Шангина", "Отдел"),
            PlantsModel(R.drawable.plants_2, "Астрагал Шангина", "Отдел"),
            PlantsModel(R.drawable.plants_3, "Астрагал Шангина", "Отдел"),
            PlantsModel(R.drawable.card_img, "Астрагал Шангина", "Отдел"),
        )
        adapter = PlantsAdapter(list, this)
        binding.plantRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.plantRecycler.adapter = adapter
    }

    override fun onItemClicked(model: PlantsModel) {
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

}