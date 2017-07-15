package com.skyline.teapi;

public final class IPresentationSteps extends TEIUnknownHandle {
    private IPresentationSteps(int handle)
    {
        super(handle);
    }    
    public static IPresentationSteps fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IPresentationSteps(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("AE90F75C-DFA0-4859-906E-615A8F8F308E");

    public Object get_Step(Object Index) throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetStep(this.getHandle(),Index));
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

    public int getCurrent() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetCurrent(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native Object NativeGetStep(int instance,Object Index);

private static native int NativeGetCount(int instance);

private static native int NativeGetCurrent(int instance);
};
