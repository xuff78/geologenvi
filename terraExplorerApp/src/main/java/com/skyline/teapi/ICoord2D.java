package com.skyline.teapi;

public final class ICoord2D extends TEIUnknownHandle {
    private ICoord2D(int handle)
    {
        super(handle);
    }    
    public static ICoord2D fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ICoord2D(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("84CE9DC2-65AD-11D5-85C1-0001023952C1");

    public double getX() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetX(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setX(double value) throws ApiException
    {
        checkDisposed();
        NativeSetX(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getY() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetY(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setY(double value) throws ApiException
    {
        checkDisposed();
        NativeSetY(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native double NativeGetX(int instance);

private static native void NativeSetX(int instance,double pVal);

private static native double NativeGetY(int instance);

private static native void NativeSetY(int instance,double pVal);
};
