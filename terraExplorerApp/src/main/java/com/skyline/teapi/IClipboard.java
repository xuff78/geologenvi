package com.skyline.teapi;

public final class IClipboard extends TEIUnknownHandle {
    private IClipboard(int handle)
    {
        super(handle);
    }    
    public static IClipboard fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IClipboard(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("360452A1-D8FB-41A7-A2B6-A74EFB51A32B");

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

    public void AddObject(String ObjectID) throws ApiException
    {
        checkDisposed();
        NativeAddObject(this.getHandle(),ObjectID);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void RemoveAll() throws ApiException
    {
        checkDisposed();
        NativeRemoveAll(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native Object NativeGetItem(int instance,Object Index);

private static native int NativeGetCount(int instance);

private static native void NativeAddObject(int instance,String ObjectID);

private static native void NativeRemoveAll(int instance);
};
