package kg.tabiyat.ui.main.animals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.tabiyat.base.OnDataClickListener
import kg.tabiyat.base.loadImage
import kg.tabiyat.databinding.PlantsListBinding
import kg.tabiyat.data.local.db.entity.PlantsEntity
import kg.tabiyat.data.model.Datum

class AnimalsAdapter(
    private val itemClickListener: OnDataClickListener
) : RecyclerView.Adapter<AnimalsHolder>() {

    var list = arrayListOf<Datum>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalsHolder {
        return AnimalsHolder(
            PlantsListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AnimalsHolder, position: Int) {
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

class AnimalsHolder(binding: PlantsListBinding) : RecyclerView.ViewHolder(binding.root) {
    private val textView = binding.plantTitleList
    private val imageView = binding.plantImgList
    fun onBind(item: Datum, clickListener: OnDataClickListener) {
        textView.text = item.name.toString()
        imageView.loadImage(item.urlPick.toString())
        itemView.setOnClickListener {
            clickListener.onItemClicked(item)
        }
    }
}



