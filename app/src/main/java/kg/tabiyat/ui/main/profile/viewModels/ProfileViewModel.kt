package kg.tabiyat.ui.main.profile.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.tabiyat.data.model.Customer
import kg.tabiyat.data.model.Status
import kg.tabiyat.data.repository.ProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel(var repository: ProfileRepository) : ViewModel() {
    val user: MutableLiveData<Customer> = MutableLiveData()

    fun getUserData() {
        viewModelScope.launch {
            repository.getUser().observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
                        user.postValue(it.data!!.data!!.customer)
                    }
                    Status.ERROR -> error(it.message.toString())
                }
            }
        }
    }


}