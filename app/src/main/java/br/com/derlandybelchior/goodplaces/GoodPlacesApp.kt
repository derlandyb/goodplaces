package br.com.derlandybelchior.goodplaces

import android.app.Application
import br.com.derlandybelchior.goodplaces.data.service.LocationServiceAPI
import br.com.derlandybelchior.goodplaces.data.RemoteRepository
import br.com.derlandybelchior.goodplaces.data.service.GoodPlacesServices
import br.com.derlandybelchior.goodplaces.data.service.PhotoLocationServiceAPI
import br.com.derlandybelchior.goodplaces.domain.FetchLocationUseCase
import br.com.derlandybelchior.goodplaces.domain.LocationRepository
import br.com.derlandybelchior.goodplaces.presentation.home.PlacesViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.core.context.startKoin
import org.koin.dsl.module

class GoodPlacesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GoodPlacesApp)
            modules(appModule)
        }
    }
}

private val appModule = module {
    factory {
        FetchLocationUseCase(
            locationRepository = get()
        )
    }

    factory<LocationRepository> {
        RemoteRepository(locationServiceAPI = get(), photoLocationServiceAPI = get())
    }

    single<LocationServiceAPI> {
        GoodPlacesServices.retrofit(BuildConfig.GOOD_PLACES_BASE_URL)
            .create(LocationServiceAPI::class.java)
    }

    single<PhotoLocationServiceAPI> {
        GoodPlacesServices.retrofit(BuildConfig.UNSPLASH_PHOTOS_BASE_URL)
            .create(PhotoLocationServiceAPI::class.java)
    }

    viewModel {
        PlacesViewModel(
            fetchLocationUseCase = get(),
            dispatcher = Dispatchers.IO
        )
    }
}