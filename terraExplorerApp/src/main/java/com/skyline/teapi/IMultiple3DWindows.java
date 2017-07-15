package com.skyline.teapi;

public final class IMultiple3DWindows extends TEIUnknownHandle {
    private IMultiple3DWindows(int handle)
    {
        super(handle);
    }    
    public static IMultiple3DWindows fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IMultiple3DWindows(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("0960F56F-81C1-4CFB-88E5-3E187E8359C3");

    public boolean IsLeader() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeIsLeader(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void LinkPosition(Object target,double OffsetX,double OffsetY,double OffsetAltitude,double OffsetYaw,double OffsetPitch,int LinkFlags) throws ApiException
    {
        checkDisposed();
        NativeLinkPosition(this.getHandle(),target,OffsetX,OffsetY,OffsetAltitude,OffsetYaw,OffsetPitch,LinkFlags);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void LinkPosition(Object target) throws ApiException
    {
        LinkPosition(target,0,0,0,0,0,LinkPositionFlags.LP_NONE);
    }

    public void LinkPosition(Object target,double OffsetX) throws ApiException
    {
        LinkPosition(target,OffsetX,0,0,0,0,LinkPositionFlags.LP_NONE);
    }

    public void LinkPosition(Object target,double OffsetX,double OffsetY) throws ApiException
    {
        LinkPosition(target,OffsetX,OffsetY,0,0,0,LinkPositionFlags.LP_NONE);
    }

    public void LinkPosition(Object target,double OffsetX,double OffsetY,double OffsetAltitude) throws ApiException
    {
        LinkPosition(target,OffsetX,OffsetY,OffsetAltitude,0,0,LinkPositionFlags.LP_NONE);
    }

    public void LinkPosition(Object target,double OffsetX,double OffsetY,double OffsetAltitude,double OffsetYaw) throws ApiException
    {
        LinkPosition(target,OffsetX,OffsetY,OffsetAltitude,OffsetYaw,0,LinkPositionFlags.LP_NONE);
    }

    public void LinkPosition(Object target,double OffsetX,double OffsetY,double OffsetAltitude,double OffsetYaw,double OffsetPitch) throws ApiException
    {
        LinkPosition(target,OffsetX,OffsetY,OffsetAltitude,OffsetYaw,OffsetPitch,LinkPositionFlags.LP_NONE);
    }

    public void SetAsLeader() throws ApiException
    {
        checkDisposed();
        NativeSetAsLeader(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void UnlinkPosition() throws ApiException
    {
        checkDisposed();
        NativeUnlinkPosition(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native boolean NativeIsLeader(int instance);

private static native void NativeLinkPosition(int instance,Object target,double OffsetX,double OffsetY,double OffsetAltitude,double OffsetYaw,double OffsetPitch,int LinkFlags);

private static native void NativeSetAsLeader(int instance);

private static native void NativeUnlinkPosition(int instance);
};
