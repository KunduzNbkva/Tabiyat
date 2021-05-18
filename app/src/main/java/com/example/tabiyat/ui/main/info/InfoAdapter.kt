package com.example.tabiyat.ui.main.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tabiyat.base.ListModel
import com.example.tabiyat.base.OnItemClickListener
import com.example.tabiyat.databinding.InfoListBinding

class InfoAdapter(
    private val list: ArrayList<ListModel>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<InfoHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoHolder {
        return InfoHolder(
            InfoListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: InfoHolder, position: Int) {
        val item = list[position]
        holder.onBind(item, itemClickListener)

    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class InfoHolder(binding: InfoListBinding) : RecyclerView.ViewHolder(binding.root) {
    private val textView = binding.infoTitle
    private val imageView = binding.infoImg
    fun onBind(item: ListModel, clickListener: OnItemClickListener) {
                textView.text = item.nameTxt
                imageView.setImageResource(item.srcImage)
                itemView.setOnClickListener {
                    clickListener.onItemClicked(item) }
            }
        }



