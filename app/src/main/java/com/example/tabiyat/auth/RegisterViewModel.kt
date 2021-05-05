package com.example.tabiyat.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabiyat.App
import com.example.tabiyat.data.Repository
import com.example.tabiyat.data.model.Status
import com.example.tabiyat.data.model.SignUpModel
import com.example.tabiyat.data.model.User
import kotlinx.coroutines.launch


class RegisterViewModel(private val repository: Repository) : ViewModel() {
    val signupResponse: MutableLiveData<User> = MutableLiveData()



    fun createUser(signUpModel: SignUpModel){
        viewModelScope.launch {
            repository.createUser(signUpModel).observeForever{
                when (it.status) {
                    Status.SUCCESS -> {
                        signupResponse.postValue(it.data)
                        App.prefs?.saveToken(it.data?.apiToken!!)
                    }
                    Status.ERROR -> error(it.message.toString())
                    Status.LOADING -> null
                }
            }
        }
    }


}
