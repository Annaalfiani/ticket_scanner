package com.annaalfiani.ticketscanner

import android.app.Application
import com.annaalfiani.ticketscanner.repositories.OrderDetailRepository
import com.annaalfiani.ticketscanner.ui.main.MainViewModel
import com.annaalfiani.ticketscanner.webservices.ApiCllient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApp)
            modules(listOf(retrofitModule, repositoryModule, viewModelModule))
        }
    }
}

val retrofitModule = module {
    single { ApiCllient.instance() }
}
val repositoryModule = module {
    factory { OrderDetailRepository(get()) }
}
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}