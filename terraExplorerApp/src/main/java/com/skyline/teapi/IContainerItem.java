package com.skyline.teapi;

public final class IContainerItem extends TEIUnknownHandle {
    private IContainerItem(int handle)
    {
        super(handle);
    }    
    public static IContainerItem fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IContainerItem(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("FB5A34AF-3D38-4265-BEA1-4EFC1227682F");

    public int getItemID() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetItemID(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String getName() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetName(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setName(String value) throws ApiException
    {
        checkDisposed();
        NativeSetName(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getURL() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetURL(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setURL(String value) throws ApiException
    {
        checkDisposed();
        NativeSetURL(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

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

    public boolean getUseURL() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetUseURL(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setUseURL(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetUseURL(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getStartupSite() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetStartupSite(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setStartupSite(int value) throws ApiException
    {
        checkDisposed();
        NativeSetStartupSite(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int get_reserved() throws ApiException
    {
        checkDisposed();
        int result = (NativeGet_reserved(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native int NativeGetItemID(int instance);

private static native String NativeGetName(int instance);

private static native void NativeSetName(int instance,String pVal);

private static native String NativeGetURL(int instance);

private static native void NativeSetURL(int instance,String pVal);

private static native String NativeGetText(int instance);

private static native void NativeSetText(int instance,String pVal);

private static native boolean NativeGetUseURL(int instance);

private static native void NativeSetUseURL(int instance,boolean pVal);

private static native int NativeGetStartupSite(int instance);

private static native void NativeSetStartupSite(int instance,int pVal);

private static native int NativeGet_reserved(int instance);
};
