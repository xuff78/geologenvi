package com.skyline.teapi;

public final class IWorldPointInfo extends TEIUnknownHandle {
    private IWorldPointInfo(int handle)
    {
        super(handle);
    }    
    public static IWorldPointInfo fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IWorldPointInfo(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("966592F6-FE3D-4311-97FA-4555CB23BFC0");

    public IPosition getPosition() throws ApiException
    {
        checkDisposed();
        IPosition result = IPosition.fromHandle(NativeGetPosition(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String getObjectID() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetObjectID(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getType() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetType(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native int NativeGetPosition(int instance);

private static native String NativeGetObjectID(int instance);

private static native int NativeGetType(int instance);
};
