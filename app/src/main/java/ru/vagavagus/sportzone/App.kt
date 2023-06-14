package ru.vagavagus.sportzone

import android.app.Application
import android.content.Context
import com.onesignal.OneSignal
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.vagavagus.sportzone.common.Constants
import ru.vagavagus.sportzone.di.dataModule

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(dataModule)
        }

        val sharedPrefs = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
        val disabledPush = sharedPrefs.getBoolean("disabledpush", false)

        if(!disabledPush) {
            OneSignal.initWithContext(this)
            OneSignal.setAppId(Constants.ONESIGNAL_ID)
            OneSignal.promptForPushNotifications()
        } else {
            OneSignal.initWithContext(this)
            OneSignal.setAppId(Constants.ONESIGNAL_ID)
            OneSignal.disablePush(true)
        }
    }
}