package com.skyline.teapi;

public final class ITerrainObject extends TEIUnknownHandle {
    private ITerrainObject(int handle)
    {
        super(handle);
    }    
    public static ITerrainObject fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ITerrainObject(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("6E9B74B2-5EB5-4C5C-879A-0D5E2E17B39D");

    public boolean getGroundObject() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetGroundObject(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setGroundObject(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetGroundObject(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getDrawOrder() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetDrawOrder(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setDrawOrder(double value) throws ApiException
    {
        checkDisposed();
        NativeSetDrawOrder(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IBBox3D getBBox() throws ApiException
    {
        checkDisposed();
        IBBox3D result = IBBox3D.fromHandle(NativeGetBBox(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IColor getTint() throws ApiException
    {
        checkDisposed();
        IColor result = IColor.fromHandle(NativeGetTint(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setTint(IColor value) throws ApiException
    {
        checkDisposed();
        NativeSetTint(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double GetRecommendedDistance() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetRecommendedDistance(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native boolean NativeGetGroundObject(int instance);

private static native void NativeSetGroundObject(int instance,boolean pVal);

private static native double NativeGetDrawOrder(int instance);

private static native void NativeSetDrawOrder(int instance,double pVal);

private static native int NativeGetBBox(int instance);

private static native int NativeGetTint(int instance);

private static native void NativeSetTint(int instance,IColor pVal);

private static native double NativeGetRecommendedDistance(int instance);
};
