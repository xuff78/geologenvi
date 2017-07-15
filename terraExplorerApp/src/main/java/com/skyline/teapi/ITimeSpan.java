package com.skyline.teapi;

public final class ITimeSpan extends TEIUnknownHandle {
    private ITimeSpan(int handle)
    {
        super(handle);
    }    
    public static ITimeSpan fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ITimeSpan(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("D4BB3E97-9BD4-4645-8D38-B0B431FD48C0");

    public Object getStart() throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetStart(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setStart(Object value) throws ApiException
    {
        checkDisposed();
        NativeSetStart(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public Object getEnd() throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetEnd(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setEnd(Object value) throws ApiException
    {
        checkDisposed();
        NativeSetEnd(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native Object NativeGetStart(int instance);

private static native void NativeSetStart(int instance,Object pVal);

private static native Object NativeGetEnd(int instance);

private static native void NativeSetEnd(int instance,Object pVal);
};
