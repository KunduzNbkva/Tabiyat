package com.example.tabiyat.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabiyat.App
import com.example.tabiyat.data.model.Customer
import com.example.tabiyat.data.model.LoginModel
import com.example.tabiyat.data.model.Status
import com.example.tabiyat.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AuthRepository): ViewModel() {
    var user = MutableLiveData<Customer>()
    val status: MutableLiveData<Status> = MutableLiveData<Status>()


    fun loginUser(loginModel: LoginModel) {
        viewModelScope.launch {
            repository.loginUser(loginModel).observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
                        user.value = it.data!!.data!!.customer
                        App.prefs!!.saveAuthToken(it.data!!.data!!.customer!!.apiToken!!)
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

