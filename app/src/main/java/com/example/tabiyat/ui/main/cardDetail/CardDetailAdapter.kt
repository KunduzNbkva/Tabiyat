package com.example.tabiyat.ui.main.cardDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tabiyat.databinding.ExSubListBinding


class CardDetailAdapter(private var list: ArrayList<String>) : RecyclerView.Adapter<CardDetailAdapter.ObservationAddHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObservationAddHolder {
        return  ObservationAddHolder(
            ExSubListBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ObservationAddHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }



    class ObservationAddHolder(binding: ExSubListBinding): RecyclerView.ViewHolder(binding.root){
        val textView = binding.childTextTitle
        fun onBind(text:String) {
            textView.text  = text
        }
    }

}