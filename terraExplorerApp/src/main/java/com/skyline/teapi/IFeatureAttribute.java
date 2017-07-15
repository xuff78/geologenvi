package com.skyline.teapi;

public  class IFeatureAttribute extends TEIUnknownHandle {
    protected IFeatureAttribute(int handle)
    {
        super(handle);
    }    
    public static IFeatureAttribute fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IFeatureAttribute(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("D3FDE445-4A9D-43E5-970C-9AFA4737F594");

    public String getName() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetName(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String getValue() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetValue(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setValue(String value) throws ApiException
    {
        checkDisposed();
        NativeSetValue(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native String NativeGetName(int instance);

private static native String NativeGetValue(int instance);

private static native void NativeSetValue(int instance,String pVal);
};
