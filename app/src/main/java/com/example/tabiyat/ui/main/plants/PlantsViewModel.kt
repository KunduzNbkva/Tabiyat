package com.example.tabiyat.ui.main.plants

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabiyat.data.repository.PlantsRepository
import com.example.tabiyat.data.model.Datum
import com.example.tabiyat.data.model.Status
import kotlinx.coroutines.launch

class PlantsViewModel(private val plantsRepository: PlantsRepository) : ViewModel() {
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