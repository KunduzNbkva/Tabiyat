package com.example.tabiyat.ui.main.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.tabiyat.R
import com.example.tabiyat.base.uiModels.ListModel
import com.example.tabiyat.databinding.ProfileListviewBinding
import com.example.tabiyat.ui.main.tabiyat.OnItemClickListener

class ProfileAdapter(
    private val list: ArrayList<ListModel>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ProfileViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(
            ProfileListviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val item = list[position]
        holder.onBind(item, itemClickListener)

    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class ProfileViewHolder(binding: ProfileListviewBinding) : RecyclerView.ViewHolder(binding.root) {
    private val textView = binding.profileListTitle
    private val imageView = binding.profileListImg
    private val countView = binding.profileListCount

    fun onBind(item: ListModel, clickListener: OnItemClickListener) {
        textView.text = item.nameTxt
        imageView.setImageResource(item.srcImage)
        countView.text = item.count

        itemView.setOnClickListener {
            when (adapterPosition) {
                0 -> Navigation.findNavController(it)
                    .navigate(R.id.action_navigation_profile_to_ratingFragment)
                else -> clickListener.onItemClicked(item)
            }
        }
    }
}


