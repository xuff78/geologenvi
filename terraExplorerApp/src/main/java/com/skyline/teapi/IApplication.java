package com.skyline.teapi;

public final class IApplication extends TEIUnknownHandle {
    private IApplication(int handle)
    {
        super(handle);
    }    
    public static IApplication fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IApplication(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("90A3EFC8-5AA3-4CC6-80F2-ED721D701734");

    public IContainers getContainers() throws ApiException
    {
        checkDisposed();
        IContainers result = IContainers.fromHandle(NativeGetContainers(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean getCPUSaveMode() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetCPUSaveMode(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setCPUSaveMode(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetCPUSaveMode(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getSuppressUIErrors() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetSuppressUIErrors(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setSuppressUIErrors(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetSuppressUIErrors(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getDataPath() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetDataPath(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String getExecutablePath() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetExecutablePath(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean getEnableJoystick() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetEnableJoystick(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setEnableJoystick(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetEnableJoystick(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IMultiple3DWindows getMultiple3DWindows() throws ApiException
    {
        checkDisposed();
        IMultiple3DWindows result = IMultiple3DWindows.fromHandle(NativeGetMultiple3DWindows(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IClipboard getClipboard() throws ApiException
    {
        checkDisposed();
        IClipboard result = IClipboard.fromHandle(NativeGetClipboard(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native int NativeGetContainers(int instance);

private static native boolean NativeGetCPUSaveMode(int instance);

private static native void NativeSetCPUSaveMode(int instance,boolean pVal);

private static native boolean NativeGetSuppressUIErrors(int instance);

private static native void NativeSetSuppressUIErrors(int instance,boolean pVal);

private static native String NativeGetDataPath(int instance);

private static native String NativeGetExecutablePath(int instance);

private static native boolean NativeGetEnableJoystick(int instance);

private static native void NativeSetEnableJoystick(int instance,boolean pVal);

private static native int NativeGetMultiple3DWindows(int instance);

private static native int NativeGetClipboard(int instance);
};
