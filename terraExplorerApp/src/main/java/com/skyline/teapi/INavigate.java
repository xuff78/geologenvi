package com.skyline.teapi;

public final class INavigate extends TEIUnknownHandle {
    private INavigate(int handle)
    {
        super(handle);
    }    
    public static INavigate fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new INavigate(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("243F2327-BFE7-45D0-B34C-1929E77E083C");

    public boolean getUndergroundMode() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetUndergroundMode(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setUndergroundMode(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetUndergroundMode(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getFieldOfView() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetFieldOfView(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setFieldOfView(double value) throws ApiException
    {
        checkDisposed();
        NativeSetFieldOfView(this.getHandle(),value);
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

    public double DetectCollisionToTarget(IPosition target) throws ApiException
    {
        checkDisposed();
        double result = (NativeDetectCollisionToTarget(this.getHandle(),target));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void FlyThrough(Object arrTargets) throws ApiException
    {
        checkDisposed();
        NativeFlyThrough(this.getHandle(),arrTargets);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void FlyTo(Object target,int Pattern) throws ApiException
    {
        checkDisposed();
        NativeFlyTo(this.getHandle(),target,Pattern);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void FlyTo(Object target) throws ApiException
    {
        FlyTo(target,ActionCode.AC_FLYTO);
    }

    public IPosition GetPosition(int AltitudeType) throws ApiException
    {
        checkDisposed();
        IPosition result = IPosition.fromHandle(NativeGetPosition(this.getHandle(),AltitudeType));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPosition GetPosition() throws ApiException
    {
        return GetPosition(AltitudeTypeCode.ATC_TERRAIN_RELATIVE);
    }

    public void JumpTo(Object target) throws ApiException
    {
        checkDisposed();
        NativeJumpTo(this.getHandle(),target);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SetGPSMode(int GPSMode) throws ApiException
    {
        checkDisposed();
        NativeSetGPSMode(this.getHandle(),GPSMode);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SetGPSPosition(IPosition Position) throws ApiException
    {
        checkDisposed();
        NativeSetGPSPosition(this.getHandle(),Position);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SetPosition(IPosition Position) throws ApiException
    {
        checkDisposed();
        NativeSetPosition(this.getHandle(),Position);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void Stop() throws ApiException
    {
        checkDisposed();
        NativeStop(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void ZoomIn(double delta) throws ApiException
    {
        checkDisposed();
        NativeZoomIn(this.getHandle(),delta);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void ZoomIn() throws ApiException
    {
        ZoomIn(0);
    }

    public void ZoomOut(double delta) throws ApiException
    {
        checkDisposed();
        NativeZoomOut(this.getHandle(),delta);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void ZoomOut() throws ApiException
    {
        ZoomOut(0);
    }

    public void ZoomTo(double distanceFromPOI,int Flags) throws ApiException
    {
        checkDisposed();
        NativeZoomTo(this.getHandle(),distanceFromPOI,Flags);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void ZoomTo(double distanceFromPOI) throws ApiException
    {
        ZoomTo(distanceFromPOI,0);
    }

private static native boolean NativeGetUndergroundMode(int instance);

private static native void NativeSetUndergroundMode(int instance,boolean pVal);

private static native double NativeGetFieldOfView(int instance);

private static native void NativeSetFieldOfView(int instance,double pVal);

private static native double NativeGetSpeed(int instance);

private static native void NativeSetSpeed(int instance,double pVal);

private static native double NativeDetectCollisionToTarget(int instance,IPosition target);

private static native void NativeFlyThrough(int instance,Object arrTargets);

private static native void NativeFlyTo(int instance,Object target,int Pattern);

private static native int NativeGetPosition(int instance,int AltitudeType);

private static native void NativeJumpTo(int instance,Object target);

private static native void NativeSetGPSMode(int instance,int GPSMode);

private static native void NativeSetGPSPosition(int instance,IPosition Position);

private static native void NativeSetPosition(int instance,IPosition Position);

private static native void NativeStop(int instance);

private static native void NativeZoomIn(int instance,double delta);

private static native void NativeZoomOut(int instance,double delta);

private static native void NativeZoomTo(int instance,double distanceFromPOI,int Flags);
};
