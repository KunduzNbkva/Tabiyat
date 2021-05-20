package kg.tabiyat.ui.main.animals

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.tabiyat.R
import kg.tabiyat.base.BaseFragment
import kg.tabiyat.base.OnDataClickListener
import kg.tabiyat.data.model.Datum
import kg.tabiyat.databinding.AnimalsFragmentBinding

class AnimalsFragment() : BaseFragment<AnimalsFragmentBinding>(AnimalsFragmentBinding::inflate),
    OnDataClickListener, View.OnClickListener {
    private lateinit var adapter: AnimalsAdapter
    private var clickCounter: Int = 0
    private val viewModel by inject<AnimalsViewModel>()


    override fun setUpViews() {
        super.setUpViews()
        viewModel.resetPage()
        loadData()
        setRecycler()
    }

    override fun observeData() {
        super.observeData()
        viewModel.animalsList.observe(viewLifecycleOwner, {
            adapter.addItems(it)
        })
    }

    private fun setRecycler() {
        adapter = AnimalsAdapter(this)
        val manager = LinearLayoutManager(requireContext())
        binding.animalsRecycler.layoutManager = manager
        binding.animalsRecycler.adapter = adapter
        binding.animalsRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (manager.findLastCompletelyVisibleItemPosition() == adapter.list.size - 1) {
                    loadData()
                }
            }
        })
    }

    private fun loadData() {
        viewModel.getAnimalsList()
    }


    override fun onClick(v: View?) {
        clickCounter++
        if (clickCounter % 2 == 0) {
            binding.searchCardView.visibility = View.GONE
        } else binding.searchCardView.visibility = View.VISIBLE
    }

    override fun onItemClicked(model: Datum) {
        val bundle = Bundle()
        model.let {
            bundle.putSerializable("model", it)
            bundle.putString("type", "animals")
        }
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_animalsFragment_to_plantsDetailFragment, bundle)
        }
    }


}