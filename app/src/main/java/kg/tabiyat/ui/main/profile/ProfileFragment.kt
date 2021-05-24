package kg.tabiyat.ui.main.profile

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kg.tabiyat.R
import kg.tabiyat.base.BaseFragment
import kg.tabiyat.base.ListModel
import kg.tabiyat.base.OnItemClickListener
import kg.tabiyat.base.loadImage
import kg.tabiyat.databinding.ProfileFragmentBinding
import kg.tabiyat.ui.main.profile.adapter.ProfileAdapter
import kg.tabiyat.ui.main.profile.viewModels.ProfileViewModel
import org.koin.android.ext.android.inject

class ProfileFragment : BaseFragment<ProfileFragmentBinding>(ProfileFragmentBinding::inflate), OnItemClickListener {
    private val viewModel by inject<ProfileViewModel>()
    private lateinit var bundle: Bundle


    override fun setUpViews() {
        super.setUpViews()
        bundle = Bundle()
        viewModel.getUserData()
        setRecycler()
        editProfileClick()
    }

    override fun observeData() {
        super.observeData()
        observeUserData()
    }


    private fun observeUserData() {
        viewModel.user.observe(viewLifecycleOwner, {
            bundle.putSerializable(AccountFragment.USER_KEY, it)
            binding.profileName.text = it.fullName.toString()
            binding.profileImg.loadImage(it.avatar.toString())
        })
    }



    private fun setRecycler() {
        val list = arrayListOf(
            ListModel(R.drawable.ic_rating, "Ваш рейтинг (5)", "150"),
            ListModel(R.drawable.ic_accepted, "Потвержденные наблюдения", "20"),
            ListModel(R.drawable.ic_detailed, "Уточненные наблюдения", "10"),
            ListModel(R.drawable.ic_waiting, "Наблюдения в ожидании", "10"),
            ListModel(R.drawable.ic_cancelled, "Отклоненные наблюдения", "20"),
        )
        val adapter = ProfileAdapter(list, this)
        binding.profileList.layoutManager = LinearLayoutManager(requireContext())
        binding.profileList.adapter = adapter

    }

    override fun onItemClicked(model: ListModel) {
        view.let {
            Navigation.findNavController(it!!)
                .navigate(R.id.action_navigation_profile_to_observationsFragment)
        }
    }

    private fun editProfileClick() {
        binding.profileEditBtn.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_navigation_profile_to_accountFragment,bundle)
        }
    }


}

