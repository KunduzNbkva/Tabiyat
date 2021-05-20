package kg.tabiyat.ui.main.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.tabiyat.databinding.RatingListBinding

class RatingAdapter(private var list: ArrayList<String>) :
    RecyclerView.Adapter<RatingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        return RatingViewHolder(
            RatingListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}


class RatingViewHolder(binding: RatingListBinding) : RecyclerView.ViewHolder(binding.root) {
    val ratingName = binding.ratingTitle
    fun onBind(s: String) {
        ratingName.text = s
    }
}