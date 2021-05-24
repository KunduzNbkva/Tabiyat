package kg.tabiyat.ui.main.favorite

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.tabiyat.R
import kg.tabiyat.base.BaseFragment
import kg.tabiyat.base.OnDataClickListener
import kg.tabiyat.databinding.FavoriteFragmentBinding
import kg.tabiyat.data.model.Datum
import kg.tabiyat.ui.main.cardDetail.CardDetailFragment
import kg.tabiyat.ui.main.favorite.adapter.FavoriteAdapter
import org.koin.android.ext.android.inject


class FavoriteFragment : BaseFragment<FavoriteFragmentBinding>(FavoriteFragmentBinding::inflate), OnDataClickListener {
    private lateinit var adapter: FavoriteAdapter
    private val viewModel by inject<FavoriteViewModel>()
    private lateinit var btnAnimals: Button
    private lateinit var btnInfo: Button
    private lateinit var btnPlants: Button
    private lateinit var manager: LinearLayoutManager
    private var isLoading: Boolean = false
    private var favoritableId: Int = 0


    override fun setUpViews() {
        super.setUpViews()
        viewModel.resetPage()
        setRecycler()
        btnAnimals = binding.favoriteAnimalsBtn
        btnInfo = binding.favoriteInfoBtn
        btnPlants = binding.favoritePlantsBtn
        viewModel.getFavoritesList()
        setOnBtnTouch(btnAnimals)
        setOnBtnTouch(btnInfo)
        setOnBtnTouch(btnPlants)
    }

    override fun observeData() {
        super.observeData()
        observe()
    }


    override fun onItemClicked(model: Datum) {
            val bundle = Bundle()
            bundle.putSerializable(CardDetailFragment.FAV_MODEL_KEY, model)
            bundle.putInt("favoritable_id",favoritableId!!)
            view?.let {
                Navigation.findNavController(it)
                    .navigate(R.id.action_navigation_liked_to_favoriteDetailFragment, bundle)
            }

    }


    private fun setOnBtnTouch(button: Button) {
        button.setOnClickListener {
            button.isSelected = !button.isSelected
        }
    }

    private fun observe() {
        viewModel.favoritesList.observe(viewLifecycleOwner, {
            if (it == null) {
                binding.favoriteBtnLayout.visibility = View.VISIBLE
                binding.favoriteList.visibility = View.VISIBLE
                binding.noFavoriteLayout.visibility = View.GONE
            } else
                adapter.addItems(it)
                favoritableId = it?.get(0)!!.id!!
        })
    }


    private fun setRecycler() {
        adapter = FavoriteAdapter(this)
        manager = LinearLayoutManager(requireContext())
        binding.favoriteList.layoutManager = LinearLayoutManager(requireContext())
        binding.favoriteList.adapter = adapter
        binding.favoriteBtnLayout.visibility = View.VISIBLE
        binding.favoriteList.visibility = View.VISIBLE
        binding.noFavoriteLayout.visibility = View.GONE
        binding.favoriteList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (manager.findLastCompletelyVisibleItemPosition() == adapter.list.size - 1) {
                    viewModel.getFavoritesList()
                    isLoading = true
                }
            }
        })
    }

}
