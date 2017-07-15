package com.skyline.teapi;

public final class IVisibility extends TEIUnknownHandle {
    private IVisibility(int handle)
    {
        super(handle);
    }    
    public static IVisibility fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IVisibility(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("10AF4735-4043-493C-A588-C6082C18D3CC");

    public boolean getShow() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetShow(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setShow(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetShow(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getMaxVisibilityDistance() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetMaxVisibilityDistance(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setMaxVisibilityDistance(double value) throws ApiException
    {
        checkDisposed();
        NativeSetMaxVisibilityDistance(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getMinVisibilityDistance() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetMinVisibilityDistance(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setMinVisibilityDistance(double value) throws ApiException
    {
        checkDisposed();
        NativeSetMinVisibilityDistance(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native boolean NativeGetShow(int instance);

private static native void NativeSetShow(int instance,boolean pVal);

private static native double NativeGetMaxVisibilityDistance(int instance);

private static native void NativeSetMaxVisibilityDistance(int instance,double pVal);

private static native double NativeGetMinVisibilityDistance(int instance);

private static native void NativeSetMinVisibilityDistance(int instance,double pVal);
};
