package com.skyline.teapi;

public final class IWks extends TEIUnknownHandle {
    private IWks(int handle)
    {
        super(handle);
    }    
    public static IWks fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IWks(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("84CE9E00-65AD-11D5-85C1-0001023952C1");

    public Object ExportToWKB(boolean b3Dimension) throws ApiException
    {
        checkDisposed();
        Object result = (NativeExportToWKB(this.getHandle(),b3Dimension));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String ExportToWKT() throws ApiException
    {
        checkDisposed();
        String result = (NativeExportToWKT(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void ImportFromWKB(Object buffer) throws ApiException
    {
        checkDisposed();
        NativeImportFromWKB(this.getHandle(),buffer);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void ImportFromWKT(String wkt) throws ApiException
    {
        checkDisposed();
        NativeImportFromWKT(this.getHandle(),wkt);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native Object NativeExportToWKB(int instance,boolean b3Dimension);

private static native String NativeExportToWKT(int instance);

private static native void NativeImportFromWKB(int instance,Object buffer);

private static native void NativeImportFromWKT(int instance,String wkt);
};
