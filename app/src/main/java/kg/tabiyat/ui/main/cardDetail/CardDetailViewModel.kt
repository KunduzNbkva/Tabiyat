package kg.tabiyat.ui.main.cardDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.tabiyat.data.model.Favorite
import kg.tabiyat.data.model.FavoriteModel
import kg.tabiyat.data.model.Status
import kg.tabiyat.data.repository.FavoriteRepository
import kotlinx.coroutines.launch

class CardDetailViewModel(private var repository: FavoriteRepository) : ViewModel() {
    var favoriteResponse: MutableLiveData<Favorite> = MutableLiveData()
    private var isFavorite = false

    fun isFavorite(): Boolean {
        return isFavorite
    }

    private fun setFavorite(favorite: Boolean) {
        isFavorite = favorite
    }

    fun createFavorite(favoriteModel: FavoriteModel) {
        viewModelScope.launch {
            repository.createFavorite(favoriteModel).observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
                        favoriteResponse.postValue(it.data!!.data!!.favorites!![0])
                        setFavorite(true)
                    }
                    Status.ERROR -> error(it.message.toString())
                    Status.LOADING -> null
                }
            }
        }
    }

    fun deleteFavorite(id: Int) {
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

}