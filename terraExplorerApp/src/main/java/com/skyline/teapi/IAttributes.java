package com.skyline.teapi;

public final class IAttributes extends TEIUnknownHandle {
    private IAttributes(int handle)
    {
        super(handle);
    }    
    public static IAttributes fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IAttributes(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("61CFBAEC-3B2E-4924-9614-A4094B8DA846");

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

    public IAttribute get_Attribute(Object Index) throws ApiException
    {
        checkDisposed();
        IAttribute result = IAttribute.fromHandle(NativeGetAttribute(this.getHandle(),Index));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setImportAll(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetImportAll(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void CreateAttribute(String AttributeName,int attributeType,int Size,int Precision) throws ApiException
    {
        checkDisposed();
        NativeCreateAttribute(this.getHandle(),AttributeName,attributeType,Size,Precision);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void CreateAttribute(String AttributeName,int attributeType,int Size) throws ApiException
    {
        CreateAttribute(AttributeName,attributeType,Size,15);
    }

    public boolean IsAttributeExist(String AttributeName) throws ApiException
    {
        checkDisposed();
        boolean result = (NativeIsAttributeExist(this.getHandle(),AttributeName));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native Object NativeGetItem(int instance,Object Index);

private static native int NativeGetCount(int instance);

private static native int NativeGetAttribute(int instance,Object Index);

private static native void NativeSetImportAll(int instance,boolean abc);

private static native void NativeCreateAttribute(int instance,String AttributeName,int attributeType,int Size,int Precision);

private static native boolean NativeIsAttributeExist(int instance,String AttributeName);
};
