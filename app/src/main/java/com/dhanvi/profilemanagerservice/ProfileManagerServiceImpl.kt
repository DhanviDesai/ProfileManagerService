package com.dhanvi.profilemanagerservice

import com.dhanvi.pmservice.IProfileManagerServiceAidl

class ProfileManagerServiceImpl: IProfileManagerServiceAidl.Stub(){
    override fun getServiceVersion(): String {
        return BuildConfig.VERSION_NAME
    }

    override fun getModifiedString(partialString: String?): String {
        return "This is a string from ProfileManagerService with PMS-$partialString"
    }
}