package com.example.tabiyat.ui.main.addObservatrion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tabiyat.R
import com.example.tabiyat.databinding.AddObservationFragmentBinding
import org.koin.android.ext.android.inject

class AddObservationFragment : Fragment() {
    private lateinit var binding: AddObservationFragmentBinding
    private val viewModel by inject<AddObservationViewModel>()
    private var recyclerViewCategory: RecyclerView? = null
    private var recyclerViewAbundance: RecyclerView? = null
    private var recyclerViewBiotop: RecyclerView? = null
    private var recyclerViewFenofaze: RecyclerView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddObservationFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewCategory = requireActivity().findViewById(R.id.recycler_category)
        recyclerViewAbundance = requireActivity().findViewById(R.id.recycler_abundance)
        recyclerViewBiotop = requireActivity().findViewById(R.id.recycler_biotop)
        recyclerViewFenofaze = requireActivity().findViewById(R.id.recycler_fenofaze)
        createExpansionList()
    }


    private fun createExpansionList() {
        val category = ArrayList<String>()
        category.add("Категория 1")
        category.add("Категория 2")
        category.add("Категория 3")

        val abundance = ArrayList<String>()
        abundance.add("Обилие 1")
        abundance.add("Обилие 2")
        abundance.add("Обилие 3")

        val biotop = ArrayList<String>()
        biotop.add("Биотоп 1")
        biotop.add("Биотоп 2")
        biotop.add("Биотоп 3")

        val fenofaze = ArrayList<String>()
        fenofaze.add("Фенофаза 1")
        fenofaze.add("Фенофаза 2")
        fenofaze.add("Фенофаза 3")

        val adapterBiotop = ExpandableObservationAdapter(biotop)
        recyclerViewBiotop!!.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewBiotop!!.adapter = adapterBiotop

        val adapterCategory = ExpandableObservationAdapter(category)
        recyclerViewCategory!!.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewCategory!!.adapter = adapterCategory

        val adapterAbundance = ExpandableObservationAdapter(abundance)
        recyclerViewAbundance!!.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewAbundance!!.adapter = adapterAbundance


        val adapterFenofaze = ExpandableObservationAdapter(fenofaze)
        recyclerViewFenofaze!!.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewFenofaze!!.adapter = adapterFenofaze
    }
}