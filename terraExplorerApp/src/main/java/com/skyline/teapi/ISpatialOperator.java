package com.skyline.teapi;

public final class ISpatialOperator extends TEIUnknownHandle {
    private ISpatialOperator(int handle)
    {
        super(handle);
    }    
    public static ISpatialOperator fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ISpatialOperator(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("84CE9E02-65AD-11D5-85C1-0001023952C1");

    public IGeometry Boundary() throws ApiException
    {
        checkDisposed();
        IGeometry result = IGeometry.fromHandle(NativeBoundary(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IGeometry buffer(double buffer) throws ApiException
    {
        checkDisposed();
        IGeometry result = IGeometry.fromHandle(Nativebuffer(this.getHandle(),buffer));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IGeometry Difference(IGeometry otherGeometry) throws ApiException
    {
        checkDisposed();
        IGeometry result = IGeometry.fromHandle(NativeDifference(this.getHandle(),otherGeometry));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double Distance(IGeometry otherGeometry) throws ApiException
    {
        checkDisposed();
        double result = (NativeDistance(this.getHandle(),otherGeometry));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IGeometry Intersection(IGeometry otherGeometry) throws ApiException
    {
        checkDisposed();
        IGeometry result = IGeometry.fromHandle(NativeIntersection(this.getHandle(),otherGeometry));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IGeometry SymmetricDifference(IGeometry otherGeometry) throws ApiException
    {
        checkDisposed();
        IGeometry result = IGeometry.fromHandle(NativeSymmetricDifference(this.getHandle(),otherGeometry));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IGeometry Union(IGeometry otherGeometry) throws ApiException
    {
        checkDisposed();
        IGeometry result = IGeometry.fromHandle(NativeUnion(this.getHandle(),otherGeometry));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native int NativeBoundary(int instance);

private static native int Nativebuffer(int instance,double buffer);

private static native int NativeDifference(int instance,IGeometry otherGeometry);

private static native double NativeDistance(int instance,IGeometry otherGeometry);

private static native int NativeIntersection(int instance,IGeometry otherGeometry);

private static native int NativeSymmetricDifference(int instance,IGeometry otherGeometry);

private static native int NativeUnion(int instance,IGeometry otherGeometry);
};
