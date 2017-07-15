package com.skyline.teapi;

public final class I3DMLFeatureLayers extends TEIUnknownHandle {
    private I3DMLFeatureLayers(int handle)
    {
        super(handle);
    }    
    public static I3DMLFeatureLayers fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new I3DMLFeatureLayers(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("06945ADF-F93F-4092-B8D6-3FFE457C7BA7");

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
