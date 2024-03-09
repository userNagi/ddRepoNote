package com.nagi

import android.app.Application
import com.nagi.ddreponote.data.AppDatabase

class DDRepoNote : Application() {
    override fun onCreate() {
        super.onCreate()
        initData()
    }

    private fun initData() {
        AppDatabase.getInstance(applicationContext)
    }
}