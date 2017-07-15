package com.skyline.teapi;

public final class IBBox3D extends TEIUnknownHandle {
    private IBBox3D(int handle)
    {
        super(handle);
    }    
    public static IBBox3D fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IBBox3D(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("8423BCC9-FDF8-4B78-8DF9-AD521D2454F8");

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

    public double getMinHeight() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetMinHeight(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setMinHeight(double value) throws ApiException
    {
        checkDisposed();
        NativeSetMinHeight(this.getHandle(),value);
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

    public double getMaxHeight() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetMaxHeight(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setMaxHeight(double value) throws ApiException
    {
        checkDisposed();
        NativeSetMaxHeight(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native double NativeGetMinX(int instance);

private static native void NativeSetMinX(int instance,double pVal);

private static native double NativeGetMinY(int instance);

private static native void NativeSetMinY(int instance,double pVal);

private static native double NativeGetMinHeight(int instance);

private static native void NativeSetMinHeight(int instance,double pVal);

private static native double NativeGetMaxX(int instance);

private static native void NativeSetMaxX(int instance,double pVal);

private static native double NativeGetMaxY(int instance);

private static native void NativeSetMaxY(int instance,double pVal);

private static native double NativeGetMaxHeight(int instance);

private static native void NativeSetMaxHeight(int instance,double pVal);
};
