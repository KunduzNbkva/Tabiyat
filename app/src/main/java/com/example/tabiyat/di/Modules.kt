package com.example.tabiyat.di

import com.example.tabiyat.auth.LoginViewModel
import com.example.tabiyat.auth.RegisterViewModel
import com.example.tabiyat.data.prefs.SharedPref
import com.example.tabiyat.data.remote.RetrofitClient.provideApi
import com.example.tabiyat.data.repository.*
import com.example.tabiyat.ui.main.animals.AnimalsViewModel
import com.example.tabiyat.ui.main.cardDetail.CardDetailViewModel
import com.example.tabiyat.ui.main.favorite.FavoriteViewModel
import com.example.tabiyat.ui.main.info.InfoViewModel
import com.example.tabiyat.ui.main.plants.PlantsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RegisterViewModel(get()) }
    viewModel { PlantsViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { AnimalsViewModel(get()) }
    viewModel { InfoViewModel(get()) }
    viewModel { CardDetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }


}
var repositoryModule = module {
    factory { AuthRepository(get()) }
    factory { PlantsRepository(get()) }
    factory { AnimalsRepository(get()) }
    factory { InfoRepository(get()) }
    factory { FavoriteRepository(get()) }
}
var networkModule = module {
    single { provideApi() }
}

var appModule = module {
    single { SharedPref(get()) }
}
