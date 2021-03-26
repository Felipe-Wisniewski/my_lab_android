package com.wisnitech.googlebooks

import android.app.Application
import com.wisnitech.googlebooks.di.booksModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(booksModule)
        }
    }
}