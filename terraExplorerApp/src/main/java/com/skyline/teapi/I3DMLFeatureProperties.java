package com.skyline.teapi;

public final class I3DMLFeatureProperties extends TEIUnknownHandle {
    private I3DMLFeatureProperties(int handle)
    {
        super(handle);
    }    
    public static I3DMLFeatureProperties fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new I3DMLFeatureProperties(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("A2EFAEA1-8234-43F8-824B-086B0639CAB2");

    public IColor getTint() throws ApiException
    {
        checkDisposed();
        IColor result = IColor.fromHandle(NativeGetTint(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setTint(IColor value) throws ApiException
    {
        checkDisposed();
        NativeSetTint(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getMessageID() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetMessageID(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setMessageID(String value) throws ApiException
    {
        checkDisposed();
        NativeSetMessageID(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public ITooltip getTooltip() throws ApiException
    {
        checkDisposed();
        ITooltip result = ITooltip.fromHandle(NativeGetTooltip(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native int NativeGetTint(int instance);

private static native void NativeSetTint(int instance,IColor pVal);

private static native String NativeGetMessageID(int instance);

private static native void NativeSetMessageID(int instance,String pVal);

private static native int NativeGetTooltip(int instance);
};
