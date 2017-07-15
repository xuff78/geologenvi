package com.skyline.teapi;

public final class IDataSourceInfo extends TEIUnknownHandle {
    private IDataSourceInfo(int handle)
    {
        super(handle);
    }    
    public static IDataSourceInfo fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IDataSourceInfo(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("2338D04A-F0D3-48C3-B439-6107756F23B5");

    public String getConnectionString() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetConnectionString(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setConnectionString(String value) throws ApiException
    {
        checkDisposed();
        NativeSetConnectionString(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IAttributes getAttributes() throws ApiException
    {
        checkDisposed();
        IAttributes result = IAttributes.fromHandle(NativeGetAttributes(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean getHasZ() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetHasZ(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IBBox2D getBBox() throws ApiException
    {
        checkDisposed();
        IBBox2D result = IBBox2D.fromHandle(NativeGetBBox(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getRecordsCount() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetRecordsCount(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native String NativeGetConnectionString(int instance);

private static native void NativeSetConnectionString(int instance,String pVal);

private static native int NativeGetAttributes(int instance);

private static native boolean NativeGetHasZ(int instance);

private static native int NativeGetBBox(int instance);

private static native int NativeGetRecordsCount(int instance);
};
