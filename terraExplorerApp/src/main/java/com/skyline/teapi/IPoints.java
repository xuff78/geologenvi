package com.skyline.teapi;

public final class IPoints extends TEIUnknownHandle {
    private IPoints(int handle)
    {
        super(handle);
    }    
    public static IPoints fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IPoints(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("84CE9DF7-65AD-11D5-85C1-0001023952C1");

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

    public void AddPoint(double X,double Y,double Z) throws ApiException
    {
        checkDisposed();
        NativeAddPoint(this.getHandle(),X,Y,Z);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void DeletePoint(int Index) throws ApiException
    {
        checkDisposed();
        NativeDeletePoint(this.getHandle(),Index);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void InsertPoint(int InsertAfterIndex,double X,double Y,double Z) throws ApiException
    {
        checkDisposed();
        NativeInsertPoint(this.getHandle(),InsertAfterIndex,X,Y,Z);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public Object ToArray() throws ApiException
    {
        checkDisposed();
        Object result = (NativeToArray(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native Object NativeGetItem(int instance,Object Index);

private static native int NativeGetCount(int instance);

private static native void NativeAddPoint(int instance,double X,double Y,double Z);

private static native void NativeDeletePoint(int instance,int Index);

private static native void NativeInsertPoint(int instance,int InsertAfterIndex,double X,double Y,double Z);

private static native Object NativeToArray(int instance);
};
