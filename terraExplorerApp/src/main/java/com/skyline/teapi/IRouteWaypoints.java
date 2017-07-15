package com.skyline.teapi;

public final class IRouteWaypoints extends TEIUnknownHandle {
    private IRouteWaypoints(int handle)
    {
        super(handle);
    }    
    public static IRouteWaypoints fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IRouteWaypoints(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("DDCBBD72-952E-4E25-BDEC-68EC2DEC8C90");

    public Object get_Item(Object Index) throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetItem(this.getHandle(),Index));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getCount() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetCount(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getCurrent() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetCurrent(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void AddWaypoint(IRouteWaypoint pWaypoint) throws ApiException
    {
        checkDisposed();
        NativeAddWaypoint(this.getHandle(),pWaypoint);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void DeleteWaypoint(int Index) throws ApiException
    {
        checkDisposed();
        NativeDeleteWaypoint(this.getHandle(),Index);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void FromArray(Object Waypoints) throws ApiException
    {
        checkDisposed();
        NativeFromArray(this.getHandle(),Waypoints);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IRouteWaypoint GetWaypoint(int Index) throws ApiException
    {
        checkDisposed();
        IRouteWaypoint result = IRouteWaypoint.fromHandle(NativeGetWaypoint(this.getHandle(),Index));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void InsertWaypoint(int IndexInsertAfter,IRouteWaypoint pWaypoint) throws ApiException
    {
        checkDisposed();
        NativeInsertWaypoint(this.getHandle(),IndexInsertAfter,pWaypoint);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void ModifyWaypoint(int Index,IRouteWaypoint pWaypoint) throws ApiException
    {
        checkDisposed();
        NativeModifyWaypoint(this.getHandle(),Index,pWaypoint);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public Object ToArray() throws ApiException
    {
        checkDisposed();
        Object result = (NativeToArray(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native Object NativeGetItem(int instance,Object Index);

private static native int NativeGetCount(int instance);

private static native int NativeGetCurrent(int instance);

private static native void NativeAddWaypoint(int instance,IRouteWaypoint pWaypoint);

private static native void NativeDeleteWaypoint(int instance,int Index);

private static native void NativeFromArray(int instance,Object Waypoints);

private static native int NativeGetWaypoint(int instance,int Index);

private static native void NativeInsertWaypoint(int instance,int IndexInsertAfter,IRouteWaypoint pWaypoint);

private static native void NativeModifyWaypoint(int instance,int Index,IRouteWaypoint pWaypoint);

private static native Object NativeToArray(int instance);
};
