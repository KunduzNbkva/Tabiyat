package kg.tabiyat.ui.main.profile

import kg.tabiyat.base.BaseFragment
import kg.tabiyat.base.loadImage
import kg.tabiyat.databinding.ProjectInfoFragmentBinding
import kg.tabiyat.ui.main.profile.viewModels.ProjectInfoViewModel
import org.koin.android.ext.android.inject

class ProjectInfoFragment : BaseFragment<ProjectInfoFragmentBinding>(ProjectInfoFragmentBinding::inflate) {
    private val viewModel by inject<ProjectInfoViewModel>()

    override fun setUpViews() {
        super.setUpViews()
        viewModel.getProjectInfo()
    }

    override fun observeData() {
        super.observeData()
        observeProjectInfo()
    }


    private fun observeProjectInfo() {
        viewModel.projectInfo.observe(viewLifecycleOwner, {
            binding.projectInfoImg.loadImage(it.information!!.image.toString())
            binding.projectInfoTitle.text = it.information!!.description!!.ru.toString()
        })
    }


}