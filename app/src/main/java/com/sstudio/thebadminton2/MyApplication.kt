package com.sstudio.thebadminton2

import android.app.Application
import com.sstudio.thebadminton2.core.di.networkModule
import com.sstudio.thebadminton2.core.di.repositoryModule
import com.sstudio.thebadminton2.di.useCaseModule
import com.sstudio.thebadminton2.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}