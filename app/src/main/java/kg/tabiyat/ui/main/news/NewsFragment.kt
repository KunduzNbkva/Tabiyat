package kg.tabiyat.ui.main.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kg.tabiyat.databinding.NewsFragmentBinding
import org.koin.android.ext.android.inject

class NewsFragment : Fragment() {
    private lateinit var binding: NewsFragmentBinding
    private  val viewModel by inject<NewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewsFragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}