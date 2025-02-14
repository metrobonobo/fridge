// IMyAidlInterface.aidl
package com.tcn.drivers.aidl;


// Declare any non-default types here with import statements

interface IBinderPool {
    IBinder queryBinder(int binderCode);
}
