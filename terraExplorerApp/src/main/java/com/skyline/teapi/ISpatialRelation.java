package com.skyline.teapi;

public final class ISpatialRelation extends TEIUnknownHandle {
    private ISpatialRelation(int handle)
    {
        super(handle);
    }    
    public static ISpatialRelation fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ISpatialRelation(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("84CE9E01-65AD-11D5-85C1-0001023952C1");

    public boolean Contains(IGeometry otherGeometry) throws ApiException
    {
        checkDisposed();
        boolean result = (NativeContains(this.getHandle(),otherGeometry));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean Crosses(IGeometry otherGeometry) throws ApiException
    {
        checkDisposed();
        boolean result = (NativeCrosses(this.getHandle(),otherGeometry));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean Disjoint(IGeometry otherGeometry) throws ApiException
    {
        checkDisposed();
        boolean result = (NativeDisjoint(this.getHandle(),otherGeometry));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean Equals(IGeometry otherGeometry) throws ApiException
    {
        checkDisposed();
        boolean result = (NativeEquals(this.getHandle(),otherGeometry));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean Intersects(IGeometry otherGeometry) throws ApiException
    {
        checkDisposed();
        boolean result = (NativeIntersects(this.getHandle(),otherGeometry));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean Overlaps(IGeometry otherGeometry) throws ApiException
    {
        checkDisposed();
        boolean result = (NativeOverlaps(this.getHandle(),otherGeometry));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean Touches(IGeometry otherGeometry) throws ApiException
    {
        checkDisposed();
        boolean result = (NativeTouches(this.getHandle(),otherGeometry));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean Within(IGeometry otherGeometry) throws ApiException
    {
        checkDisposed();
        boolean result = (NativeWithin(this.getHandle(),otherGeometry));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native boolean NativeContains(int instance,IGeometry otherGeometry);

private static native boolean NativeCrosses(int instance,IGeometry otherGeometry);

private static native boolean NativeDisjoint(int instance,IGeometry otherGeometry);

private static native boolean NativeEquals(int instance,IGeometry otherGeometry);

private static native boolean NativeIntersects(int instance,IGeometry otherGeometry);

private static native boolean NativeOverlaps(int instance,IGeometry otherGeometry);

private static native boolean NativeTouches(int instance,IGeometry otherGeometry);

private static native boolean NativeWithin(int instance,IGeometry otherGeometry);
};
