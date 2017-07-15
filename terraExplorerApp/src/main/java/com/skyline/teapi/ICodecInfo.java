package com.skyline.teapi;

public final class ICodecInfo extends TEIUnknownHandle {
    private ICodecInfo(int handle)
    {
        super(handle);
    }    
    public static ICodecInfo fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ICodecInfo(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("CAD76246-B1AF-4527-B5BF-697549406FC4");

    public double getQuality() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetQuality(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setQuality(double value) throws ApiException
    {
        checkDisposed();
        NativeSetQuality(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean CanSetQuality() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeCanSetQuality(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double GetDefaultQuality() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetDefaultQuality(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String GetName() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetName(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean IsConfigurable() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeIsConfigurable(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void ShowConfigDialog() throws ApiException
    {
        checkDisposed();
        NativeShowConfigDialog(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native double NativeGetQuality(int instance);

private static native void NativeSetQuality(int instance,double pVal);

private static native boolean NativeCanSetQuality(int instance);

private static native double NativeGetDefaultQuality(int instance);

private static native String NativeGetName(int instance);

private static native boolean NativeIsConfigurable(int instance);

private static native void NativeShowConfigDialog(int instance);
};
