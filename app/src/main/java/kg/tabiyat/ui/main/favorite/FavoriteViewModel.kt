package kg.tabiyat.ui.main.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.tabiyat.data.model.Favorite
import kg.tabiyat.data.model.Status
import kg.tabiyat.data.repository.FavoriteRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(private var repository: FavoriteRepository) : ViewModel() {
    var favoritesList = MutableLiveData<List<Favorite>>()
    private var page = 0
    private var hasNext = true
    private var isFavorite = false


    fun getFavoritesList() {
        if (!hasNext) return
        viewModelScope.launch {
            repository.getFavoritesList(page).observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
                        val list = it.data!!.data!!.favorites!!
                        favoritesList.postValue(list)
                        page++
                        if (list.size < 20) hasNext = false
                    }
                    Status.ERROR -> error(it.message.toString())
                    Status.LOADING -> null
                }
            }
        }
    }

    fun deleteFavorite(id: String) {
        viewModelScope.launch {
            repository.deleteFavorite(id).observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
                        setFavorite(false)
                    }
                    Status.ERROR -> error(it.data!!.status.toString())
                    Status.LOADING -> null
                }
            }
        }
    }

    private fun setFavorite(favorite: Boolean) {
        isFavorite = favorite
    }

    fun resetPage() {
        page = 0
    }

}