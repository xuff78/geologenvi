package com.skyline.teapi;

public final class IAction extends TEIUnknownHandle {
    private IAction(int handle)
    {
        super(handle);
    }    
    public static IAction fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IAction(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("2627E9AC-B8CA-4DCB-8676-57E3AFD1A17C");

    public int getCode() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetCode(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setCode(int value) throws ApiException
    {
        checkDisposed();
        NativeSetCode(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public Object getParam() throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetParam(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setParam(Object value) throws ApiException
    {
        checkDisposed();
        NativeSetParam(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native int NativeGetCode(int instance);

private static native void NativeSetCode(int instance,int pVal);

private static native Object NativeGetParam(int instance);

private static native void NativeSetParam(int instance,Object pVal);
};
