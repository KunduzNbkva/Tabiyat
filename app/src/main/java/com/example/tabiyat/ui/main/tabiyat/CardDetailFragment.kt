package com.example.tabiyat.ui.main.tabiyat

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tabiyat.R
import com.example.tabiyat.databinding.CardDetailFragmentBinding
import com.example.tabiyat.ui.main.tabiyat.adapters.AddObservationAdapter
import com.example.tabiyat.ui.main.tabiyat.viewModels.CardDetailViewModel
import org.koin.android.ext.android.inject

class CardDetailFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: CardDetailFragmentBinding
    private val viewModel by inject<CardDetailViewModel>()
    private var buttonAddObservation:Button? = null
    private var redBookRecycler:RecyclerView? = null
    private var fenofazeRecycler:RecyclerView? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CardDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        redBookRecycler = view.findViewById(R.id.redBook_list)
        fenofazeRecycler= view.findViewById(R.id.fenofaze_list)
        buttonAddObservation = view.findViewById(R.id.add_observation_btn)
        buttonAddObservation!!.setOnClickListener(this)
        openObservations()
        setList()
    }

    private fun setList(){
        val redBookList= ArrayList<String>()
        redBookList.add("Не входит / Входит")
        redBookList.add("Extinct")

        val fenofazeList= ArrayList<String>()
        fenofazeList.add("Почкование")
        fenofazeList.add("Рассвет")
        fenofazeList.add("Стеблестроение")

        val redBookAdapter = AddObservationAdapter(redBookList)
        redBookRecycler!!.layoutManager = LinearLayoutManager(requireContext())
        redBookRecycler!!.adapter = redBookAdapter

        val fenofazeAdapter = AddObservationAdapter(fenofazeList)
        fenofazeRecycler!!.layoutManager = LinearLayoutManager(requireContext())
        fenofazeRecycler!!.adapter = fenofazeAdapter
    }

    private fun openObservations(){
       binding.observationLayout.setOnClickListener {
           Navigation.findNavController(it).navigate(R.id.action_plantsDetailFragment_to_observationsFragment)
       }
    }

    override fun onClick(v: View?) {
        Navigation.findNavController(v!!).navigate(R.id.action_plantsDetailFragment_to_addObservationFragment)    }

}