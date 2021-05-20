package kg.tabiyat.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kg.tabiyat.databinding.ProgressHolderBinding

class ProgressHolder(binding: ProgressHolderBinding) : RecyclerView.ViewHolder(binding.root) {
    private val progressBar = binding.progressHolder
    fun onProgress() {
        progressBar.visibility = View.VISIBLE
    }

}
