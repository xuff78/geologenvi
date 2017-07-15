package com.skyline.teapi;

public final class IRings extends TEIUnknownHandle {
    private IRings(int handle)
    {
        super(handle);
    }    
    public static IRings fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IRings(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("84CE9DFA-65AD-11D5-85C1-0001023952C1");

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

    public ILinearRing AddRing(Object var) throws ApiException
    {
        checkDisposed();
        ILinearRing result = ILinearRing.fromHandle(NativeAddRing(this.getHandle(),var));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ILinearRing AddRing() throws ApiException
    {
        return AddRing(0);
    }

    public void DeleteRing(int Index) throws ApiException
    {
        checkDisposed();
        NativeDeleteRing(this.getHandle(),Index);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native Object NativeGetItem(int instance,Object Index);

private static native int NativeGetCount(int instance);

private static native int NativeAddRing(int instance,Object var);

private static native void NativeDeleteRing(int instance,int Index);
};
