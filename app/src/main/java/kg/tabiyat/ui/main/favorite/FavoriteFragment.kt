package kg.tabiyat.ui.main.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.tabiyat.R
import kg.tabiyat.base.OnFavoriteClickListener
import kg.tabiyat.data.model.Favorite
import kg.tabiyat.databinding.FavoriteFragmentBinding
import kg.tabiyat.ui.main.cardDetail.CardDetailFragment
import kg.tabiyat.ui.main.favorite.adapter.FavoriteAdapter
import org.koin.android.ext.android.inject


class FavoriteFragment : Fragment(), OnFavoriteClickListener {
    private lateinit var binding: FavoriteFragmentBinding
    private lateinit var adapter: FavoriteAdapter
    private val viewModel by inject<FavoriteViewModel>()
    private lateinit var btnAnimals: Button
    private lateinit var btnInfo: Button
    private lateinit var btnPlants: Button
    private lateinit var manager: LinearLayoutManager
    private var isLoading: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavoriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
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


    override fun onItemClicked(model: Favorite) {
        val bundle = Bundle()
        bundle.putSerializable(CardDetailFragment.FAV_MODEL_KEY, model.favoritable)
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_navigation_liked_to_cardDetailFragment, bundle)
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
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.favoriteList)
    }


    var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
        ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN or ItemTouchHelper.UP
        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            Toast.makeText(requireContext(), "on Move", Toast.LENGTH_SHORT).show()
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            Toast.makeText(requireContext(), "on Swiped ", Toast.LENGTH_SHORT).show()
            //Remove swiped item from list and notify the RecyclerView
            val position = viewHolder.adapterPosition
            //viewModel.deleteFavorite(position.toString())
            adapter.list.removeAt(position)
            adapter.notifyItemRemoved(position)
        }
    }
}
//todo deleting of favorite item