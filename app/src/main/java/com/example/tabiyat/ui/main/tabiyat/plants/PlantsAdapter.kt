package com.example.tabiyat.ui.main.tabiyat.plants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tabiyat.base.ListModel
import com.example.tabiyat.base.OnMainCardClickListener
import com.example.tabiyat.databinding.PlantsListBinding

class PlantsAdapter(
    private val list: ArrayList<ListModel>,
    private val itemClickListener: OnMainCardClickListener
) : RecyclerView.Adapter<PlantsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantsHolder {
        return PlantsHolder(
            PlantsListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
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
    private val textView = binding.plantTitle
    private val imageView = binding.plantImg
    fun onBind(item: ListModel, clickListener: OnMainCardClickListener) {
                textView.text = item.nameTxt
                imageView.setImageResource(item.srcImage)
                itemView.setOnClickListener {
                    clickListener.onItemClicked(item,adapterPosition) }
            }
        }



