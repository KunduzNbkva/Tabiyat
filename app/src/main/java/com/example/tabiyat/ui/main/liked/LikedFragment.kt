package com.example.tabiyat.ui.main.liked

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tabiyat.R
import com.example.tabiyat.databinding.LikedFragmentBinding
import com.example.tabiyat.ui.main.liked.adapter.LikedAdapter
import com.example.tabiyat.base.OnCardClickListener
import org.koin.android.ext.android.inject

class LikedFragment : Fragment(), OnCardClickListener {
    private lateinit var binding: LikedFragmentBinding
    private lateinit var adapter: LikedAdapter
    private val viewModel by inject<LikedViewModel>()
    private var listOfLiked: ArrayList<String>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LikedFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // checkForLiked()
        setRecycler()
        setOnBtnTouch(binding.likedAnimalsBtn)
        setOnBtnTouch(binding.likedInfoBtn)
        setOnBtnTouch(binding.likedPlantsBtn)

    }


    override fun onItemClicked(model: String) {
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_navigation_liked_to_cardDetailFragment)
        }
    }
    private fun setRecycler() {
        val list = listOf(
            "Астрагал Шангина",
            "Астрагал Шангина",
            "Астрагал Шангина",
            "Астрагал Шангина"
        )
        adapter = LikedAdapter(list, this)
        binding.likedList.layoutManager = LinearLayoutManager(requireContext())
        binding.likedList.adapter = adapter
    }

    private fun setOnBtnTouch(button:Button){
        button.setOnClickListener {
            button.setBackgroundResource(R.drawable.btn_enable)
            Toast.makeText(requireContext(),"Clicked",Toast.LENGTH_SHORT).show()
        }
    }

//    private fun checkForLiked(){
//        if(listOfLiked!=null){
//            binding.likedBtnLayout.visibility = View.VISIBLE
//            binding.likedList.visibility = View.VISIBLE
//            binding.likedSearch.searchLayout.visibility = View.VISIBLE
//            binding.noLikedLayout.visibility = View.GONE
//        } else {
//            binding.likedBtnLayout.visibility = View.GONE
//            binding.likedList.visibility = View.GONE
//            binding.likedSearch.searchLayout.visibility = View.GONE
//            binding.noLikedLayout.visibility = View.VISIBLE
//        }
//    }


}