package kg.tabiyat.ui.main.addObservation.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.tabiyat.base.OnDeleteListener
import kg.tabiyat.databinding.ListImagesBinding

class ImagesAdapter(private val onClickListener: OnDeleteListener) : RecyclerView.Adapter<ImagesViewHolder>() {

    var list = arrayListOf<Uri>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        return ImagesViewHolder(
            ListImagesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.onBind(list[position],onClickListener)
    }

    override fun getItemCount() = list.size

    fun addImage(item: Uri) {
        this.list.add(item)
        notifyDataSetChanged()
    }

    fun removeImage(item: Uri) {
        this.list.remove(item)
        notifyDataSetChanged()
    }

    fun getList(): List<Uri> {
        return list
    }

}


class ImagesViewHolder(binding: ListImagesBinding) : RecyclerView.ViewHolder(binding.root) {

    val imageView = binding.observationImg

    fun onBind(s: Uri, listener: OnDeleteListener) {
        imageView.setImageURI(s)
        itemView.setOnClickListener{
            listener.onItemClicked(s)
        }
    }
}