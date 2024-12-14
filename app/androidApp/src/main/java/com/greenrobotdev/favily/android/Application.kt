package com.greenrobotdev.favily.android

import android.app.Application
import com.greenrobotdev.core.coreModule
import com.greenrobotdev.onlinestore.onlineStoreModule
import com.greenrobotdev.favily.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@Application)
            modules(appModule)
            modules(coreModule)
            modules(onlineStoreModule)

        }
    }
}