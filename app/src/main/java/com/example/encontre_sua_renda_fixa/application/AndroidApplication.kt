package com.example.encontre_sua_renda_fixa.application

import android.app.Application
import com.example.encontre_sua_renda_fixa.features.bonds.di.bondsModule
import org.koin.android.ext.android.startKoin

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(bondsModule))
    }
}