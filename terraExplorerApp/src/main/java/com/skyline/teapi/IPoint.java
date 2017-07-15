package com.skyline.teapi;

public final class IPoint extends TEIUnknownHandle {
    private IPoint(int handle)
    {
        super(handle);
    }    
    public static IPoint fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IPoint(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("84CE9DF6-65AD-11D5-85C1-0001023952C1");

    public int getDimension() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetDimension(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IGeometry getEnvelope() throws ApiException
    {
        checkDisposed();
        IGeometry result = IGeometry.fromHandle(NativeGetEnvelope(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getGeometryType() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetGeometryType(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String getGeometryTypeStr() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetGeometryTypeStr(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IWks getWks() throws ApiException
    {
        checkDisposed();
        IWks result = IWks.fromHandle(NativeGetWks(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ISpatialOperator getSpatialOperator() throws ApiException
    {
        checkDisposed();
        ISpatialOperator result = ISpatialOperator.fromHandle(NativeGetSpatialOperator(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ISpatialRelation getSpatialRelation() throws ApiException
    {
        checkDisposed();
        ISpatialRelation result = ISpatialRelation.fromHandle(NativeGetSpatialRelation(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

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

    public double getZ() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetZ(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setZ(double value) throws ApiException
    {
        checkDisposed();
        NativeSetZ(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IGeometry Clone() throws ApiException
    {
        checkDisposed();
        IGeometry result = IGeometry.fromHandle(NativeClone(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IGeometry EndEdit() throws ApiException
    {
        checkDisposed();
        IGeometry result = IGeometry.fromHandle(NativeEndEdit(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean IsEmpty() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeIsEmpty(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean IsSimple() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeIsSimple(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void SetEmpty() throws ApiException
    {
        checkDisposed();
        NativeSetEmpty(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void StartEdit() throws ApiException
    {
        checkDisposed();
        NativeStartEdit(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native int NativeGetDimension(int instance);

private static native int NativeGetEnvelope(int instance);

private static native int NativeGetGeometryType(int instance);

private static native String NativeGetGeometryTypeStr(int instance);

private static native int NativeGetWks(int instance);

private static native int NativeGetSpatialOperator(int instance);

private static native int NativeGetSpatialRelation(int instance);

private static native double NativeGetX(int instance);

private static native void NativeSetX(int instance,double X);

private static native double NativeGetY(int instance);

private static native void NativeSetY(int instance,double Y);

private static native double NativeGetZ(int instance);

private static native void NativeSetZ(int instance,double Z);

private static native int NativeClone(int instance);

private static native int NativeEndEdit(int instance);

private static native boolean NativeIsEmpty(int instance);

private static native boolean NativeIsSimple(int instance);

private static native void NativeSetEmpty(int instance);

private static native void NativeStartEdit(int instance);
};
