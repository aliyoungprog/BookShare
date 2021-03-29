package com.example.bookshare.domain

import android.app.Application
import com.example.bookshare.domain.di.koinModules
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(koinModules)
        }
    }
}