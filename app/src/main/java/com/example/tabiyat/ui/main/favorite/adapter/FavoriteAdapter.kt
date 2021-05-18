package com.example.tabiyat.ui.main.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tabiyat.databinding.PlantsListBinding
import com.example.tabiyat.base.OnCardClickListener
import com.example.tabiyat.base.OnFavoriteClickListener
import com.example.tabiyat.data.model.Favorite

class FavoriteAdapter(
    private var list: List<Favorite>,
    private val itemClickListener: OnFavoriteClickListener
) : RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteViewHolder {
       return FavoriteViewHolder(PlantsListBinding.inflate(
           LayoutInflater.from(parent.context),parent,false
       ))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val plant = list[position]
        holder.onBind(plant, itemClickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class FavoriteViewHolder(binding: PlantsListBinding) : RecyclerView.ViewHolder(binding.root) {
    private val favoriteTitle = binding.plantTitleList
    private var favoriteDep = binding.plantDepartmentList
    private val favoriteImg = binding.plantImgList
    fun onBind(favorite: Favorite, itemClickListener: OnFavoriteClickListener) {
        favoriteTitle.text = favorite.favoritableId.toString()
        itemView.setOnClickListener { itemClickListener.onItemClicked(favorite) }
    }

}
