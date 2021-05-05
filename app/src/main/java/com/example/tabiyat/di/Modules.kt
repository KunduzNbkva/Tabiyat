package com.example.tabiyat.di

import com.example.tabiyat.auth.RegisterViewModel
import com.example.tabiyat.data.Repository
import com.example.tabiyat.data.remote.RetrofitClient.provideApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
   viewModel{RegisterViewModel(get())}
}
var repositoryModule = module {
    factory { Repository(get()) }
}
var networkModule = module {
    single { provideApi() }
}