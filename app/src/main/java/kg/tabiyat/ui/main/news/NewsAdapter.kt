package kg.tabiyat.ui.main.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.tabiyat.base.OnNewsClickListener
import kg.tabiyat.base.loadImage
import kg.tabiyat.data.model.NewsData
import kg.tabiyat.databinding.NewsListBinding

class NewsAdapter(
    private val itemClickListener: OnNewsClickListener
) : RecyclerView.Adapter<NewsHolder>() {
    var list = arrayListOf<NewsData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        return NewsHolder(
            NewsListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun addItems(items: List<NewsData>) {
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val item = list[position]
        holder.onBind(item, itemClickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class NewsHolder(binding: NewsListBinding) : RecyclerView.ViewHolder(binding.root) {
    private val textView = binding.newsTitle
    private val imageView = binding.newsImg
    fun onBind(item: NewsData, clickListener: OnNewsClickListener) {
        textView.text = item.title!!.ru
        if(item.image.toString()!=null){
        imageView.loadImage(item.image.toString())}
        itemView.setOnClickListener {
            clickListener.onItemClicked(item)
        }
    }
}


