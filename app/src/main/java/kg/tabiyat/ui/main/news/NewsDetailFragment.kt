package kg.tabiyat.ui.main.news

import kg.tabiyat.base.BaseFragment
import kg.tabiyat.base.loadImage
import kg.tabiyat.data.model.NewsData
import kg.tabiyat.databinding.FragmentNewsDetailBinding

class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>(FragmentNewsDetailBinding::inflate) {
    private lateinit var model: NewsData

    override fun setUpViews() {
        super.setUpViews()
        model = arguments?.getSerializable("news") as NewsData
        setData()
    }

    override fun observeData() {
        super.observeData()
    }

    private fun setData() {
        binding.newsDetailTitle.text = model.title!!.ru
        if(model.image.toString()!=null){
        binding.newsDetailImg.loadImage(model.image.toString())}
        binding.newsContent.text = model.content!!.ru
    }


}