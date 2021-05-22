package kg.tabiyat.ui.main.plants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.tabiyat.base.OnDataClickListener
import kg.tabiyat.base.ProgressHolder
import kg.tabiyat.base.loadImage
import kg.tabiyat.data.model.Datum
import kg.tabiyat.databinding.PlantsListBinding
import kg.tabiyat.databinding.ProgressHolderBinding
import kg.tabiyat.db.entity.PlantsEntity

class PlantsAdapter(
    private val itemClickListener: OnDataClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    var list = arrayListOf<PlantsEntity>()

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
            else -> return PlantsHolder(
                PlantsListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun addItems(items: List<PlantsEntity>) {
        this.list.addAll(items)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        if (holder is PlantsHolder) {
            holder.onBind(item, itemClickListener)
        } else if (holder is ProgressHolder) {
            showLoadingView(holder)
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
    fun onBind(item: PlantsEntity, clickListener: OnDataClickListener) {
        textView.text = item.name!!.ru
        item.urlPick.toString()
        imageView.loadImage(item.urlPick.toString())
        itemView.setOnClickListener {
            clickListener.onItemClicked(item)
        }
    }
}

//TODO loading view holder make it or take it off

