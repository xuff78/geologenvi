package com.skyline.teapi;

public final class IAttachment extends TEIUnknownHandle {
    private IAttachment(int handle)
    {
        super(handle);
    }    
    public static IAttachment fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IAttachment(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("DB8C8FCA-707F-4E18-BE74-CDFC7492F8A4");

    public boolean getIsAttached() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetIsAttached(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean getAutoDetach() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetAutoDetach(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setAutoDetach(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetAutoDetach(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getDeltaX() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetDeltaX(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setDeltaX(double value) throws ApiException
    {
        checkDisposed();
        NativeSetDeltaX(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getDeltaY() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetDeltaY(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setDeltaY(double value) throws ApiException
    {
        checkDisposed();
        NativeSetDeltaY(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getDeltaAltitude() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetDeltaAltitude(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setDeltaAltitude(double value) throws ApiException
    {
        checkDisposed();
        NativeSetDeltaAltitude(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getDeltaPitch() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetDeltaPitch(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setDeltaPitch(double value) throws ApiException
    {
        checkDisposed();
        NativeSetDeltaPitch(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getDeltaYaw() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetDeltaYaw(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setDeltaYaw(double value) throws ApiException
    {
        checkDisposed();
        NativeSetDeltaYaw(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getDeltaRoll() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetDeltaRoll(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setDeltaRoll(double value) throws ApiException
    {
        checkDisposed();
        NativeSetDeltaRoll(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getAttachedToID() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetAttachedToID(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void AttachTo(String ObjectID,double DeltaX,double DeltaY,double DeltaAltitude,double DeltaYaw,double DeltaPitch,double DeltaRoll) throws ApiException
    {
        checkDisposed();
        NativeAttachTo(this.getHandle(),ObjectID,DeltaX,DeltaY,DeltaAltitude,DeltaYaw,DeltaPitch,DeltaRoll);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native boolean NativeGetIsAttached(int instance);

private static native boolean NativeGetAutoDetach(int instance);

private static native void NativeSetAutoDetach(int instance,boolean pVal);

private static native double NativeGetDeltaX(int instance);

private static native void NativeSetDeltaX(int instance,double DeltaX);

private static native double NativeGetDeltaY(int instance);

private static native void NativeSetDeltaY(int instance,double DeltaY);

private static native double NativeGetDeltaAltitude(int instance);

private static native void NativeSetDeltaAltitude(int instance,double DeltaAltitude);

private static native double NativeGetDeltaPitch(int instance);

private static native void NativeSetDeltaPitch(int instance,double DeltaPitch);

private static native double NativeGetDeltaYaw(int instance);

private static native void NativeSetDeltaYaw(int instance,double DeltaYaw);

private static native double NativeGetDeltaRoll(int instance);

private static native void NativeSetDeltaRoll(int instance,double DeltaRoll);

private static native String NativeGetAttachedToID(int instance);

private static native void NativeAttachTo(int instance,String ObjectID,double DeltaX,double DeltaY,double DeltaAltitude,double DeltaYaw,double DeltaPitch,double DeltaRoll);
};
