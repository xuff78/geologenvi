package com.skyline.teapi;

public final class IScreenRect extends TEIUnknownHandle {
    private IScreenRect(int handle)
    {
        super(handle);
    }    
    public static IScreenRect fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IScreenRect(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("7481EF93-C8FE-4E94-BDE5-49287CC2FF16");

    public int getLeft() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetLeft(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getTop() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetTop(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getWidth() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetWidth(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getHeight() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetHeight(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native int NativeGetLeft(int instance);

private static native int NativeGetTop(int instance);

private static native int NativeGetWidth(int instance);

private static native int NativeGetHeight(int instance);
};
