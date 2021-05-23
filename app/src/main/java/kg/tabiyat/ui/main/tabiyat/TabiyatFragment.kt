package kg.tabiyat.ui.main.tabiyat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kg.tabiyat.R
import kg.tabiyat.base.ListModel
import kg.tabiyat.base.OnMainCardClickListener
import kg.tabiyat.databinding.TabiyatFragmentBinding
import kg.tabiyat.ui.main.tabiyat.adapters.MainListAdapter
import org.koin.android.ext.android.inject


class TabiyatFragment : Fragment(), OnMainCardClickListener {
    private lateinit var binding: TabiyatFragmentBinding
    private lateinit var adapter: MainListAdapter
    private val viewModel by inject<TabiyatViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TabiyatFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = arrayListOf(
            ListModel(R.drawable.plants, getString(R.string.plants), ""),
            ListModel(R.drawable.animals,  getString(R.string.animals), ""),
            ListModel(R.drawable.info_notes,  getString(R.string.useful_info), ""),
            ListModel(R.drawable.news, getString(R.string.news), "")
        )
        adapter = MainListAdapter(list, this)
        binding.mainList.layoutManager = LinearLayoutManager(requireContext())
        binding.mainList.adapter = adapter
    }

    override fun onItemClicked(model: ListModel, adapterPosition: Int) {
        when (adapterPosition) {
            0 -> {
                view?.let {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_navigation_tabiyat_to_plantsFragment)
                }
            }
            1 -> {
                view?.let {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_navigation_tabiyat_to_animalsFragment)
                }
            }
            2 -> {
                view?.let {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_navigation_tabiyat_to_infoFragment)
                }
            }
            3 -> {
                view?.let{
                    Navigation.findNavController(it)
                        .navigate(R.id.action_navigation_tabiyat_to_newsFragment)
                }
            }
        }
    }


}