package com.skyline.teapi;

public final class IFillStyle extends TEIUnknownHandle {
    private IFillStyle(int handle)
    {
        super(handle);
    }    
    public static IFillStyle fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IFillStyle(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("0879546F-D306-48D2-965E-4EF6D6741F8B");

    public IColor getColor() throws ApiException
    {
        checkDisposed();
        IColor result = IColor.fromHandle(NativeGetColor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setColor(IColor value) throws ApiException
    {
        checkDisposed();
        NativeSetColor(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IObjectTexture getTexture() throws ApiException
    {
        checkDisposed();
        IObjectTexture result = IObjectTexture.fromHandle(NativeGetTexture(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native int NativeGetColor(int instance);

private static native void NativeSetColor(int instance,IColor pVal);

private static native int NativeGetTexture(int instance);
};
