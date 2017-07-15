package com.skyline.teapi;

public final class ITerrain extends TEIUnknownHandle {
    private ITerrain(int handle)
    {
        super(handle);
    }    
    public static ITerrain fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ITerrain(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("3DEDFA83-4E7D-4B6A-B570-104DB94CA3D9");

    public ICoordinateSystem getCoordinateSystem() throws ApiException
    {
        checkDisposed();
        ICoordinateSystem result = ICoordinateSystem.fromHandle(NativeGetCoordinateSystem(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setCoordinateSystem(ICoordinateSystem value) throws ApiException
    {
        checkDisposed();
        NativeSetCoordinateSystem(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getName() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetName(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getLevels() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetLevels(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double getBestLevelWidth() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetBestLevelWidth(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double getBestMPP() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetBestMPP(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String getDescription() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetDescription(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double getLeft() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetLeft(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double getTop() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetTop(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double getRight() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetRight(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double getBottom() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetBottom(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double getOpacity() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetOpacity(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setOpacity(double value) throws ApiException
    {
        checkDisposed();
        NativeSetOpacity(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getStreamQuality() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetStreamQuality(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IWorldPointInfo GetGroundHeightInfo(double X,double Y,int Level,boolean IncludeGroundObject) throws ApiException
    {
        checkDisposed();
        IWorldPointInfo result = IWorldPointInfo.fromHandle(NativeGetGroundHeightInfo(this.getHandle(),X,Y,Level,IncludeGroundObject));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IWorldPointInfo GetGroundHeightInfo(double X,double Y,int Level) throws ApiException
    {
        return GetGroundHeightInfo(X,Y,Level,true);
    }

private static native int NativeGetCoordinateSystem(int instance);

private static native void NativeSetCoordinateSystem(int instance,ICoordinateSystem pVal);

private static native String NativeGetName(int instance);

private static native int NativeGetLevels(int instance);

private static native double NativeGetBestLevelWidth(int instance);

private static native double NativeGetBestMPP(int instance);

private static native String NativeGetDescription(int instance);

private static native double NativeGetLeft(int instance);

private static native double NativeGetTop(int instance);

private static native double NativeGetRight(int instance);

private static native double NativeGetBottom(int instance);

private static native double NativeGetOpacity(int instance);

private static native void NativeSetOpacity(int instance,double Opacity);

private static native int NativeGetStreamQuality(int instance);

private static native int NativeGetGroundHeightInfo(int instance,double X,double Y,int Level,boolean IncludeGroundObject);
};
