package com.skyline.teapi;

public final class ILicenseManager extends TEIUnknownHandle {
    private ILicenseManager(int handle)
    {
        super(handle);
    }    
    public static ILicenseManager fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ILicenseManager(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("06D28DA3-401E-45BF-81DE-AD9347357249");

    public String GetAttribute(String Module,String AttributeName) throws ApiException
    {
        checkDisposed();
        String result = (NativeGetAttribute(this.getHandle(),Module,AttributeName));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IModuleLicenseInfo GetModuleInfo(String Module) throws ApiException
    {
        checkDisposed();
        IModuleLicenseInfo result = IModuleLicenseInfo.fromHandle(NativeGetModuleInfo(this.getHandle(),Module));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int GetPermission(int PermissionCode,Object parameters) throws ApiException
    {
        checkDisposed();
        int result = (NativeGetPermission(this.getHandle(),PermissionCode,parameters));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void SetClientID(String Module,String ClientID) throws ApiException
    {
        checkDisposed();
        NativeSetClientID(this.getHandle(),Module,ClientID);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native String NativeGetAttribute(int instance,String Module,String AttributeName);

private static native int NativeGetModuleInfo(int instance,String Module);

private static native int NativeGetPermission(int instance,int PermissionCode,Object parameters);

private static native void NativeSetClientID(int instance,String Module,String ClientID);
};
