package kg.tabiyat.ui.main.info

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.tabiyat.R
import kg.tabiyat.base.BaseFragment
import kg.tabiyat.base.OnDataClickListener
import kg.tabiyat.databinding.InfoFragmentBinding
import kg.tabiyat.data.local.db.entity.PlantsEntity
import kg.tabiyat.data.model.Datum
import org.koin.android.ext.android.inject

class InfoFragment : BaseFragment<InfoFragmentBinding>(InfoFragmentBinding::inflate),
    OnDataClickListener, View.OnClickListener {
    private lateinit var adapter: InfoAdapter
    private var clickCounter: Int = 0
    private val viewModel by inject<InfoViewModel>()


    override fun setUpViews() {
        super.setUpViews()
        viewModel.resetPage()
        loadData()
        setRecycler()
    }

    override fun observeData() {
        super.observeData()
//        viewModel.getLocalPlantsList().observe(viewLifecycleOwner){
//            adapter.addItems(it)
//        }
        viewModel.infoList.observe(viewLifecycleOwner, {
            adapter.addItems(it)
        })
    }

    private fun setRecycler() {
        adapter = InfoAdapter(this)
        val manager = LinearLayoutManager(requireContext())
        binding.infoRecycler.layoutManager = manager
        binding.infoRecycler.adapter = adapter
        binding.infoRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (manager.findLastCompletelyVisibleItemPosition() == adapter.list.size - 1) {
                    loadData()
                }
            }
        })
    }

    private fun loadData() {
        viewModel.getInfoList()
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
                .navigate(R.id.action_infoFragment_to_plantsDetailFragment)
        }
    }


}