package com.example.tabiyat.ui.main.animals

import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tabiyat.R
import com.example.tabiyat.base.*
import com.example.tabiyat.data.model.Datum
import com.example.tabiyat.databinding.AnimalsFragmentBinding
import org.koin.android.ext.android.inject

class AnimalsFragment() : BaseFragment<AnimalsFragmentBinding>(AnimalsFragmentBinding::inflate),
    OnDataClickListener, View.OnClickListener {
    private lateinit var adapter: AnimalsAdapter
    private var clickCounter: Int = 0
    private val viewModel by inject<AnimalsViewModel>()


    override fun setUpViews() {
        super.setUpViews()
        loadData()
    }

    override fun observeData() {
        super.observeData()
        viewModel.animalsList.observe(viewLifecycleOwner, {
            setRecycler(it)
        })
    }

    private fun setRecycler(list: List<Datum>) {
        adapter = AnimalsAdapter(list, this)
        val manager = LinearLayoutManager(requireContext())
        binding.animalsRecycler.layoutManager = manager
        binding.animalsRecycler.adapter = adapter
        binding.animalsRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (manager.findLastCompletelyVisibleItemPosition() == list.size - 1) {
                    loadData()
                }
            }
        })
    }

    private fun loadData() {
        viewModel.getAnimalsList()
    }


    override fun onClick(v: View?) {
        clickCounter++
        if (clickCounter % 2 == 0) {
            binding.searchCardView.visibility = View.GONE
        } else binding.searchCardView.visibility = View.VISIBLE
    }

    override fun onItemClicked(model: Datum) {
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_animalsFragment_to_plantsDetailFragment)
        }
    }


}