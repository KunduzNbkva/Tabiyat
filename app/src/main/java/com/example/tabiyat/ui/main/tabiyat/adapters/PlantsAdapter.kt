package com.example.tabiyat.ui.main.tabiyat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tabiyat.base.OnPlantsClickListener
import com.example.tabiyat.base.Plant
import com.example.tabiyat.data.model.Datum
import com.example.tabiyat.databinding.PlantsListBinding


class PlantsAdapter(
    private var list: ArrayList<Plant>,
    private val itemClickListener: OnPlantsClickListener
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PlantsListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plant = list[position]
        holder.onBind(plant, itemClickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

//    fun addItems(items: List<Datum>) {
//        this.list = items
//        notifyDataSetChanged()
//    }

}

class ViewHolder(binding: PlantsListBinding) : RecyclerView.ViewHolder(binding.root) {
    private val plantsTitle = binding.plantTitle
    private var plantDep = binding.plantDepartment
 //   private val plantImg = binding.plantImg
    fun onBind(plant: Plant, itemClickListener: OnPlantsClickListener) {
        plantsTitle.text = plant.title
      //  plantImg.setImageResource(plant.img)
//        Glide.with(itemView)
//            .load(plant.urlPick)
//            .centerCrop()
//            .into(plantImg)
        itemView.setOnClickListener { itemClickListener.onItemClicked(plant) }
    }

}