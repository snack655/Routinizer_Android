package kr.co.override.routinizer.extension

import android.app.Application

class MyApp : Application() {
    companion object {
        lateinit var instance: MyApp
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}