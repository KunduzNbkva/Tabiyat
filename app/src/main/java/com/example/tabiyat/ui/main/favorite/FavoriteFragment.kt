package com.example.tabiyat.ui.main.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tabiyat.R
import com.example.tabiyat.base.OnFavoriteClickListener
import com.example.tabiyat.data.model.Favorite
import com.example.tabiyat.databinding.FavoriteFragmentBinding
import com.example.tabiyat.ui.main.favorite.adapter.FavoriteAdapter
import org.koin.android.ext.android.inject

class FavoriteFragment : Fragment(), OnFavoriteClickListener {
    private lateinit var binding: FavoriteFragmentBinding
    private lateinit var adapter: FavoriteAdapter
    private val viewModel by inject<FavoriteViewModel>()
    private lateinit var btnAnimals: Button
    private lateinit var btnInfo: Button
    private lateinit var btnPlants: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavoriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        btnAnimals = binding.favoriteAnimalsBtn
        btnInfo = binding.favoriteInfoBtn
        btnPlants = binding.favoritePlantsBtn
        viewModel.getFavoritesList()
        setOnBtnTouch(btnAnimals)
        setOnBtnTouch(btnInfo)
        setOnBtnTouch(btnPlants)
    }


    override fun onItemClicked(model: Favorite) {
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_navigation_liked_to_cardDetailFragment)
        }
    }


    private fun setOnBtnTouch(button: Button) {
        button.setOnClickListener {
            button.isSelected = !button.isSelected
        }
    }

    private fun observe() {
        viewModel.favoritesList.observe(viewLifecycleOwner, {
            if (it == null) {
                binding.favoriteBtnLayout.visibility = View.VISIBLE
                binding.favoriteList.visibility = View.VISIBLE
                binding.noFavoriteLayout.visibility = View.GONE
            } else setRecycler(it)
        })
    }


    private fun setRecycler(list: List<Favorite>) {
        adapter = FavoriteAdapter(list, this)
        binding.favoriteList.layoutManager = LinearLayoutManager(requireContext())
        binding.favoriteList.adapter = adapter
        binding.favoriteBtnLayout.visibility = View.VISIBLE
        binding.favoriteList.visibility = View.VISIBLE
        binding.noFavoriteLayout.visibility = View.GONE

    }


}