package com.skyline.teapi;

public final class IFeatures extends TEIUnknownHandle {
    private IFeatures(int handle)
    {
        super(handle);
    }    
    public static IFeatures fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IFeatures(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("5457FB0D-6D7B-47CA-8AF3-FE91313BDCE6");

    public Object get_Item(Object Index) throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetItem(this.getHandle(),Index));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getCount() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetCount(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native Object NativeGetItem(int instance,Object Index);

private static native int NativeGetCount(int instance);
};
