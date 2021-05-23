package kg.tabiyat.ui.main.plants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.tabiyat.base.OnDataClickListener
import kg.tabiyat.base.loadImage
import kg.tabiyat.data.model.Datum
import kg.tabiyat.databinding.PlantsListBinding

class PlantsAdapter(
    private val itemClickListener: OnDataClickListener
) : RecyclerView.Adapter<PlantsHolder>() {
    //var list = arrayListOf<PlantsEntity>()
    var list = arrayListOf<Datum>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantsHolder {
        return PlantsHolder(
            PlantsListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun addItems(items: List<Datum>) {
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PlantsHolder, position: Int) {
        val item = list[position]
        holder.onBind(item, itemClickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class PlantsHolder(binding: PlantsListBinding) : RecyclerView.ViewHolder(binding.root) {
    private val textView = binding.plantTitleList
    private val imageView = binding.plantImgList
    fun onBind(item: Datum, clickListener: OnDataClickListener) {
        textView.text = item.name!!.ru
        item.urlPick.toString()
        imageView.loadImage(item.urlPick.toString())
        itemView.setOnClickListener {
            clickListener.onItemClicked(item)
        }
    }
}


