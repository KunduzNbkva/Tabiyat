package com.example.tabiyat.ui.main.tabiyat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.tabiyat.databinding.ExpansionCategoryBinding
import com.example.tabiyat.databinding.ListObservationBinding
import com.example.tabiyat.ui.main.tabiyat.OnCardClickListener


class ObservationAdapter(
    private var list: List<String>,
    private val itemClickListener: OnCardClickListener
) : RecyclerView.Adapter<ObservationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObservationViewHolder {
        return ObservationViewHolder(
            ListObservationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ObservationViewHolder, position: Int) {
        val model = list[position]
        holder.onBind(model, itemClickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class ObservationViewHolder(binding: ListObservationBinding) : RecyclerView.ViewHolder(binding.root) {
    private val observationTitle = binding.observationTitle
    private val observationImg =binding.observationImg
    fun onBind(string: String, itemClickListener: OnCardClickListener) {
        observationTitle.text = string
        itemView.setOnClickListener { itemClickListener.onItemClicked(string) }
    }

}