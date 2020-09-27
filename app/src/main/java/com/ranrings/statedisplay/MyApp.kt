package com.ranrings.statedisplay

import android.app.Application
import com.ranrings.statedisplay.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {


    companion object {
        lateinit var appContext : Application
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            modules(appModule)
        }

    }
}