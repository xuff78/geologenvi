package com.skyline.teapi;

public final class IFeatureLayer extends TEIUnknownHandle {
    private IFeatureLayer(int handle)
    {
        super(handle);
    }    
    public static IFeatureLayer fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IFeatureLayer(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("C194FCCE-0D52-4D86-8A07-0ABE7F83CED5");

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

    public IFeatureGroups getFeatureGroups() throws ApiException
    {
        checkDisposed();
        IFeatureGroups result = IFeatureGroups.fromHandle(NativeGetFeatureGroups(this.getHandle()));
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

    public IVisibility getVisibility() throws ApiException
    {
        checkDisposed();
        IVisibility result = IVisibility.fromHandle(NativeGetVisibility(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IDataSourceInfo getDataSourceInfo() throws ApiException
    {
        checkDisposed();
        IDataSourceInfo result = IDataSourceInfo.fromHandle(NativeGetDataSourceInfo(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getGeometryType() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetGeometryType(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getStreamStatus() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetStreamStatus(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setStreamStatus(int value) throws ApiException
    {
        checkDisposed();
        NativeSetStreamStatus(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IBBox2D getBBox() throws ApiException
    {
        checkDisposed();
        IBBox2D result = IBBox2D.fromHandle(NativeGetBBox(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setBBox(IBBox2D value) throws ApiException
    {
        checkDisposed();
        NativeSetBBox(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String getFilter() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetFilter(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setFilter(String value) throws ApiException
    {
        checkDisposed();
        NativeSetFilter(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getIgnoreZ() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetIgnoreZ(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setIgnoreZ(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetIgnoreZ(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getAltitudeUnit() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetAltitudeUnit(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setAltitudeUnit(int value) throws ApiException
    {
        checkDisposed();
        NativeSetAltitudeUnit(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double getBlockWidth() throws ApiException
    {
        checkDisposed();
        double result = (NativeGetBlockWidth(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setBlockWidth(double value) throws ApiException
    {
        checkDisposed();
        NativeSetBlockWidth(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getReproject() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetReproject(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setReproject(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetReproject(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getStreaming() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetStreaming(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setStreaming(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetStreaming(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getAnnotation() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetAnnotation(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setAnnotation(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetAnnotation(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getEditable() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetEditable(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean getDynamicAnnotationPlacement() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetDynamicAnnotationPlacement(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setDynamicAnnotationPlacement(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetDynamicAnnotationPlacement(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getMinimizeLabelDuplications() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetMinimizeLabelDuplications(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setMinimizeLabelDuplications(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetMinimizeLabelDuplications(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public ICoordinateSystem getCoordinateSystem() throws ApiException
    {
        checkDisposed();
        ICoordinateSystem result = ICoordinateSystem.fromHandle(NativeGetCoordinateSystem(this.getHandle()));
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

    public void Load() throws ApiException
    {
        checkDisposed();
        NativeLoad(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void Refresh() throws ApiException
    {
        checkDisposed();
        NativeRefresh(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void Save() throws ApiException
    {
        checkDisposed();
        NativeSave(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
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

private static native int NativeGetFeatureGroups(int instance);

private static native int NativeGetPosition(int instance);

private static native void NativeSetPosition(int instance,IPosition pVal);

private static native int NativeGetVisibility(int instance);

private static native int NativeGetDataSourceInfo(int instance);

private static native int NativeGetGeometryType(int instance);

private static native int NativeGetStreamStatus(int instance);

private static native void NativeSetStreamStatus(int instance,int pStreamStatus);

private static native int NativeGetBBox(int instance);

private static native void NativeSetBBox(int instance,IBBox2D pVal);

private static native String NativeGetFilter(int instance);

private static native void NativeSetFilter(int instance,String pVal);

private static native boolean NativeGetIgnoreZ(int instance);

private static native void NativeSetIgnoreZ(int instance,boolean pVal);

private static native int NativeGetAltitudeUnit(int instance);

private static native void NativeSetAltitudeUnit(int instance,int pVal);

private static native double NativeGetBlockWidth(int instance);

private static native void NativeSetBlockWidth(int instance,double pVal);

private static native boolean NativeGetReproject(int instance);

private static native void NativeSetReproject(int instance,boolean pVal);

private static native boolean NativeGetStreaming(int instance);

private static native void NativeSetStreaming(int instance,boolean pVal);

private static native boolean NativeGetAnnotation(int instance);

private static native void NativeSetAnnotation(int instance,boolean pVal);

private static native boolean NativeGetEditable(int instance);

private static native boolean NativeGetDynamicAnnotationPlacement(int instance);

private static native void NativeSetDynamicAnnotationPlacement(int instance,boolean pVal);

private static native boolean NativeGetMinimizeLabelDuplications(int instance);

private static native void NativeSetMinimizeLabelDuplications(int instance,boolean pVal);

private static native int NativeGetCoordinateSystem(int instance);

private static native int NativeGetSelectedFeatures(int instance);

private static native int NativeExecuteSpatialQuery(int instance,IGeometry pIGeometry,int intersectionType);

private static native Object NativeGetParam(int instance,int Code);

private static native void NativeLoad(int instance);

private static native void NativeRefresh(int instance);

private static native void NativeSave(int instance);

private static native String NativeSaveAs(int instance,String FileName,String AttributeFilter,IGeometry AreaFilter);

private static native void NativeSetParam(int instance,int Code,Object Param);
};
