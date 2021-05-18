package com.example.tabiyat.ui.main.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabiyat.data.model.Favorite
import com.example.tabiyat.data.model.Status
import com.example.tabiyat.data.repository.FavoriteRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(private var repository: FavoriteRepository) : ViewModel() {
    var favoritesList = MutableLiveData<List<Favorite>>()
    private var page = 0
    private var hasNext = true

    fun getFavoritesList(){
        if (!hasNext) return
        viewModelScope.launch {
            repository.getFavoritesList(page).observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
                        val list = it.data!!.data!!.favorites!!
                        favoritesList.postValue(list)
                        page++
                        if (list.size < 20) hasNext=false
                    }
                    Status.ERROR -> error(it.message.toString())
                    Status.LOADING -> null
                }
            }
        }
    }

    fun resetPage() {
        page = 0
    }

}