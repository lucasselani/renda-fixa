package com.lambreta.rendafixa.application

import android.app.Application
import com.lambreta.rendafixa.BuildConfig
import com.lambreta.rendafixa.features.bonds.di.bondsModule
import com.lambreta.rendafixa.features.bonds.di.viewModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(bondsModule, viewModule))
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}