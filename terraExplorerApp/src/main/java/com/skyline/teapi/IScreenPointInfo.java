package com.skyline.teapi;

public final class IScreenPointInfo extends TEIUnknownHandle {
    private IScreenPointInfo(int handle)
    {
        super(handle);
    }    
    public static IScreenPointInfo fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IScreenPointInfo(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("AE4478A3-CF8E-4397-8071-4771B81FFDBE");

    public double getX() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetX(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double getY() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetY(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean getInsideScreenRect() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetInsideScreenRect(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean getPointBehindCamera() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetPointBehindCamera(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native double NativeGetX(int instance);

private static native double NativeGetY(int instance);

private static native boolean NativeGetInsideScreenRect(int instance);

private static native boolean NativeGetPointBehindCamera(int instance);
};
