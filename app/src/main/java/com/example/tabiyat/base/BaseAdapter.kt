package com.example.tabiyat.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


//
//abstract class BaseAdapter<Data : Any>(private var dataList: List<Data>,private val inflate: Inflate<ViewBinding>,) : RecyclerView.Adapter<BaseViewHolder>() {
//
//    abstract val layoutItemId: Int
//
//    override fun getItemCount(): Int = dataList.size
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
//        return BaseViewHolder(ViewBinding)
//    }
//}
//
//
//abstract class BaseViewHolder<VB : ViewBinding> constructor ( val binding:VB) : RecyclerView.ViewHolder(binding.root) {
//
//}

//open class BindingViewHolder<T : ViewBinding> private constructor(
//    val binding: T
//) : RecyclerView.ViewHolder(binding.root) {
//    constructor(
//        parent: ViewGroup,
//        creator: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> T
//    ) : this(creator(
//        LayoutInflater.from(parent.context),
//        parent,
//        false
//    ))
//}
//
//fun <T : ViewBinding> ViewGroup.viewHolderFrom(
//    creator: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> T
//): BindingViewHolder<T> = BindingViewHolder(this, creator)