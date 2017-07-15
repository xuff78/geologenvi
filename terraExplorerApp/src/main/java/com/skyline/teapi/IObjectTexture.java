package com.skyline.teapi;

public final class IObjectTexture extends TEIUnknownHandle {
    private IObjectTexture(int handle)
    {
        super(handle);
    }    
    public static IObjectTexture fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IObjectTexture(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("60BDB6AF-698D-4E6E-8B0A-5F461345C33A");

    public String getFileName() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetFileName(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setFileName(String value) throws ApiException
    {
        checkDisposed();
        NativeSetFileName(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getTilingMethod() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetTilingMethod(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setTilingMethod(int value) throws ApiException
    {
        checkDisposed();
        NativeSetTilingMethod(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getScaleX() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetScaleX(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setScaleX(double value) throws ApiException
    {
        checkDisposed();
        NativeSetScaleX(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getScaleY() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetScaleY(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setScaleY(double value) throws ApiException
    {
        checkDisposed();
        NativeSetScaleY(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getRotateAngle() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetRotateAngle(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setRotateAngle(double value) throws ApiException
    {
        checkDisposed();
        NativeSetRotateAngle(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native String NativeGetFileName(int instance);

private static native void NativeSetFileName(int instance,String pVal);

private static native int NativeGetTilingMethod(int instance);

private static native void NativeSetTilingMethod(int instance,int pVal);

private static native double NativeGetScaleX(int instance);

private static native void NativeSetScaleX(int instance,double pVal);

private static native double NativeGetScaleY(int instance);

private static native void NativeSetScaleY(int instance,double pVal);

private static native double NativeGetRotateAngle(int instance);

private static native void NativeSetRotateAngle(int instance,double pVal);
};
