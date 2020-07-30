package com.example.teacher;
import android.os.RemoteException;
public class PersonImpl extends IMyMessage.Stub {
    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
    }
}
a