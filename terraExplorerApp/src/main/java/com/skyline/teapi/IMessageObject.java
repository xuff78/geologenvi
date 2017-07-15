package com.skyline.teapi;

public final class IMessageObject extends TEIUnknownHandle {
    private IMessageObject(int handle)
    {
        super(handle);
    }    
    public static IMessageObject fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IMessageObject(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("0D90D231-F8F3-45DF-92F4-621EE102D7AC");

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

    public void Activate() throws ApiException
    {
        checkDisposed();
        NativeActivate(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public ITerraExplorerObject GetMessageObject() throws ApiException
    {
        checkDisposed();
        ITerraExplorerObject result = ITerraExplorerObject.fromHandle(NativeGetMessageObject(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native String NativeGetMessageID(int instance);

private static native void NativeSetMessageID(int instance,String pVal);

private static native void NativeActivate(int instance);

private static native int NativeGetMessageObject(int instance);
};
