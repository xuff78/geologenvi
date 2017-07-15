package com.skyline.teapi;

public final class ICoordinateSystem extends TEIUnknownHandle {
    private ICoordinateSystem(int handle)
    {
        super(handle);
    }    
    public static ICoordinateSystem fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ICoordinateSystem(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("BA4BD798-E5B5-47CB-9DCF-BC319B25D433");

    public String getWellKnownText() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetWellKnownText(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setWellKnownText(String value) throws ApiException
    {
        checkDisposed();
        NativeSetWellKnownText(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getWktDescription() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetWktDescription(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String getPrettyWkt() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetPrettyWkt(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double getFactorToMeter() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetFactorToMeter(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double GetFactorToMeterEx(double Y) throws ApiException
    {
        checkDisposed();
        double result = (NativeGetFactorToMeterEx(this.getHandle(),Y));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void InitFromBMG(String Group,String Sys,String Datum,String Unit) throws ApiException
    {
        checkDisposed();
        NativeInitFromBMG(this.getHandle(),Group,Sys,Datum,Unit);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void InitFromEPSG(int EPSG) throws ApiException
    {
        checkDisposed();
        NativeInitFromEPSG(this.getHandle(),EPSG);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void InitLatLong() throws ApiException
    {
        checkDisposed();
        NativeInitLatLong(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean IsPlanar() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeIsPlanar(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean IsSame(ICoordinateSystem pCoordinateSystem) throws ApiException
    {
        checkDisposed();
        boolean result = (NativeIsSame(this.getHandle(),pCoordinateSystem));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean IsWktValid() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeIsWktValid(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native String NativeGetWellKnownText(int instance);

private static native void NativeSetWellKnownText(int instance,String pVal);

private static native String NativeGetWktDescription(int instance);

private static native String NativeGetPrettyWkt(int instance);

private static native double NativeGetFactorToMeter(int instance);

private static native double NativeGetFactorToMeterEx(int instance,double Y);

private static native void NativeInitFromBMG(int instance,String Group,String Sys,String Datum,String Unit);

private static native void NativeInitFromEPSG(int instance,int EPSG);

private static native void NativeInitLatLong(int instance);

private static native boolean NativeIsPlanar(int instance);

private static native boolean NativeIsSame(int instance,ICoordinateSystem pCoordinateSystem);

private static native boolean NativeIsWktValid(int instance);
};
