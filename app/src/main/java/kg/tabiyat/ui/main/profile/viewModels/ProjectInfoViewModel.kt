package kg.tabiyat.ui.main.profile.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.tabiyat.data.model.AboutModel
import kg.tabiyat.data.model.Status
import kg.tabiyat.data.repository.AboutRepository
import kotlinx.coroutines.launch

class ProjectInfoViewModel(var repository: AboutRepository) : ViewModel() {
    val projectInfo: MutableLiveData<AboutModel> = MutableLiveData()

    fun getProjectInfo() {
        viewModelScope.launch {
            repository.getProjectInfo().observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
                        projectInfo.postValue(it.data!!)
                    }
                    Status.ERROR -> error(it.message.toString())
                }
            }
        }
    }

}