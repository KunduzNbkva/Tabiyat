package kg.tabiyat.ui.main.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.tabiyat.data.model.Datum
import kg.tabiyat.data.model.Status
import kg.tabiyat.data.repository.InfoRepository
import kg.tabiyat.data.repository.PlantsRepository
import kg.tabiyat.db.entity.PlantsEntity
import kotlinx.coroutines.launch

class InfoViewModel(private var repository: InfoRepository, var plantsRepository: PlantsRepository) : ViewModel() {
    var infoList = MutableLiveData<List<Datum>>()
    private var page = 0
    private var hasNext = true

    fun getAnimalsList() {
        if (!hasNext) return
        viewModelScope.launch {
            repository.getInfoList(page).observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
                        val list = it.data!!.data!!.plants!!.data!!
                        infoList.postValue(list)
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