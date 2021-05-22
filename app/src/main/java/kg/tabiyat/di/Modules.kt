package kg.tabiyat.di

import androidx.room.Room
import kg.tabiyat.auth.LoginViewModel
import kg.tabiyat.auth.RegisterViewModel
import kg.tabiyat.data.prefs.SharedPref
import kg.tabiyat.data.remote.RetrofitClient.provideApi
import kg.tabiyat.data.repository.*
import kg.tabiyat.db.AppDatabase
import kg.tabiyat.ui.main.addObservatrion.viewModel.AddObservationViewModel
import kg.tabiyat.ui.main.addObservatrion.viewModel.ChoosePlantViewModel
import kg.tabiyat.ui.main.animals.AnimalsViewModel
import kg.tabiyat.ui.main.cardDetail.CardDetailViewModel
import kg.tabiyat.ui.main.favorite.FavoriteViewModel
import kg.tabiyat.ui.main.info.InfoViewModel
import kg.tabiyat.ui.main.plants.PlantsViewModel
import kg.tabiyat.ui.main.profile.viewModels.AccountViewModel
import kg.tabiyat.ui.main.profile.viewModels.ProfileViewModel
import kg.tabiyat.ui.main.profile.viewModels.ProjectInfoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RegisterViewModel(get()) }
    viewModel { PlantsViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { AnimalsViewModel(get()) }
    viewModel { InfoViewModel(get(), get()) }
    viewModel { CardDetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { ChoosePlantViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { AddObservationViewModel(get()) }
    viewModel { ProjectInfoViewModel(get()) }
    viewModel { AccountViewModel(get()) }


}
var repositoryModule = module {
    factory { AuthRepository(get()) }
    factory { PlantsRepository(get(), get()) }
    factory { AnimalsRepository(get()) }
    factory { InfoRepository(get()) }
    factory { FavoriteRepository(get()) }
    factory { ProfileRepository(get()) }
    factory { ObservationRepository(get()) }
    factory { AboutRepository(get()) }
}
var networkModule = module {
    single { provideApi() }
}

var appModule = module {
    single { SharedPref(get()) }
}

var localModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "dbCategoriesResponse"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}
