package kg.tabiyat.ui.main.tabiyat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.tabiyat.base.ListModel
import kg.tabiyat.base.OnMainCardClickListener
import kg.tabiyat.databinding.MainCardsListBinding

class MainListAdapter(
    private val list: ArrayList<ListModel>,
    private val itemClickListener: OnMainCardClickListener
) : RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            MainCardsListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = list[position]
        holder.onBind(item, itemClickListener)

    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class MainViewHolder(binding: MainCardsListBinding) : RecyclerView.ViewHolder(binding.root) {
    private val textView = binding.mainListTitle
    private val imageView = binding.mainListImg
    fun onBind(item: ListModel, clickListener: OnMainCardClickListener) {
        textView.text = item.nameTxt
        imageView.setImageResource(item.srcImage)
        itemView.setOnClickListener { clickListener.onItemClicked(item, adapterPosition) }
    }


}