package kg.tabiyat.ui.main.addAnimalsObservation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.tabiyat.base.OnDataClickListener
import kg.tabiyat.databinding.ExListBinding
import kg.tabiyat.data.model.Datum
import kg.tabiyat.ui.main.addObservation.adapter.ChoosePlantHolder

class ChooseAnimalsAdapter(
    private val itemClickListener: OnDataClickListener
) : RecyclerView.Adapter<ChoosePlantHolder>() {

    var list = arrayListOf<Datum>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoosePlantHolder {
        return ChoosePlantHolder(
            ExListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun addItems(items: List<Datum>) {
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ChoosePlantHolder, position: Int) {
        val item = list[position]
        holder.onBind(item, itemClickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class ChoosePlantHolder(binding: ExListBinding) : RecyclerView.ViewHolder(binding.root) {
    private val textView = binding.listTitle
    fun onBind(item: Datum, clickListener: OnDataClickListener) {
        textView.text = item.name!!.ru.toString()
        itemView.setOnClickListener { clickListener.onItemClicked(item) }
    }
}

