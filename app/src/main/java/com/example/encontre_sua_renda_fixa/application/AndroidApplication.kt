package com.example.encontre_sua_renda_fixa.application

import android.app.Application
import com.example.encontre_sua_renda_fixa.BuildConfig
import com.example.encontre_sua_renda_fixa.features.bonds.di.bondsModule
import com.example.encontre_sua_renda_fixa.features.bonds.di.viewModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(bondsModule, viewModule))
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}