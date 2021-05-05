package com.example.tabiyat.ui.main.liked.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tabiyat.databinding.PlantsListBinding
import com.example.tabiyat.ui.main.tabiyat.OnCardClickListener

class LikedAdapter(
    private var list: List<String>,
    private val itemClickListener: OnCardClickListener
) : RecyclerView.Adapter<LikedViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LikedViewHolder {
       return LikedViewHolder(PlantsListBinding.inflate(
           LayoutInflater.from(parent.context),parent,false
       ))
    }

    override fun onBindViewHolder(holder: LikedViewHolder, position: Int) {
        val plant = list[position]
        holder.onBind(plant, itemClickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class LikedViewHolder(binding: PlantsListBinding) : RecyclerView.ViewHolder(binding.root) {
    private val cardTitle = binding.plantTitle
    private var cardDep = binding.plantDepartment
    private val cardImg = binding.plantImg
    fun onBind(card: String, itemClickListener: OnCardClickListener) {
        cardTitle.text = card
        itemView.setOnClickListener { itemClickListener.onItemClicked(card) }
    }

}
