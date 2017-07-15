package com.skyline.teapi;

public final class IRouteWaypoint extends TEIUnknownHandle {
    private IRouteWaypoint(int handle)
    {
        super(handle);
    }    
    public static IRouteWaypoint fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IRouteWaypoint(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("AF96372A-C0D1-46F6-905C-C75AB45A2EE6");

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

    public double getAltitude() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetAltitude(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setAltitude(double value) throws ApiException
    {
        checkDisposed();
        NativeSetAltitude(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getYaw() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetYaw(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setYaw(double value) throws ApiException
    {
        checkDisposed();
        NativeSetYaw(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getPitch() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetPitch(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setPitch(double value) throws ApiException
    {
        checkDisposed();
        NativeSetPitch(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getRoll() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetRoll(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setRoll(double value) throws ApiException
    {
        checkDisposed();
        NativeSetRoll(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getCameraDeltaYaw() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetCameraDeltaYaw(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setCameraDeltaYaw(double value) throws ApiException
    {
        checkDisposed();
        NativeSetCameraDeltaYaw(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getCameraDeltaPitch() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetCameraDeltaPitch(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setCameraDeltaPitch(double value) throws ApiException
    {
        checkDisposed();
        NativeSetCameraDeltaPitch(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getSpeed() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetSpeed(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setSpeed(double value) throws ApiException
    {
        checkDisposed();
        NativeSetSpeed(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getMessageID() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetMessageID(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setMessageID(String value) throws ApiException
    {
        checkDisposed();
        NativeSetMessageID(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native double NativeGetX(int instance);

private static native void NativeSetX(int instance,double pVal);

private static native double NativeGetY(int instance);

private static native void NativeSetY(int instance,double pVal);

private static native double NativeGetAltitude(int instance);

private static native void NativeSetAltitude(int instance,double pVal);

private static native double NativeGetYaw(int instance);

private static native void NativeSetYaw(int instance,double pVal);

private static native double NativeGetPitch(int instance);

private static native void NativeSetPitch(int instance,double pVal);

private static native double NativeGetRoll(int instance);

private static native void NativeSetRoll(int instance,double pVal);

private static native double NativeGetCameraDeltaYaw(int instance);

private static native void NativeSetCameraDeltaYaw(int instance,double pVal);

private static native double NativeGetCameraDeltaPitch(int instance);

private static native void NativeSetCameraDeltaPitch(int instance,double pVal);

private static native double NativeGetSpeed(int instance);

private static native void NativeSetSpeed(int instance,double pVal);

private static native String NativeGetMessageID(int instance);

private static native void NativeSetMessageID(int instance,String pVal);
};
