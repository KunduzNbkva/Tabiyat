package com.example.tabiyat.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabiyat.App
import com.example.tabiyat.data.model.Customer
import com.example.tabiyat.data.model.SignUpModel
import com.example.tabiyat.data.model.Status
import com.example.tabiyat.data.repository.AuthRepository
import kotlinx.coroutines.launch


class RegisterViewModel(private val repository: AuthRepository) : ViewModel() {
    val signupResponse: MutableLiveData<Customer> = MutableLiveData()
    val status : MutableLiveData<Status> = MutableLiveData<Status>()


    fun createUser(signUpModel: SignUpModel) {
        viewModelScope.launch {
            repository.createUser(signUpModel).observeForever {
                when (it.status) {
                    Status.SUCCESS -> {
                        signupResponse.postValue(it.data!!.data!!.customer)
                        App.prefs?.saveAuthToken(it.data.data!!.customer!!.apiToken!!)
                        status.postValue(Status.SUCCESS)
                    }
                    Status.ERROR -> error(it.message.toString())
                    Status.LOADING ->{
                        status.postValue(Status.LOADING)
                    }
                }
            }
        }
    }

}

