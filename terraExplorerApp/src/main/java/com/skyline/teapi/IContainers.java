package com.skyline.teapi;

public final class IContainers extends TEIUnknownHandle {
    private IContainers(int handle)
    {
        super(handle);
    }    
    public static IContainers fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IContainers(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("768C4A04-A502-4A78-B2C3-1E8416889600");

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

    public int AddContainer(String Name,String URL,int StartupSite) throws ApiException
    {
        checkDisposed();
        int result = (NativeAddContainer(this.getHandle(),Name,URL,StartupSite));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IContainerItem GetContainer(int Index) throws ApiException
    {
        checkDisposed();
        IContainerItem result = IContainerItem.fromHandle(NativeGetContainer(this.getHandle(),Index));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void RemoveContainer(int Index) throws ApiException
    {
        checkDisposed();
        NativeRemoveContainer(this.getHandle(),Index);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native Object NativeGetItem(int instance,Object Index);

private static native int NativeGetCount(int instance);

private static native int NativeAddContainer(int instance,String Name,String URL,int StartupSite);

private static native int NativeGetContainer(int instance,int Index);

private static native void NativeRemoveContainer(int instance,int Index);
};
