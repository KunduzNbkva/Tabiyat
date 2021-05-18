package com.example.tabiyat.ui.main.info

import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tabiyat.R
import com.example.tabiyat.base.BaseFragment
import com.example.tabiyat.base.BaseViewModel
import com.example.tabiyat.base.ListModel
import com.example.tabiyat.base.OnItemClickListener
import com.example.tabiyat.databinding.InfoFragmentBinding
import org.koin.android.ext.android.inject

class InfoFragment: BaseFragment<InfoFragmentBinding>(InfoFragmentBinding::inflate),OnItemClickListener,View.OnClickListener{
    private lateinit var adapter: InfoAdapter
    private var clickCounter: Int = 0
    private  val  viewModel by inject<InfoViewModel>()


    override fun setUpViews() {
        super.setUpViews()
        setRecycler()
    }

        private fun setRecycler() {
        val list = arrayListOf(
            ListModel(R.drawable.plants_1, "Астрагал Шангина", "Отдел"),
            ListModel(R.drawable.plants_2, "Астрагал Шангина", "Отдел"),
            ListModel(R.drawable.plants_3, "Астрагал Шангина", "Отдел"),
            ListModel(R.drawable.card_img, "Астрагал Шангина", "Отдел"),
        )
        adapter = InfoAdapter(list, this)
        binding.infoRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.infoRecycler.adapter = adapter
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
                .navigate(R.id.action_infoFragment_to_plantsDetailFragment)
        }
    }


}