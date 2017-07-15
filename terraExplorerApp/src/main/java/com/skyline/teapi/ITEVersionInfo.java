package com.skyline.teapi;

public final class ITEVersionInfo extends TEIUnknownHandle {
    private ITEVersionInfo(int handle)
    {
        super(handle);
    }    
    public static ITEVersionInfo fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ITEVersionInfo(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("2F112735-5D10-492F-8D5A-BA4C3971CC8A");

    public int getMajor() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetMajor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getMinor() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetMinor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getBuild() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetBuild(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getFreeze() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetFreeze(this.getHandle()));
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

private static native int NativeGetMajor(int instance);

private static native int NativeGetMinor(int instance);

private static native int NativeGetBuild(int instance);

private static native int NativeGetFreeze(int instance);

private static native int NativeGetType(int instance);
};
