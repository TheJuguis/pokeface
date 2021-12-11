package com.example.pokeface

import android.app.Application
import com.example.pokeface.database.DatabaseManager

open class MyApplication: Application() {
    override fun onCreate() {
        DatabaseManager.instance.initializeDb(applicationContext)
        super.onCreate()
    }
}