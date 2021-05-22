package kg.tabiyat.ui.main.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.tabiyat.base.OnDataClickListener
import kg.tabiyat.base.loadImage
import kg.tabiyat.data.model.Datum
import kg.tabiyat.data.model.Favorite
import kg.tabiyat.databinding.PlantsListBinding
import kg.tabiyat.db.entity.PlantsEntity

class FavoriteAdapter(
    private val itemClickListener: OnDataClickListener
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
        val plant = list[position].favoritable!!
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
    fun onBind(favorite: PlantsEntity, itemClickListener: OnDataClickListener) {
        favoriteTitle.text = favorite.name!!.ru
        favoriteDep.text = favorite.genusId.toString()
        favoriteImg.loadImage(favorite.urlPick.toString())
        itemView.setOnClickListener { itemClickListener.onItemClicked(favorite) }
    }

}
