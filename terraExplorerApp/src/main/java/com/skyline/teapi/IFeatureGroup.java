package com.skyline.teapi;

public final class IFeatureGroup extends TEIUnknownHandle {
    private IFeatureGroup(int handle)
    {
        super(handle);
    }    
    public static IFeatureGroup fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IFeatureGroup(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("CB3EB86A-C78D-4240-9197-0B947E35F9AD");

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

    public int getGeometryType() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetGeometryType(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getDisplayAs() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetDisplayAs(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setDisplayAs(int value) throws ApiException
    {
        checkDisposed();
        NativeSetDisplayAs(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String CreateFeature(Object Geometry,String Attributes) throws ApiException
    {
        checkDisposed();
        String result = (NativeCreateFeature(this.getHandle(),Geometry,Attributes));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String CreateFeature(Object Geometry) throws ApiException
    {
        return CreateFeature(Geometry,"");
    }

    public IFeature ExecuteGetDataSourceFeatureIdQuery(String FeatureID) throws ApiException
    {
        checkDisposed();
        IFeature result = IFeature.fromHandle(NativeExecuteGetDataSourceFeatureIdQuery(this.getHandle(),FeatureID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String GetClassification(String Name) throws ApiException
    {
        checkDisposed();
        String result = (NativeGetClassification(this.getHandle(),Name));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IFeatures GetCurrentFeatures() throws ApiException
    {
        checkDisposed();
        IFeatures result = IFeatures.fromHandle(NativeGetCurrentFeatures(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IFeature GetFeatureByObjectID(String ObjectID) throws ApiException
    {
        checkDisposed();
        IFeature result = IFeature.fromHandle(NativeGetFeatureByObjectID(this.getHandle(),ObjectID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public Object GetParam(int Code) throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetParam(this.getHandle(),Code));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public Object GetProperty(String Name) throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetProperty(this.getHandle(),Name));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean IsAnnotation() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeIsAnnotation(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean IsClassified(String Name) throws ApiException
    {
        checkDisposed();
        boolean result = (NativeIsClassified(this.getHandle(),Name));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void RemoveFeature(String ObjectIdStr) throws ApiException
    {
        checkDisposed();
        NativeRemoveFeature(this.getHandle(),ObjectIdStr);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SetClassification(String Name,String Value) throws ApiException
    {
        checkDisposed();
        NativeSetClassification(this.getHandle(),Name,Value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SetParam(int Code,Object Param) throws ApiException
    {
        checkDisposed();
        NativeSetParam(this.getHandle(),Code,Param);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SetProperty(String Name,Object Value) throws ApiException
    {
        checkDisposed();
        NativeSetProperty(this.getHandle(),Name,Value);
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native String NativeGetID(int instance);

private static native int NativeGetObjectType(int instance);

private static native String NativeGetClientData(int instance,String Namespace);

private static native void NativeSetClientData(int instance,String Namespace,String pVal);

private static native boolean NativeGetSaveInFlyFile(int instance);

private static native void NativeSetSaveInFlyFile(int instance,boolean pVal);

private static native int NativeGetTreeItem(int instance);

private static native int NativeGetGeometryType(int instance);

private static native int NativeGetDisplayAs(int instance);

private static native void NativeSetDisplayAs(int instance,int ObjectType);

private static native String NativeCreateFeature(int instance,Object Geometry,String Attributes);

private static native int NativeExecuteGetDataSourceFeatureIdQuery(int instance,String FeatureID);

private static native String NativeGetClassification(int instance,String Name);

private static native int NativeGetCurrentFeatures(int instance);

private static native int NativeGetFeatureByObjectID(int instance,String ObjectID);

private static native Object NativeGetParam(int instance,int Code);

private static native Object NativeGetProperty(int instance,String Name);

private static native boolean NativeIsAnnotation(int instance);

private static native boolean NativeIsClassified(int instance,String Name);

private static native void NativeRemoveFeature(int instance,String ObjectIdStr);

private static native void NativeSetClassification(int instance,String Name,String Value);

private static native void NativeSetParam(int instance,int Code,Object Param);

private static native void NativeSetProperty(int instance,String Name,Object Value);
};
