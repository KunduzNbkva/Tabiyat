package com.example.tabiyat.ui.main.tabiyat.animals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tabiyat.R
import com.example.tabiyat.base.ListModel
import com.example.tabiyat.base.OnItemClickListener
import com.example.tabiyat.databinding.AnimalsFragmentBinding
import org.koin.android.ext.android.inject

class AnimalsFragment : Fragment(), OnItemClickListener,View.OnClickListener {
    private lateinit var binding: AnimalsFragmentBinding
    private val viewModel by inject <AnimalsViewModel>()
    private lateinit var adapter: AnimalsAdapter
    private var clickCounter: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = AnimalsFragmentBinding.inflate(LayoutInflater.from(requireContext()),container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
    }

    private fun setRecycler() {
        val list = arrayListOf(
            ListModel(R.drawable.plants_1, "Астрагал Шангина", "Отдел"),
            ListModel(R.drawable.plants_2, "Астрагал Шангина", "Отдел"),
            ListModel(R.drawable.plants_3, "Астрагал Шангина", "Отдел"),
            ListModel(R.drawable.card_img, "Астрагал Шангина", "Отдел"),
        )
        adapter = AnimalsAdapter(list, this)
        binding.animalsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.animalsRecycler.adapter = adapter
    }

    override fun onClick(v: View?) {
        clickCounter++
        if (clickCounter % 2 == 0) {
            binding.searchCardView.visibility = View.GONE
        } else binding.searchCardView.visibility = View.VISIBLE
    }

    override fun onItemClicked(model: ListModel) {
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_plantsFragment_to_cardDetailFragment)
        }
    }

}