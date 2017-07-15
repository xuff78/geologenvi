package com.skyline.teapi;

public final class IPolygon extends TEIUnknownHandle {
    private IPolygon(int handle)
    {
        super(handle);
    }    
    public static IPolygon fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IPolygon(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("84CE9DFB-65AD-11D5-85C1-0001023952C1");

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

    public double getArea() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetArea(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPoint getCentroid() throws ApiException
    {
        checkDisposed();
        IPoint result = IPoint.fromHandle(NativeGetCentroid(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPoint getPointOnSurface() throws ApiException
    {
        checkDisposed();
        IPoint result = IPoint.fromHandle(NativeGetPointOnSurface(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ILinearRing getExteriorRing() throws ApiException
    {
        checkDisposed();
        ILinearRing result = ILinearRing.fromHandle(NativeGetExteriorRing(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ILinearRing get_InteriorRing(int Index) throws ApiException
    {
        checkDisposed();
        ILinearRing result = ILinearRing.fromHandle(NativeGetInteriorRing(this.getHandle(),Index));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getNumInteriorRings() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetNumInteriorRings(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IRings getRings() throws ApiException
    {
        checkDisposed();
        IRings result = IRings.fromHandle(NativeGetRings(this.getHandle()));
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

private static native double NativeGetArea(int instance);

private static native int NativeGetCentroid(int instance);

private static native int NativeGetPointOnSurface(int instance);

private static native int NativeGetExteriorRing(int instance);

private static native int NativeGetInteriorRing(int instance,int Index);

private static native int NativeGetNumInteriorRings(int instance);

private static native int NativeGetRings(int instance);

private static native int NativeClone(int instance);

private static native int NativeEndEdit(int instance);

private static native boolean NativeIsEmpty(int instance);

private static native boolean NativeIsSimple(int instance);

private static native void NativeSetEmpty(int instance);

private static native void NativeStartEdit(int instance);
};
