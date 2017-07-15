package com.skyline.teapi;

public final class IModuleLicenseInfo extends TEIUnknownHandle {
    private IModuleLicenseInfo(int handle)
    {
        super(handle);
    }    
    public static IModuleLicenseInfo fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IModuleLicenseInfo(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("C47E25A6-797A-4F49-AE96-124056EFB6D6");

    public String getModule() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetModule(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean getExist() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetExist(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean getValid() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetValid(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getErrCode() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetErrCode(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getErrCodeEx() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetErrCodeEx(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String getErrString() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetErrString(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String getPurchaseType() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetPurchaseType(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public Object getExpirationDate() throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetExpirationDate(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String getVersion() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetVersion(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native String NativeGetModule(int instance);

private static native boolean NativeGetExist(int instance);

private static native boolean NativeGetValid(int instance);

private static native int NativeGetErrCode(int instance);

private static native int NativeGetErrCodeEx(int instance);

private static native String NativeGetErrString(int instance);

private static native String NativeGetPurchaseType(int instance);

private static native Object NativeGetExpirationDate(int instance);

private static native String NativeGetVersion(int instance);
};
