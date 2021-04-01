package com.danielcunha.topseries.core.application

import android.app.Application
import com.danielcunha.topseries.core.injection.repositoryModule
import com.danielcunha.topseries.core.injection.retrofitModule
import com.danielcunha.topseries.core.injection.useCaseModule
import com.danielcunha.topseries.core.injection.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startDependencyInjection()
    }

    private fun startDependencyInjection() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    viewModelModule,
                    retrofitModule,
                    repositoryModule,
                    useCaseModule
                )
            )
        }
    }
}