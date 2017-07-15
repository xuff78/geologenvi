package com.skyline.teapi;

public final class IPointCloudDefaultLocation extends TEIUnknownHandle {
    private IPointCloudDefaultLocation(int handle)
    {
        super(handle);
    }    
    public static IPointCloudDefaultLocation fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IPointCloudDefaultLocation(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("6F8F792F-6A04-4A64-A3A3-233BE8E7BB70");

    public double getX() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetX(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double getY() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetY(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double getAltitude() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetAltitude(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ICoordinateSystem getCoordinateSystem() throws ApiException
    {
        checkDisposed();
        ICoordinateSystem result = ICoordinateSystem.fromHandle(NativeGetCoordinateSystem(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native double NativeGetX(int instance);

private static native double NativeGetY(int instance);

private static native double NativeGetAltitude(int instance);

private static native int NativeGetCoordinateSystem(int instance);
};
