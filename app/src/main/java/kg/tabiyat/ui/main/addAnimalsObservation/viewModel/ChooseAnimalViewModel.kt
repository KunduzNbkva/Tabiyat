package kg.tabiyat.ui.main.addAnimalsObservation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.tabiyat.data.model.Datum
import kg.tabiyat.data.model.Status
import kg.tabiyat.data.repository.AnimalsRepository
import kotlinx.coroutines.launch

class ChooseAnimalViewModel(private val animalsRepository: AnimalsRepository): ViewModel() {
    var animalsList = MutableLiveData<List<Datum>>()
    private var page = 0
    private var hasNext = true

    fun getAnimalsList(){
        if (!hasNext) return
        viewModelScope.launch {
            animalsRepository.getAnimalsList(page).observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
                        val list = it.data!!.data!!.plants!!.data!!
//                        plantsList.postValue(list)
                        animalsRepository.db.mainDao().insertPlantsList(list)
                        page++
                        if (list.size < 20) hasNext=false
                    }
                    Status.ERROR -> error(it.message.toString())
                    Status.LOADING -> null
                }
            }
        }
    }

//    fun getLocalPlantsList() : LiveData<List<PlantsEntity>>{
//      return animalsRepository.getLocalPlantsList()
//    }

    fun resetPage() {
        page = 0
    }

}