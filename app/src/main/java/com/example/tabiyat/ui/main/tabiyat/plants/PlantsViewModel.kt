package com.example.tabiyat.ui.main.tabiyat.plants

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabiyat.data.Repository
import com.example.tabiyat.data.model.Datum
import com.example.tabiyat.data.model.Status
import kotlinx.coroutines.launch

class PlantsViewModel(private val repository: Repository) : ViewModel() {
    var plantsList = MutableLiveData<List<Datum>>()

    fun getPlantsList(){
        viewModelScope.launch {
            repository.getPlantsList().observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
                        plantsList.postValue(it.data!!.plants!!.data!!)
                    }
                    Status.ERROR -> error(it.message.toString())
                    Status.LOADING -> null
                }
            }
        }
    }

}