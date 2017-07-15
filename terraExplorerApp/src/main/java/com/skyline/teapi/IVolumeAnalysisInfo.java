package com.skyline.teapi;

public final class IVolumeAnalysisInfo extends TEIUnknownHandle {
    private IVolumeAnalysisInfo(int handle)
    {
        super(handle);
    }    
    public static IVolumeAnalysisInfo fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IVolumeAnalysisInfo(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("CD983633-D504-4850-9307-25245E5D3004");

    public double getAddedCubicMeters() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetAddedCubicMeters(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double getRemovedCubicMeters() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetRemovedCubicMeters(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native double NativeGetAddedCubicMeters(int instance);

private static native double NativeGetRemovedCubicMeters(int instance);
};
