package kg.tabiyat.ui.main.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.tabiyat.base.OnDataClickListener
import kg.tabiyat.base.loadImage
import kg.tabiyat.data.model.Datum
import kg.tabiyat.databinding.InfoListBinding

class InfoAdapter(private val itemClickListener: OnDataClickListener) :
    RecyclerView.Adapter<InfoHolder>() {

    val list = arrayListOf<Datum>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoHolder {
        return InfoHolder(
            InfoListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: InfoHolder, position: Int) {
        val item = list[position]
        holder.onBind(item, itemClickListener)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addItems(items: List<Datum>) {
        this.list.addAll(items)
        notifyDataSetChanged()
    }
}

class InfoHolder(binding: InfoListBinding) : RecyclerView.ViewHolder(binding.root) {
    private val textView = binding.infoTitle
    private val imageView = binding.infoImg
    fun onBind(item: Datum, clickListener: OnDataClickListener) {
        textView.text = item.name.toString()
        imageView.loadImage(item.urlPick.toString())
        itemView.setOnClickListener {
            clickListener.onItemClicked(item)
        }
    }
}



