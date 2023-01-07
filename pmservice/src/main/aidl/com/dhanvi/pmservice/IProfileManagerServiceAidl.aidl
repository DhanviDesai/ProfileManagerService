// IProfileManagerServiceAdil.aidl
package com.dhanvi.pmservice;

// Declare any non-default types here with import statements

interface IProfileManagerServiceAidl {
    String getServiceVersion();
    String getModifiedString(in String partialString);
}