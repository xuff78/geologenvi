package com.skyline.teapi;

public final class ICommand extends TEIUnknownHandle {
    private ICommand(int handle)
    {
        super(handle);
    }    
    public static ICommand fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ICommand(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("F5CE90E3-0CC2-4D98-AC51-A765ACA37EEE");

    public boolean CanExecute(int CommandID,Object parameter) throws ApiException
    {
        checkDisposed();
        boolean result = (NativeCanExecute(this.getHandle(),CommandID,parameter));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void Execute(int CommandID,Object parameters) throws ApiException
    {
        checkDisposed();
        NativeExecute(this.getHandle(),CommandID,parameters);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public Object GetValue(int CommandID) throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetValue(this.getHandle(),CommandID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean IsChecked(int CommandID,Object parameters) throws ApiException
    {
        checkDisposed();
        boolean result = (NativeIsChecked(this.getHandle(),CommandID,parameters));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native boolean NativeCanExecute(int instance,int CommandID,Object parameter);

private static native void NativeExecute(int instance,int CommandID,Object parameters);

private static native Object NativeGetValue(int instance,int CommandID);

private static native boolean NativeIsChecked(int instance,int CommandID,Object parameters);
};
