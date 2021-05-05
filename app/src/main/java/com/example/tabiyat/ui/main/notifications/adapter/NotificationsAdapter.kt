package com.example.tabiyat.ui.main.notifications.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tabiyat.databinding.NotificationsItemBinding

//TODO change model of list

class NotificationsAdapter(private val list: ArrayList<String>) :
    RecyclerView.Adapter<NotificationsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsViewHolder {
        return NotificationsViewHolder(
            NotificationsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotificationsViewHolder, position: Int) {
        val item = list[position]
        holder.onBind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class NotificationsViewHolder(binding: NotificationsItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private val notificationTitle = binding.notificationTitle
    private val notificationSubTitle = binding.notificationSubtitle
    private val notificationDateTime = binding.notificationDateTime

    fun onBind(model: String) {
        notificationTitle.text = model
    }

}

