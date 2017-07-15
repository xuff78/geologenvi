package com.skyline.teapi;

public  class IFeatureAttributes extends TEIUnknownHandle {
    protected IFeatureAttributes(int handle)
    {
        super(handle);
    }    
    public static IFeatureAttributes fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IFeatureAttributes(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("1785B3A9-D3E6-4A41-B269-D4A7BC8F4A3C");

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

    public IFeatureAttribute GetFeatureAttribute(String attributeNameStr) throws ApiException
    {
        checkDisposed();
        IFeatureAttribute result = IFeatureAttribute.fromHandle(NativeGetFeatureAttribute(this.getHandle(),attributeNameStr));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native Object NativeGetItem(int instance,Object Index);

private static native int NativeGetCount(int instance);

private static native int NativeGetFeatureAttribute(int instance,String attributeNameStr);
};
