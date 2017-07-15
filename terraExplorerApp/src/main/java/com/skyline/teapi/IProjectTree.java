package com.skyline.teapi;

public final class IProjectTree extends TEIUnknownHandle {
    private IProjectTree(int handle)
    {
        super(handle);
    }    
    public static IProjectTree fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IProjectTree(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("C0ABDC26-750C-47E3-8894-1F1C618C8BB8");

    public String getHiddenGroupName() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetHiddenGroupName(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String getHiddenGroupID() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetHiddenGroupID(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String getRootID() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetRootID(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String getNotInTreeID() throws ApiException
    {
        checkDisposed();
        String result = (NativeGetNotInTreeID(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void _EditItemEmbedded(String ID,Object ParentWindow) throws ApiException
    {
        checkDisposed();
        Native_EditItemEmbedded(this.getHandle(),ID,ParentWindow);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String CreateGroup(String GroupName,String ParentGroupID) throws ApiException
    {
        checkDisposed();
        String result = (NativeCreateGroup(this.getHandle(),GroupName,ParentGroupID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String CreateGroup(String GroupName) throws ApiException
    {
        return CreateGroup(GroupName,"");
    }

    public String CreateLockedGroup(String GroupName,String ParentGroupID) throws ApiException
    {
        checkDisposed();
        String result = (NativeCreateLockedGroup(this.getHandle(),GroupName,ParentGroupID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String CreateLockedGroup(String GroupName) throws ApiException
    {
        return CreateLockedGroup(GroupName,"");
    }

    public void DeleteItem(String ID) throws ApiException
    {
        checkDisposed();
        NativeDeleteItem(this.getHandle(),ID);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void EditItem(String ID,int Flags) throws ApiException
    {
        checkDisposed();
        NativeEditItem(this.getHandle(),ID,Flags);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void EditItem(String ID) throws ApiException
    {
        EditItem(ID,EditItemFlags.EDIT_ITEM_USE_PROPERTY);
    }

    public void EditItems(Object Items) throws ApiException
    {
        checkDisposed();
        NativeEditItems(this.getHandle(),Items);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void EnableRedraw(boolean bEnable) throws ApiException
    {
        checkDisposed();
        NativeEnableRedraw(this.getHandle(),bEnable);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void EndEdit() throws ApiException
    {
        checkDisposed();
        NativeEndEdit(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void ExpandGroup(String GroupID,boolean bExpand) throws ApiException
    {
        checkDisposed();
        NativeExpandGroup(this.getHandle(),GroupID,bExpand);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String FindItem(String PathName) throws ApiException
    {
        checkDisposed();
        String result = (NativeFindItem(this.getHandle(),PathName));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IAction GetActivationCode(String GroupID) throws ApiException
    {
        checkDisposed();
        IAction result = IAction.fromHandle(NativeGetActivationCode(this.getHandle(),GroupID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String GetClientData(String ID,String Namespace) throws ApiException
    {
        checkDisposed();
        String result = (NativeGetClientData(this.getHandle(),ID,Namespace));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public Object GetGroupEndTime(String GroupID) throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetGroupEndTime(this.getHandle(),GroupID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPosition GetGroupLocation(String GroupID) throws ApiException
    {
        checkDisposed();
        IPosition result = IPosition.fromHandle(NativeGetGroupLocation(this.getHandle(),GroupID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public Object GetGroupStartTime(String GroupID) throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetGroupStartTime(this.getHandle(),GroupID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String GetItemName(String ID) throws ApiException
    {
        checkDisposed();
        String result = (NativeGetItemName(this.getHandle(),ID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IFeatureLayer GetLayer(String GroupID) throws ApiException
    {
        checkDisposed();
        IFeatureLayer result = IFeatureLayer.fromHandle(NativeGetLayer(this.getHandle(),GroupID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String GetNextItem(String ID,int Code) throws ApiException
    {
        checkDisposed();
        String result = (NativeGetNextItem(this.getHandle(),ID,Code));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerraExplorerObject GetObject(String ID) throws ApiException
    {
        checkDisposed();
        ITerraExplorerObject result = ITerraExplorerObject.fromHandle(NativeGetObject(this.getHandle(),ID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int GetVisibility(String ID) throws ApiException
    {
        checkDisposed();
        int result = (NativeGetVisibility(this.getHandle(),ID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean IsGroup(String ID) throws ApiException
    {
        checkDisposed();
        boolean result = (NativeIsGroup(this.getHandle(),ID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean IsLayer(String GroupID) throws ApiException
    {
        checkDisposed();
        boolean result = (NativeIsLayer(this.getHandle(),GroupID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean IsLocked(String ID) throws ApiException
    {
        checkDisposed();
        boolean result = (NativeIsLocked(this.getHandle(),ID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String LoadFlyLayer(String FlyURL,String ParentGroupID) throws ApiException
    {
        checkDisposed();
        String result = (NativeLoadFlyLayer(this.getHandle(),FlyURL,ParentGroupID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String LoadFlyLayer(String FlyURL) throws ApiException
    {
        return LoadFlyLayer(FlyURL,"");
    }

    public String LoadKmlLayer(String KmlURL,String ParentGroupID) throws ApiException
    {
        checkDisposed();
        String result = (NativeLoadKmlLayer(this.getHandle(),KmlURL,ParentGroupID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String LoadKmlLayer(String KmlURL) throws ApiException
    {
        return LoadKmlLayer(KmlURL,"");
    }

    public void LockGroup(String GroupID,boolean bLock) throws ApiException
    {
        checkDisposed();
        NativeLockGroup(this.getHandle(),GroupID,bLock);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void RenameGroup(String GroupID,String GroupName) throws ApiException
    {
        checkDisposed();
        NativeRenameGroup(this.getHandle(),GroupID,GroupName);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public String SaveAsFly(String FlyName,String GroupID) throws ApiException
    {
        checkDisposed();
        String result = (NativeSaveAsFly(this.getHandle(),FlyName,GroupID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String SaveAsKml(String KmlName,String GroupID) throws ApiException
    {
        checkDisposed();
        String result = (NativeSaveAsKml(this.getHandle(),KmlName,GroupID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void SelectItem(String ID,int Flags,int Reserved) throws ApiException
    {
        checkDisposed();
        NativeSelectItem(this.getHandle(),ID,Flags,Reserved);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SelectItem(String ID) throws ApiException
    {
        SelectItem(ID,0,0);
    }

    public void SelectItem(String ID,int Flags) throws ApiException
    {
        SelectItem(ID,Flags,0);
    }

    public void SetActivationCode(String GroupID,int ACode,int ActivationParam) throws ApiException
    {
        checkDisposed();
        NativeSetActivationCode(this.getHandle(),GroupID,ACode,ActivationParam);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SetActivationCode(String GroupID,int ACode) throws ApiException
    {
        SetActivationCode(GroupID,ACode,-1);
    }

    public void SetClientData(String ID,String Namespace,String ClientData) throws ApiException
    {
        checkDisposed();
        NativeSetClientData(this.getHandle(),ID,Namespace,ClientData);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SetGroupEndTime(String GroupID,Object newVal) throws ApiException
    {
        checkDisposed();
        NativeSetGroupEndTime(this.getHandle(),GroupID,newVal);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SetGroupLocation(String GroupID,Object newVal) throws ApiException
    {
        checkDisposed();
        NativeSetGroupLocation(this.getHandle(),GroupID,newVal);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SetGroupStartTime(String GroupID,Object newVal) throws ApiException
    {
        checkDisposed();
        NativeSetGroupStartTime(this.getHandle(),GroupID,newVal);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SetParent(String ID,String ParentGroupID) throws ApiException
    {
        checkDisposed();
        NativeSetParent(this.getHandle(),ID,ParentGroupID);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SetVisibility(String ID,boolean bShow) throws ApiException
    {
        checkDisposed();
        NativeSetVisibility(this.getHandle(),ID,bShow);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void ShowSubTree(String GroupID,int Flags) throws ApiException
    {
        checkDisposed();
        NativeShowSubTree(this.getHandle(),GroupID,Flags);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SortGroup(String GroupID,int SType) throws ApiException
    {
        checkDisposed();
        NativeSortGroup(this.getHandle(),GroupID,SType);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SortGroup(String GroupID) throws ApiException
    {
        SortGroup(GroupID,SortType.SORT_ALPHABETICALLY_AZ);
    }

private static native String NativeGetHiddenGroupName(int instance);

private static native String NativeGetHiddenGroupID(int instance);

private static native String NativeGetRootID(int instance);

private static native String NativeGetNotInTreeID(int instance);

private static native void Native_EditItemEmbedded(int instance,String ID,Object ParentWindow);

private static native String NativeCreateGroup(int instance,String GroupName,String ParentGroupID);

private static native String NativeCreateLockedGroup(int instance,String GroupName,String ParentGroupID);

private static native void NativeDeleteItem(int instance,String ID);

private static native void NativeEditItem(int instance,String ID,int Flags);

private static native void NativeEditItems(int instance,Object Items);

private static native void NativeEnableRedraw(int instance,boolean bEnable);

private static native void NativeEndEdit(int instance);

private static native void NativeExpandGroup(int instance,String GroupID,boolean bExpand);

private static native String NativeFindItem(int instance,String PathName);

private static native int NativeGetActivationCode(int instance,String GroupID);

private static native String NativeGetClientData(int instance,String ID,String Namespace);

private static native Object NativeGetGroupEndTime(int instance,String GroupID);

private static native int NativeGetGroupLocation(int instance,String GroupID);

private static native Object NativeGetGroupStartTime(int instance,String GroupID);

private static native String NativeGetItemName(int instance,String ID);

private static native int NativeGetLayer(int instance,String GroupID);

private static native String NativeGetNextItem(int instance,String ID,int Code);

private static native int NativeGetObject(int instance,String ID);

private static native int NativeGetVisibility(int instance,String ID);

private static native boolean NativeIsGroup(int instance,String ID);

private static native boolean NativeIsLayer(int instance,String GroupID);

private static native boolean NativeIsLocked(int instance,String ID);

private static native String NativeLoadFlyLayer(int instance,String FlyURL,String ParentGroupID);

private static native String NativeLoadKmlLayer(int instance,String KmlURL,String ParentGroupID);

private static native void NativeLockGroup(int instance,String GroupID,boolean bLock);

private static native void NativeRenameGroup(int instance,String GroupID,String GroupName);

private static native String NativeSaveAsFly(int instance,String FlyName,String GroupID);

private static native String NativeSaveAsKml(int instance,String KmlName,String GroupID);

private static native void NativeSelectItem(int instance,String ID,int Flags,int Reserved);

private static native void NativeSetActivationCode(int instance,String GroupID,int ACode,int ActivationParam);

private static native void NativeSetClientData(int instance,String ID,String Namespace,String ClientData);

private static native void NativeSetGroupEndTime(int instance,String GroupID,Object newVal);

private static native void NativeSetGroupLocation(int instance,String GroupID,Object newVal);

private static native void NativeSetGroupStartTime(int instance,String GroupID,Object newVal);

private static native void NativeSetParent(int instance,String ID,String ParentGroupID);

private static native void NativeSetVisibility(int instance,String ID,boolean bShow);

private static native void NativeShowSubTree(int instance,String GroupID,int Flags);

private static native void NativeSortGroup(int instance,String GroupID,int SType);
};
