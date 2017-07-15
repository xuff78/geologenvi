package com.skyline.teapi;

public final class IBBox2D extends TEIUnknownHandle {
    private IBBox2D(int handle)
    {
        super(handle);
    }    
    public static IBBox2D fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IBBox2D(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("87565E28-4C02-456B-BC12-52C2D864FE55");

    public double getMinX() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetMinX(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setMinX(double value) throws ApiException
    {
        checkDisposed();
        NativeSetMinX(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getMinY() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetMinY(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setMinY(double value) throws ApiException
    {
        checkDisposed();
        NativeSetMinY(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getMaxX() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetMaxX(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setMaxX(double value) throws ApiException
    {
        checkDisposed();
        NativeSetMaxX(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getMaxY() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetMaxY(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setMaxY(double value) throws ApiException
    {
        checkDisposed();
        NativeSetMaxY(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native double NativeGetMinX(int instance);

private static native void NativeSetMinX(int instance,double pVal);

private static native double NativeGetMinY(int instance);

private static native void NativeSetMinY(int instance,double pVal);

private static native double NativeGetMaxX(int instance);

private static native void NativeSetMaxX(int instance,double pVal);

private static native double NativeGetMaxY(int instance);

private static native void NativeSetMaxY(int instance,double pVal);
};
