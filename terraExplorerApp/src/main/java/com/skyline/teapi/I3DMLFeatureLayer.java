package com.skyline.teapi;

public final class I3DMLFeatureLayer extends TEIUnknownHandle {
    private I3DMLFeatureLayer(int handle)
    {
        super(handle);
    }    
    public static I3DMLFeatureLayer fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new I3DMLFeatureLayer(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("E285F3E3-0E55-457D-8A8E-4E85FDC1606A");

    public String getID() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetID(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getObjectType() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetObjectType(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String get_ClientData(String Namespace) throws ApiException
    {
        checkDisposed();
        String result = (NativeGetClientData(this.getHandle(),Namespace));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void set_ClientData(String Namespace,String pVal) throws ApiException
    {
        checkDisposed();
        NativeSetClientData(this.getHandle(),Namespace,pVal);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getSaveInFlyFile() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetSaveInFlyFile(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setSaveInFlyFile(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetSaveInFlyFile(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public ITreeItem getTreeItem() throws ApiException
    {
        checkDisposed();
        ITreeItem result = ITreeItem.fromHandle(NativeGetTreeItem(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public I3DMLFeatureProperties getFeatureProperties() throws ApiException
    {
        checkDisposed();
        I3DMLFeatureProperties result = I3DMLFeatureProperties.fromHandle(NativeGetFeatureProperties(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPosition getPosition() throws ApiException
    {
        checkDisposed();
        IPosition result = IPosition.fromHandle(NativeGetPosition(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setPosition(IPosition value) throws ApiException
    {
        checkDisposed();
        NativeSetPosition(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IDataSourceInfo getDataSourceInfo() throws ApiException
    {
        checkDisposed();
        IDataSourceInfo result = IDataSourceInfo.fromHandle(NativeGetDataSourceInfo(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IFeatures getSelectedFeatures() throws ApiException
    {
        checkDisposed();
        IFeatures result = IFeatures.fromHandle(NativeGetSelectedFeatures(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IFeature ExecuteGetDataSourceFeatureIdQuery(String FeatureID) throws ApiException
    {
        checkDisposed();
        IFeature result = IFeature.fromHandle(NativeExecuteGetDataSourceFeatureIdQuery(this.getHandle(),FeatureID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IFeatures ExecuteSpatialQuery(IGeometry pIGeometry,int IntersectionType) throws ApiException
    {
        checkDisposed();
        IFeatures result = IFeatures.fromHandle(NativeExecuteSpatialQuery(this.getHandle(),pIGeometry,IntersectionType));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IFeatures ExecuteSpatialQuery(IGeometry pIGeometry) throws ApiException
    {
        return ExecuteSpatialQuery(pIGeometry,IntersectionType.IT_INTERSECT);
    }

    public Object GetParam(int Code) throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetParam(this.getHandle(),Code));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String SaveAs(String FileName,String AttributeFilter,IGeometry AreaFilter) throws ApiException
    {
        checkDisposed();
        String result = (NativeSaveAs(this.getHandle(),FileName,AttributeFilter,AreaFilter));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void SetParam(int Code,Object Param) throws ApiException
    {
        checkDisposed();
        NativeSetParam(this.getHandle(),Code,Param);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native String NativeGetID(int instance);

private static native int NativeGetObjectType(int instance);

private static native String NativeGetClientData(int instance,String Namespace);

private static native void NativeSetClientData(int instance,String Namespace,String pVal);

private static native boolean NativeGetSaveInFlyFile(int instance);

private static native void NativeSetSaveInFlyFile(int instance,boolean pVal);

private static native int NativeGetTreeItem(int instance);

private static native int NativeGetFeatureProperties(int instance);

private static native int NativeGetPosition(int instance);

private static native void NativeSetPosition(int instance,IPosition pVal);

private static native int NativeGetDataSourceInfo(int instance);

private static native int NativeGetSelectedFeatures(int instance);

private static native int NativeExecuteGetDataSourceFeatureIdQuery(int instance,String FeatureID);

private static native int NativeExecuteSpatialQuery(int instance,IGeometry pIGeometry,int intersectionType);

private static native Object NativeGetParam(int instance,int Code);

private static native String NativeSaveAs(int instance,String FileName,String AttributeFilter,IGeometry AreaFilter);

private static native void NativeSetParam(int instance,int Code,Object Param);
};
