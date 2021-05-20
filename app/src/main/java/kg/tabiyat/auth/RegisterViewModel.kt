package kg.tabiyat.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.tabiyat.data.model.Customer
import kg.tabiyat.data.model.SignUpModel
import kg.tabiyat.data.model.Status
import kg.tabiyat.data.repository.AuthRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: AuthRepository) : ViewModel() {
    val signupResponse: MutableLiveData<Customer> = MutableLiveData()
    val status: MutableLiveData<Status> = MutableLiveData<Status>()


    fun createUser(signUpModel: SignUpModel) {
        viewModelScope.launch {
            repository.createUser(signUpModel).observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
                        signupResponse.postValue(it.data!!.data!!.customer)
                        status.postValue(Status.SUCCESS)
                    }
                    Status.ERROR -> error(it.message.toString())
                    Status.LOADING -> {
                        status.postValue(Status.LOADING)
                    }
                }
            }
        }
    }

    fun updateUserName(name: String) {
        viewModelScope.launch {
            repository.updateUserName(name).observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
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

}

