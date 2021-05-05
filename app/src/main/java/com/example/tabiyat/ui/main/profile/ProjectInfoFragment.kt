package com.example.tabiyat.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tabiyat.databinding.ProjectInfoFragmentBinding
import com.example.tabiyat.ui.main.profile.viewModels.ProjectInfoViewModel
import org.koin.android.ext.android.inject

class ProjectInfoFragment : Fragment() {
    private lateinit var binding: ProjectInfoFragmentBinding
    private val viewModel by inject<ProjectInfoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProjectInfoFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


}