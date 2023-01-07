package com.dhanvi.pmservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log

class ProfileMangerInstance(private val context: Context) {
    val TAG = ProfileMangerInstance::class.java.simpleName

    private val profileManagerIntent = Intent("com.dhanvi.profilemanagerservice.ProfileManagerService").setPackage("com.dhanvi.profilemanagerservice")

    private var profileManagerService: IProfileManagerServiceAidl? = null

    private val profileManagerConnection = object: ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            Log.d(TAG, "Connected to ProfileManager")
            profileManagerService = IProfileManagerServiceAidl.Stub.asInterface(p1)
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            Log.d(TAG, "Disconnected from ProfileManager")
        }

    }

    fun connect() {
        context.bindService(profileManagerIntent, profileManagerConnection, Context.BIND_AUTO_CREATE)
    }

    fun getServiceVersion(): String? {
        return profileManagerService?.serviceVersion
    }

    fun getModifiedString(paritalString: String): String? {
        return profileManagerService?.getModifiedString(paritalString)
    }
}