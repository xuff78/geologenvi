package com.skyline.teapi;

public final class ITooltip extends TEIUnknownHandle {
    private ITooltip(int handle)
    {
        super(handle);
    }    
    public static ITooltip fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ITooltip(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("E42644A2-A2C4-4D76-9DC8-4C737D71B800");

    public String getText() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetText(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setText(String value) throws ApiException
    {
        checkDisposed();
        NativeSetText(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native String NativeGetText(int instance);

private static native void NativeSetText(int instance,String pVal);
};
