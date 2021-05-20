package kg.tabiyat.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kg.tabiyat.base.loadImage
import kg.tabiyat.databinding.ProjectInfoFragmentBinding
import kg.tabiyat.ui.main.profile.viewModels.ProjectInfoViewModel
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeProjectInfo()
        viewModel.getProjectInfo()
    }


    private fun observeProjectInfo() {
        viewModel.projectInfo.observe(viewLifecycleOwner, {
            binding.projectInfoImg.loadImage(it.information!!.image.toString())
            binding.projectInfoTitle.text = it.information!!.description.toString()
        })
    }


}