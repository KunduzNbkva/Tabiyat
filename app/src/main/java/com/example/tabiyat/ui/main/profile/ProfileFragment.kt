package com.example.tabiyat.ui.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tabiyat.R
import com.example.tabiyat.base.ListModel
import com.example.tabiyat.databinding.ProfileFragmentBinding
import com.example.tabiyat.ui.main.profile.adapter.ProfileAdapter
import com.example.tabiyat.base.OnItemClickListener
import com.example.tabiyat.ui.main.profile.viewModels.ProfileViewModel
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment(), OnItemClickListener {
    private lateinit var binding: ProfileFragmentBinding
    private val viewModel by inject<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
        editProfileClick()
    }

    private fun setRecycler(){
        val list = arrayListOf(
            ListModel(R.drawable.ic_rating,"Ваш рейтинг (5)", "150"),
            ListModel(R.drawable.ic_accepted,"Потвержденные наблюдения", "20"),
            ListModel(R.drawable.ic_detailed,"Уточненные наблюдения", "10"),
            ListModel(R.drawable.ic_waiting,"Наблюдения в ожидании", "10"),
            ListModel(R.drawable.ic_cancelled,"Отклоненные наблюдения", "20"),)
        val adapter = ProfileAdapter(list,this)
        binding.profileList.layoutManager = LinearLayoutManager(requireContext())
        binding.profileList.adapter= adapter

    }

    override fun onItemClicked(model: ListModel) {
        view.let{
            Navigation.findNavController(it!!).navigate(R.id.action_navigation_profile_to_observationsFragment)
        }
    }

    private fun editProfileClick(){
        binding.profileEditBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_navigation_profile_to_accountFragment)
        }
    }


}

