package kg.tabiyat.ui.main.animals

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.tabiyat.data.model.Datum
import kg.tabiyat.data.model.Status
import kg.tabiyat.data.repository.AnimalsRepository
import kotlinx.coroutines.launch

class AnimalsViewModel(private val animalsRepository: AnimalsRepository) : ViewModel() {
    var animalsList = MutableLiveData<List<Datum>>()
    private var page = 0
    private var hasNext = true

    fun getAnimalsList() {
        if (!hasNext) return
        viewModelScope.launch {
            animalsRepository.getAnimalsList(page).observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
                        val list = it.data!!.data!!.plants!!.data!!
//                        animalsList.postValue(list)
                        page++
                        if (list.size < 20) hasNext = false
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