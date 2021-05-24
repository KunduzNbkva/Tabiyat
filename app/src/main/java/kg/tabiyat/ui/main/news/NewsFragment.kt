package kg.tabiyat.ui.main.news

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.tabiyat.R
import kg.tabiyat.base.BaseFragment
import kg.tabiyat.base.OnNewsClickListener
import kg.tabiyat.data.model.NewsData
import kg.tabiyat.databinding.NewsFragmentBinding
import org.koin.android.ext.android.inject

class NewsFragment : BaseFragment<NewsFragmentBinding>(NewsFragmentBinding::inflate),
    OnNewsClickListener {
    private val viewModel by inject<NewsViewModel>()
    private lateinit var adapter: NewsAdapter
    private lateinit var manager: LinearLayoutManager
    private var isLoading: Boolean = false

    override fun setUpViews() {
        super.setUpViews()
        viewModel.resetPage()
        loadData()
        setRecycler()
    }

    override fun observeData() {
        super.observeData()
        observeNews()
    }

    private fun setRecycler() {
        adapter = NewsAdapter(this)
        manager = LinearLayoutManager(requireContext())
        binding.newsList.layoutManager = manager
        binding.newsList.adapter = adapter
        binding.newsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (manager.findLastCompletelyVisibleItemPosition() == adapter.list.size - 1) {
                    loadData()
                    isLoading = true
                }
            }
        })
    }

    private fun loadData() {
        viewModel.getNewsList()
    }

    private fun observeNews() {
        viewModel.newsList.observe(viewLifecycleOwner, {
            adapter.addItems(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onItemClicked(model: NewsData) {
        val bundle = Bundle()
        model.let {
            bundle.putSerializable("news", it)
        }
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_newsFragment_to_newsDetailFragment, bundle)
        }
    }


}