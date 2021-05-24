package kg.tabiyat.ui.main.profile

import androidx.recyclerview.widget.LinearLayoutManager
import kg.tabiyat.base.BaseFragment
import kg.tabiyat.databinding.RatingFragmentBinding
import kg.tabiyat.ui.main.profile.adapter.RatingAdapter
import kg.tabiyat.ui.main.profile.viewModels.RatingViewModel
import org.koin.android.ext.android.inject

class RatingFragment : BaseFragment<RatingFragmentBinding>(RatingFragmentBinding::inflate){
    private val viewModel by inject<RatingViewModel>()

    override fun setUpViews() {
        super.setUpViews()
        setRatingRecycler()
    }

    private fun setRatingRecycler() {
        val list = arrayListOf(
            "Морозов Алескей",
            "Морозов Алескей",
            "Морозов Алескей",
            "Морозов Алескей",
            "Морозов Алескей"
        )
        binding.ratingList.layoutManager = LinearLayoutManager(requireContext())
        binding.ratingList.adapter = RatingAdapter(list)
    }


}