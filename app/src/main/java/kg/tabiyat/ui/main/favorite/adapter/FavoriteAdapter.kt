package kg.tabiyat.ui.main.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.tabiyat.base.OnFavoriteClickListener
import kg.tabiyat.base.loadImage
import kg.tabiyat.data.model.Favorite
import kg.tabiyat.databinding.PlantsListBinding

class FavoriteAdapter(
    private val itemClickListener: OnFavoriteClickListener
) : RecyclerView.Adapter<FavoriteViewHolder>() {

    var list = arrayListOf<Favorite>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteViewHolder {
        return FavoriteViewHolder(
            PlantsListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val plant = list[position]
        holder.onBind(plant, itemClickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addItems(items: List<Favorite>) {
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    private fun removeItem(position: Int) {
        list.removeAt(position)
    }
}

class FavoriteViewHolder(binding: PlantsListBinding) : RecyclerView.ViewHolder(binding.root) {
    private val favoriteTitle = binding.plantTitleList
    private var favoriteDep = binding.plantDepartmentList
    private val favoriteImg = binding.plantImgList
    fun onBind(favorite: Favorite, itemClickListener: OnFavoriteClickListener) {
        favoriteTitle.text = favorite.favoritable!!.name!!.ru
        favoriteDep.text = favorite.favoritable.genusId.toString()
        favoriteImg.loadImage(favorite.favoritable.urlPick.toString())
        itemView.setOnClickListener { itemClickListener.onItemClicked(favorite) }
    }

}
