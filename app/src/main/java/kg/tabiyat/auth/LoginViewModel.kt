package kg.tabiyat.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.tabiyat.data.model.Customer
import kg.tabiyat.data.model.LoginModel
import kg.tabiyat.data.model.Status
import kg.tabiyat.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AuthRepository) : ViewModel() {
    var user = MutableLiveData<Customer>()
    val status: MutableLiveData<Status> = MutableLiveData<Status>()


    fun loginUser(loginModel: LoginModel) {
        viewModelScope.launch {
            repository.loginUser(loginModel).observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
                        user.value = it.data!!.data!!.customer!!
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

    fun createGmailUser(type: String, email: String) {
        viewModelScope.launch {
            repository.createGmailUser(type, email).observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
                        user.value = it.data!!.data!!.customer!!
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

}

