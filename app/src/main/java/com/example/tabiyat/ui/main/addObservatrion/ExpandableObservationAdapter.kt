package com.example.tabiyat.ui.main.addObservatrion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tabiyat.databinding.ExAddobsrvListBinding

class ExpandableObservationAdapter(private var list: ArrayList<String>) : RecyclerView.Adapter<ExpandableObservationAdapter.ObservationAddHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObservationAddHolder {
        return  ObservationAddHolder(ExAddobsrvListBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ObservationAddHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
       return list.size
    }



    class ObservationAddHolder(binding:ExAddobsrvListBinding): RecyclerView.ViewHolder(binding.root){
        val textView = binding.mapExListTitle
        fun onBind(text:String) {
            textView.text  = text
        }
    }

}

