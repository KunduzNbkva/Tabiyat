package com.example.tabiyat.ui.main.tabiyat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tabiyat.R
import com.example.tabiyat.base.ListModel
import com.example.tabiyat.base.OnMainCardClickListener
import com.example.tabiyat.databinding.TabiyatFragmentBinding
import com.example.tabiyat.ui.main.tabiyat.adapters.MainListAdapter
import org.koin.android.ext.android.inject


class TabiyatFragment : Fragment(), OnMainCardClickListener{
    private lateinit var binding: TabiyatFragmentBinding
    private lateinit var adapter: MainListAdapter
    private val viewModel by inject<TabiyatViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TabiyatFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = arrayListOf(
            ListModel(R.drawable.plants, "Растения", ""),
            ListModel(R.drawable.animals, "Животные", ""),
            ListModel(R.drawable.info_notes, "Полезная информация", "")
        )
        adapter = MainListAdapter(list, this)
        binding.mainList.layoutManager = LinearLayoutManager(requireContext())
        binding.mainList.adapter = adapter
    }

    override fun onItemClicked(model: ListModel, adapterPosition: Int) {
        when(adapterPosition){
            0->{
                view?.let {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_navigation_tabiyat_to_plantsFragment)
                }
            }
            1->{
                view?.let {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_navigation_tabiyat_to_animalsFragment)
                }
            }
            2->{
                view?.let {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_navigation_tabiyat_to_infoFragment)
                }
            }
        }
    }


}