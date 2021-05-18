package com.example.tabiyat.ui.main.plants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tabiyat.base.OnDataClickListener
import com.example.tabiyat.base.ProgressHolder
import com.example.tabiyat.base.loadImage
import com.example.tabiyat.data.model.Datum
import com.example.tabiyat.databinding.PlantsListBinding
import com.example.tabiyat.databinding.ProgressHolderBinding

class PlantsAdapter(
    private val itemClickListener: OnDataClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    var list = arrayListOf<Datum>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            VIEW_TYPE_ITEM -> {
                return PlantsHolder(
                    PlantsListBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            VIEW_TYPE_LOADING -> {
                return ProgressHolder(
                    ProgressHolderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else ->  return PlantsHolder(
                PlantsListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun addItems(items: List<Datum>) {
        this.list.addAll(items)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        if (holder is PlantsHolder) {
            holder.onBind(item, itemClickListener)
        } else if (holder is ProgressHolder) {
            showLoadingView(holder, )
        }
    }

    private fun showLoadingView(viewHolder: ProgressHolder) {
        viewHolder.onProgress()
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }
}

class PlantsHolder(binding: PlantsListBinding) : RecyclerView.ViewHolder(binding.root) {
    private val textView = binding.plantTitleList
    private val imageView = binding.plantImgList
    fun onBind(item: Datum, clickListener: OnDataClickListener) {
        textView.text = item.name!!.ru
        item.urlPick.toString()
        imageView.loadImage(item.urlPick.toString())
        itemView.setOnClickListener {
            clickListener.onItemClicked(item)
        }
    }
}



