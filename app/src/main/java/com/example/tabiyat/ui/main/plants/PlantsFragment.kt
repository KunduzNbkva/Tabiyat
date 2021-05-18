package com.example.tabiyat.ui.main.plants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tabiyat.R
import com.example.tabiyat.base.OnDataClickListener
import com.example.tabiyat.data.model.Datum
import com.example.tabiyat.databinding.PlantsFragmentBinding
import org.koin.android.ext.android.inject

class PlantsFragment : Fragment(), OnDataClickListener, View.OnClickListener {
    private lateinit var binding: PlantsFragmentBinding
    private lateinit var adapter: PlantsAdapter
    private val viewModel by inject<PlantsViewModel>()
    private var clickCounter: Int = 0
    private lateinit var manager:LinearLayoutManager
    private var isLoading: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PlantsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observePlants()
        viewModel.resetPage()
        loadData()
        setRecycler()
        binding.searchLayout.buttonSort.setOnClickListener(this)
    }

    private fun setRecycler() {
        adapter = PlantsAdapter(this)
        manager = LinearLayoutManager(requireContext())
        binding.plantRecycler.layoutManager = manager
        binding.plantRecycler.adapter = adapter
        binding.plantRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (manager.findLastCompletelyVisibleItemPosition() == adapter.list.size - 1) {
                    loadData()
                    isLoading = true
                }
            }
        })
    }

    private fun loadData() {
        viewModel.getPlantsList()
    }

    private fun observePlants() {
        viewModel.plantsList.observe(viewLifecycleOwner, {
            adapter.addItems(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onItemClicked(model: Datum) {
        val bundle = Bundle()
        model.let {
            bundle.putSerializable("model", it)
            bundle.putString("type","plants")
        }
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_plantsFragment_to_cardDetailFragment, bundle)
        }
    }

    override fun onClick(v: View?) {
        clickCounter++
        if (clickCounter % 2 == 0) {
            binding.searchCardView.visibility = View.GONE
        } else binding.searchCardView.visibility = View.VISIBLE
    }


}



