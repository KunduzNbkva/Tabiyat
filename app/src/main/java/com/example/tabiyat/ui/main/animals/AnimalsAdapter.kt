package com.example.tabiyat.ui.main.animals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tabiyat.base.*
import com.example.tabiyat.data.model.Datum
import com.example.tabiyat.databinding.PlantsListBinding

class AnimalsAdapter(
    private val list: List<Datum>,
    private val itemClickListener: OnDataClickListener
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
    private val textView = binding.plantTitleList
    private val imageView = binding.plantImgList
    fun onBind(item: Datum, clickListener: OnDataClickListener) {
                textView.text = item.name.toString()
                imageView.loadImage(item.urlPick.toString())
                itemView.setOnClickListener {
                    clickListener.onItemClicked(item) }
            }
        }



