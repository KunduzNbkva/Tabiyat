package kg.tabiyat.ui.main.addObservatrion.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.tabiyat.data.model.Datum
import kg.tabiyat.data.model.Status
import kg.tabiyat.data.repository.PlantsRepository
import kotlinx.coroutines.launch

class ChoosePlantViewModel(private val plantsRepository: PlantsRepository): ViewModel() {
    var plantsList = MutableLiveData<List<Datum>>()
    private var page = 0
    private var hasNext = true

    fun getPlantsList(){
        if (!hasNext) return
        viewModelScope.launch {
            plantsRepository.getPlantsList(page).observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
                        val list = it.data!!.data!!.plants!!.data!!
                        plantsList.postValue(list)
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