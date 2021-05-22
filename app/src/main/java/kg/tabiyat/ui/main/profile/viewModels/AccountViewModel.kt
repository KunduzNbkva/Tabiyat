package kg.tabiyat.ui.main.profile.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.tabiyat.data.model.Status
import kg.tabiyat.data.repository.ProfileRepository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class AccountViewModel(var repository: ProfileRepository) : ViewModel() {
    val status: MutableLiveData<Status> = MutableLiveData<Status>()


    fun updateUserName(name: String) {
        viewModelScope.launch {
            repository.updateUserName(name).observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
                        status.postValue(it.status)
                        Log.e(
                            "Name",
                            "Name updated to ${it.data!!.data!!.customer!!.fullName.toString()}"
                        )
                    }
                    Status.ERROR -> error(it.message.toString())
                }
            }
        }
    }

    fun updateUserAvatar(avatar: MultipartBody.Part) {
        viewModelScope.launch {
            repository.updateAvatar(avatar).observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
                        status.postValue(it.status)
                        Log.e(
                            "Avatar",
                            "Avatar updated to ${it.data!!.data!!.customer!!.avatar.toString()}"
                        )
                    }
                    Status.ERROR -> error(it.message.toString())
                }
            }
        }
    }

}