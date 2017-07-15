package com.skyline.teapi;

public final class ILineString extends TEIUnknownHandle {
    private ILineString(int handle)
    {
        super(handle);
    }    
    public static ILineString fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ILineString(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("84CE9DF8-65AD-11D5-85C1-0001023952C1");

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

    public double getLength() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetLength(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPoint getStartPoint() throws ApiException
    {
        checkDisposed();
        IPoint result = IPoint.fromHandle(NativeGetStartPoint(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPoint getEndPoint() throws ApiException
    {
        checkDisposed();
        IPoint result = IPoint.fromHandle(NativeGetEndPoint(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPoint get_Value(double t) throws ApiException
    {
        checkDisposed();
        IPoint result = IPoint.fromHandle(NativeGetValue(this.getHandle(),t));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getNumPoints() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetNumPoints(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPoints getPoints() throws ApiException
    {
        checkDisposed();
        IPoints result = IPoints.fromHandle(NativeGetPoints(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
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

    public boolean IsClosed() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeIsClosed(this.getHandle()));
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

private static native double NativeGetLength(int instance);

private static native int NativeGetStartPoint(int instance);

private static native int NativeGetEndPoint(int instance);

private static native int NativeGetValue(int instance,double t);

private static native int NativeGetNumPoints(int instance);

private static native int NativeGetPoints(int instance);

private static native int NativeClone(int instance);

private static native int NativeEndEdit(int instance);

private static native boolean NativeIsClosed(int instance);

private static native boolean NativeIsEmpty(int instance);

private static native boolean NativeIsSimple(int instance);

private static native void NativeSetEmpty(int instance);

private static native void NativeStartEdit(int instance);
};
