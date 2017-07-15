package com.skyline.teapi;

public final class IRoofFace extends TEIUnknownHandle {
    private IRoofFace(int handle)
    {
        super(handle);
    }    
    public static IRoofFace fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IRoofFace(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("5FC77487-954A-421E-9695-861CC8F2F5EF");

    public int getFillType() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetFillType(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setFillType(int value) throws ApiException
    {
        checkDisposed();
        NativeSetFillType(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

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

    public int getStyle() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetStyle(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setStyle(int value) throws ApiException
    {
        checkDisposed();
        NativeSetStyle(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getRooftopDeltaHeight() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetRooftopDeltaHeight(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setRooftopDeltaHeight(double value) throws ApiException
    {
        checkDisposed();
        NativeSetRooftopDeltaHeight(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native int NativeGetFillType(int instance);

private static native void NativeSetFillType(int instance,int pVal);

private static native int NativeGetColor(int instance);

private static native void NativeSetColor(int instance,IColor pVal);

private static native int NativeGetTexture(int instance);

private static native int NativeGetStyle(int instance);

private static native void NativeSetStyle(int instance,int pVal);

private static native double NativeGetRooftopDeltaHeight(int instance);

private static native void NativeSetRooftopDeltaHeight(int instance,double pVal);
};
