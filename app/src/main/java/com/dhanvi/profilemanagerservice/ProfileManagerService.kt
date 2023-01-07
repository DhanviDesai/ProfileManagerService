package com.dhanvi.profilemanagerservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class ProfileManagerService : Service() {

    val TAG = ProfileManagerService::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "ProfileManagerService Created")
    }

    override fun onBind(p0: Intent?): IBinder {
        Log.d(TAG, "ProfileManagerService was bound by somebody")
        return ProfileManagerServiceImpl()
    }
}