package com.dhanvi.pmservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log

class ProfileMangerInstance(private val context: Context) {
    val TAG = ProfileMangerInstance::class.java.simpleName

    private val profileManagerIntent = Intent("com.dhanvi.profilemanagerservice.ProfileManagerService").setPackage("")

    private val PROFILE_MANAGER_SERVICE_PACKAGE_NAME = "com.dhanvi.profilemanagerservice"
    private val PROFILE_MANAGER_SERVICE_CLASS_NAME = "com.dhanvi.profilemanagerservice.ProfileManagerService"

    private var profileManagerService: IProfileManagerServiceAidl? = null

    private var serviceConnectedCallback : ((IProfileManagerServiceAidl) -> Unit)? = null

    private val profileManagerConnection = object: ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            Log.d(TAG, "Connected to ProfileManager")
            profileManagerService = IProfileManagerServiceAidl.Stub.asInterface(p1)
            serviceConnectedCallback?.let {
                profileManagerService?.let(it)
            }
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            Log.d(TAG, "Disconnected from ProfileManager")
        }

    }

    fun connect(serviceConnectedCallback: (IProfileManagerServiceAidl) -> Unit): Boolean {
        val intent = Intent()
        intent.component = ComponentName(PROFILE_MANAGER_SERVICE_PACKAGE_NAME, PROFILE_MANAGER_SERVICE_CLASS_NAME)
        this.serviceConnectedCallback = serviceConnectedCallback
        return context.bindService(intent, profileManagerConnection, Context.BIND_AUTO_CREATE)
    }

    fun getServiceVersion(): String? {
        return profileManagerService?.serviceVersion
    }

    fun getModifiedString(paritalString: String): String? {
        return profileManagerService?.getModifiedString(paritalString)
    }
}