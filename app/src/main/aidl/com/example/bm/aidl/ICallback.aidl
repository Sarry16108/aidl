// ICallback.aidl
package com.example.bm.aidl;

// Declare any non-default types here with import statements
import com.example.bm.aidl.IListener;

interface ICallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);
    void setListener(IListener lst);
    void sendMSGtoService(String msg);
}
