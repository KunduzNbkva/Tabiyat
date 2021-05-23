package kg.tabiyat.ui.main.addObservation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.tabiyat.data.model.PostObserve
import kg.tabiyat.data.model.Status
import kg.tabiyat.data.repository.ObservationRepository
import kotlinx.coroutines.launch

class AddObservationViewModel(private var repository: ObservationRepository) : ViewModel() {
    val message: MutableLiveData<String> = MutableLiveData()

    fun postObservation(postObserve: PostObserve) {
        viewModelScope.launch {
            repository.postObservation(postObserve).observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
                        message.postValue("Success")
                        Log.e("post_observation","success: post is created")
                    }
                    Status.ERROR -> error(it.message.toString())
                }
            }
        }
    }
}