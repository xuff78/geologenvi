package com.skyline.teapi;

public final class IGeometryCreator extends TEIUnknownHandle {
    private IGeometryCreator(int handle)
    {
        super(handle);
    }    
    public static IGeometryCreator fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IGeometryCreator(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("84CE9E03-65AD-11D5-85C1-0001023952C1");

    public IGeometry CreateGeometryFromWKB(Object buffer) throws ApiException
    {
        checkDisposed();
        IGeometry result = IGeometry.fromHandle(NativeCreateGeometryFromWKB(this.getHandle(),buffer));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IGeometry CreateGeometryFromWKT(String buffer) throws ApiException
    {
        checkDisposed();
        IGeometry result = IGeometry.fromHandle(NativeCreateGeometryFromWKT(this.getHandle(),buffer));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ILinearRing CreateLinearRingGeometry(Object Vertices) throws ApiException
    {
        checkDisposed();
        ILinearRing result = ILinearRing.fromHandle(NativeCreateLinearRingGeometry(this.getHandle(),Vertices));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ILineString CreateLineStringGeometry(Object Vertices) throws ApiException
    {
        checkDisposed();
        ILineString result = ILineString.fromHandle(NativeCreateLineStringGeometry(this.getHandle(),Vertices));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IMultiLineString CreateMultiLineStringGeometry(Object lineStringList) throws ApiException
    {
        checkDisposed();
        IMultiLineString result = IMultiLineString.fromHandle(NativeCreateMultiLineStringGeometry(this.getHandle(),lineStringList));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IMultiPoint CreateMultiPointGeometry(Object pointList) throws ApiException
    {
        checkDisposed();
        IMultiPoint result = IMultiPoint.fromHandle(NativeCreateMultiPointGeometry(this.getHandle(),pointList));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IMultiPolygon CreateMultiPolygonGeometry(Object polygonList) throws ApiException
    {
        checkDisposed();
        IMultiPolygon result = IMultiPolygon.fromHandle(NativeCreateMultiPolygonGeometry(this.getHandle(),polygonList));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPoint CreatePointGeometry(Object vertex) throws ApiException
    {
        checkDisposed();
        IPoint result = IPoint.fromHandle(NativeCreatePointGeometry(this.getHandle(),vertex));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPolygon CreatePolygonGeometry(Object ExteriorRing,Object interiorRingList) throws ApiException
    {
        checkDisposed();
        IPolygon result = IPolygon.fromHandle(NativeCreatePolygonGeometry(this.getHandle(),ExteriorRing,interiorRingList));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPolygon CreatePolygonGeometry(Object ExteriorRing) throws ApiException
    {
        return CreatePolygonGeometry(ExteriorRing,0);
    }

private static native int NativeCreateGeometryFromWKB(int instance,Object buffer);

private static native int NativeCreateGeometryFromWKT(int instance,String buffer);

private static native int NativeCreateLinearRingGeometry(int instance,Object Vertices);

private static native int NativeCreateLineStringGeometry(int instance,Object Vertices);

private static native int NativeCreateMultiLineStringGeometry(int instance,Object lineStringList);

private static native int NativeCreateMultiPointGeometry(int instance,Object pointList);

private static native int NativeCreateMultiPolygonGeometry(int instance,Object polygonList);

private static native int NativeCreatePointGeometry(int instance,Object vertex);

private static native int NativeCreatePolygonGeometry(int instance,Object ExteriorRing,Object interiorRingList);
};
