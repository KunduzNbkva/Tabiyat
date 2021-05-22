package kg.tabiyat.ui.main.plants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.tabiyat.data.model.Datum
import kg.tabiyat.data.model.Status
import kg.tabiyat.data.repository.PlantsRepository
import kg.tabiyat.db.entity.PlantsEntity
import kotlinx.coroutines.launch

class PlantsViewModel(private val plantsRepository: PlantsRepository) : ViewModel() {
    var plantsList = MutableLiveData<List<Datum>>()
    private var page = 0
    private var hasNext = true

    fun getPlantsList() {
        if (!hasNext) return
        viewModelScope.launch {
            plantsRepository.getPlantsList(page).observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
                        val list = it.data!!.data!!.plants!!.data!!
                        plantsList.postValue(list)
                        plantsRepository.db.mainDao().insertPlantsList(list as List<PlantsEntity>)
                        page++
                        if (list.size < 20) hasNext = false
                    }
                    Status.ERROR -> error(it.message.toString())
                    Status.LOADING -> null
                }
            }
        }
    }

    fun getLocalPlantsList() : LiveData<List<PlantsEntity>>{
        return plantsRepository.getLocalPlantsList()
    }

    fun resetPage() {
        page = 0
    }


}