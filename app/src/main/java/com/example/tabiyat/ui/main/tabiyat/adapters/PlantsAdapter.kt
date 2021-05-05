package com.example.tabiyat.ui.main.tabiyat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tabiyat.databinding.PlantsListBinding
import com.example.tabiyat.ui.main.tabiyat.OnPlantsClickListener
import com.example.tabiyat.base.uiModels.PlantsModel


class PlantsAdapter(
    private var list: List<PlantsModel>,
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
}

class ViewHolder(binding: PlantsListBinding) : RecyclerView.ViewHolder(binding.root) {
    private val plantsTitle = binding.plantTitle
    private var plantDep = binding.plantDepartment
    private val plantImg = binding.plantImg
    fun onBind(plant: PlantsModel, itemClickListener: OnPlantsClickListener) {
        plantsTitle.text = plant.title
        plantDep.text = plant.department
        plantImg.setImageResource(plant.img)
        itemView.setOnClickListener { itemClickListener.onItemClicked(plant) }
    }

}