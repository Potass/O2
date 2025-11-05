package cz.jvyh.o2.scratch

import android.app.Application
import cz.jvyh.o2.scratch.common.di.KoinHolder
import org.koin.android.ext.koin.androidContext

class O2ScratchApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        KoinHolder.create {
            androidContext(this@O2ScratchApplication)
        }
    }
}