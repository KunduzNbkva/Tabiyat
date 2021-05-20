package kg.tabiyat.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kg.tabiyat.R
import kg.tabiyat.databinding.FragmentViewPagerBinding

class ViewPagerFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPagerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when(arguments!!.getInt("pos")){
            0 -> binding.introImage.setImageResource(R.drawable.intro_ic1)
            1 -> binding.introImage.setImageResource(R.drawable.intro_ic2)
            2 -> binding.introImage.setImageResource(R.drawable.intro_ic3)
        }

    }

}
