package com.example.tabiyat.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tabiyat.databinding.RatingFragmentBinding
import com.example.tabiyat.ui.main.profile.adapter.RatingAdapter
import org.koin.android.ext.android.inject

class RatingFragment : Fragment() {
    private lateinit var binding: RatingFragmentBinding
    private val viewModel by inject<RatingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RatingFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRatingRecycler()
    }

    private fun setRatingRecycler() {
        val list = arrayListOf(
            "Морозов Алескей",
            "Морозов Алескей",
            "Морозов Алескей",
            "Морозов Алескей",
            "Морозов Алескей"
        )
        binding.ratingList.layoutManager = LinearLayoutManager(requireContext())
        binding.ratingList.adapter = RatingAdapter(list)
    }


}