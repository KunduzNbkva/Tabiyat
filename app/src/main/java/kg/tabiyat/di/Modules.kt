package kg.tabiyat.di

import androidx.room.Room
import kg.tabiyat.auth.LoginViewModel
import kg.tabiyat.auth.RegisterViewModel
import kg.tabiyat.data.prefs.SharedPref
import kg.tabiyat.data.remote.RetrofitClient.provideApi
import kg.tabiyat.data.repository.*
import kg.tabiyat.data.local.db.AppDatabase
import kg.tabiyat.ui.main.addAnimalsObservation.viewModel.AddAnimalObservationViewModel
import kg.tabiyat.ui.main.addObservation.viewModel.AddObservationViewModel
import kg.tabiyat.ui.main.addAnimalsObservation.viewModel.ChooseAnimalViewModel
import kg.tabiyat.ui.main.addObservation.viewModel.ChoosePlantViewModel
import kg.tabiyat.ui.main.animals.AnimalsViewModel
import kg.tabiyat.ui.main.cardFavoriteDetail.CardFavDetailViewModel
import kg.tabiyat.ui.main.favorite.FavoriteViewModel
import kg.tabiyat.ui.main.info.InfoViewModel
import kg.tabiyat.ui.main.news.NewsViewModel
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
    viewModel { CardFavDetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { ChooseAnimalViewModel(get()) }
    viewModel { ChoosePlantViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { AddObservationViewModel(get()) }
    viewModel { ProjectInfoViewModel(get()) }
    viewModel { AccountViewModel(get()) }
    viewModel { AddAnimalObservationViewModel(get()) }
    viewModel { NewsViewModel(get()) }
}
var repositoryModule = module {
    factory { AuthRepository(get()) }
    factory { PlantsRepository(get(), get()) }
    factory { AnimalsRepository(get(),get()) }
    factory { InfoRepository(get()) }
    factory { FavoriteRepository(get()) }
    factory { ProfileRepository(get()) }
    factory { ObservationRepository(get()) }
    factory { AboutRepository(get()) }
    factory { NewsRepository(get()) }

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
