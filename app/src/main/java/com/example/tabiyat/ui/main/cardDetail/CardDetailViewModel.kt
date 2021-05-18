package com.example.tabiyat.ui.main.cardDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabiyat.App
import com.example.tabiyat.data.model.Favorite
import com.example.tabiyat.data.model.FavoriteModel
import com.example.tabiyat.data.model.Status
import com.example.tabiyat.data.repository.FavoriteRepository
import kotlinx.coroutines.launch

class CardDetailViewModel(private var repository: FavoriteRepository) : ViewModel() {
    var favoriteResponse:MutableLiveData<Favorite> = MutableLiveData()
    private var isFavorite = false

    fun isFavorite(): Boolean {
        return isFavorite
    }

    private fun setFavorite(favorite: Boolean) {
        isFavorite = favorite
    }

    fun createFavorite(favoriteModel: FavoriteModel) {
       viewModelScope.launch {
           repository.createFavorite(favoriteModel).observeForever{
               when (it.status) {
                   Status.SUCCESS -> {
                       favoriteResponse.postValue(it.data!!.data!!.favorites!![0])
                       setFavorite(true)
                   }
                   Status.ERROR -> error(it.message.toString())
                   Status.LOADING ->null
               }
           }
       }
    }

}