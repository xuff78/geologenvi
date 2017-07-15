package com.skyline.teapi;

public final class IMouseInfo extends TEIUnknownHandle {
    private IMouseInfo(int handle)
    {
        super(handle);
    }    
    public static IMouseInfo fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IMouseInfo(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("7F553714-34AB-4625-A06F-D0A46338920E");

    public int getX() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetX(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getY() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetY(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getdelta() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetdelta(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getFlags() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetFlags(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native int NativeGetX(int instance);

private static native int NativeGetY(int instance);

private static native int NativeGetdelta(int instance);

private static native int NativeGetFlags(int instance);
};
