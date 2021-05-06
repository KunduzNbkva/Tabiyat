package com.example.tabiyat.ui.main.tabiyat.animals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tabiyat.base.*
import com.example.tabiyat.databinding.PlantsListBinding

class AnimalsAdapter(
    private val list: ArrayList<ListModel>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<AnimalsHolder>() {
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
}

class AnimalsHolder(binding: PlantsListBinding) : RecyclerView.ViewHolder(binding.root) {
    private val textView = binding.plantTitle
    private val imageView = binding.plantImg
    fun onBind(item: ListModel, clickListener: OnItemClickListener) {
                textView.text = item.nameTxt
                imageView.setImageResource(item.srcImage)
                itemView.setOnClickListener {
                    clickListener.onItemClicked(item) }
            }
        }



