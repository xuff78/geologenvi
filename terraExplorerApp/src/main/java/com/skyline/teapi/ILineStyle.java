package com.skyline.teapi;

public final class ILineStyle extends TEIUnknownHandle {
    private ILineStyle(int handle)
    {
        super(handle);
    }    
    public static ILineStyle fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ILineStyle(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("3D5ACF0A-3739-4459-9690-6309DD873730");

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

    public double getWidth() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetWidth(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setWidth(double value) throws ApiException
    {
        checkDisposed();
        NativeSetWidth(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IColor getBackColor() throws ApiException
    {
        checkDisposed();
        IColor result = IColor.fromHandle(NativeGetBackColor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setBackColor(IColor value) throws ApiException
    {
        checkDisposed();
        NativeSetBackColor(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getPattern() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetPattern(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setPattern(int value) throws ApiException
    {
        checkDisposed();
        NativeSetPattern(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native int NativeGetColor(int instance);

private static native void NativeSetColor(int instance,IColor pVal);

private static native double NativeGetWidth(int instance);

private static native void NativeSetWidth(int instance,double pVal);

private static native int NativeGetBackColor(int instance);

private static native void NativeSetBackColor(int instance,IColor pVal);

private static native int NativeGetPattern(int instance);

private static native void NativeSetPattern(int instance,int pVal);
};
