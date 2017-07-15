package com.skyline.teapi;

public final class ISGWorld extends TEIUnknownHandle {
    private ISGWorld(int handle)
    {
        super(handle);
    }    
    public static ISGWorld fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ISGWorld(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("01F65968-A33F-4806-8300-A3DDB6B03A45");

	private static native int GetInstanceHandle();
	private static ISGWorld instance;
	public static ISGWorld getInstance()
	{
        if(android.os.Looper.myLooper() == android.os.Looper.getMainLooper()) // main thread, warning
        {
           android.util.Log.w("TEApi", "ISGWorld.getInstance() is called on UI thread and not on render thread.");
           //throw new ApiException("ISGWorld.getInstance() can't be called on UI thread.");
        }

		if(instance == null)
			instance = new ISGWorld(GetInstanceHandle());
		return instance;
	}
	public static void clean()
	{
		instance = null;
	}
private static native void NativeAttachEventEx(int instance,String bstrEventName,String eventSignature, Object listener);
private static native void NativeDetachEventEx(int instance,String bstrEventName,String eventSignature, Object listener);
public interface OnLoadFinishedListener { public void OnLoadFinished(boolean bSuccess); }

    public void addOnLoadFinishedListener(OnLoadFinishedListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnLoadFinished","(Z)V", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnLoadFinishedListener(OnLoadFinishedListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnLoadFinished","(Z)V",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnFileClosingListener { public void OnFileClosing(); }

    public void addOnFileClosingListener(OnFileClosingListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnFileClosing","()V", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnFileClosingListener(OnFileClosingListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnFileClosing","()V",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnFrameListener { public void OnFrame(); }

    public void addOnFrameListener(OnFrameListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnFrame","()V", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnFrameListener(OnFrameListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnFrame","()V",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnSGWorldMessageListener { public boolean OnSGWorldMessage(String MessageID,String SourceObjectID); }

    public void addOnSGWorldMessageListener(OnSGWorldMessageListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnSGWorldMessage","(Ljava/lang/String;Ljava/lang/String;)Z", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnSGWorldMessageListener(OnSGWorldMessageListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnSGWorldMessage","(Ljava/lang/String;Ljava/lang/String;)Z",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnObjectActionListener { public void OnObjectAction(String ObjectID,IAction Action); }

    public void addOnObjectActionListener(OnObjectActionListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnObjectAction","(Ljava/lang/String;Lcom/skyline/teapi/IAction;)V", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnObjectActionListener(OnObjectActionListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnObjectAction","(Ljava/lang/String;Lcom/skyline/teapi/IAction;)V",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnFileSaveListener { public void OnFileSave(); }

    public void addOnFileSaveListener(OnFileSaveListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnFileSave","()V", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnFileSaveListener(OnFileSaveListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnFileSave","()V",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnRenderQualityChangedListener { public void OnRenderQualityChanged(int Quality); }

    public void addOnRenderQualityChangedListener(OnRenderQualityChangedListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnRenderQualityChanged","(I)V", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnRenderQualityChangedListener(OnRenderQualityChangedListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnRenderQualityChanged","(I)V",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnInputModeChangedListener { public void OnInputModeChanged(int NewMode); }

    public void addOnInputModeChangedListener(OnInputModeChangedListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnInputModeChanged","(I)V", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnInputModeChangedListener(OnInputModeChangedListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnInputModeChanged","(I)V",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnLButtonDownListener { public boolean OnLButtonDown(int Flags,int X,int Y); }

    public void addOnLButtonDownListener(OnLButtonDownListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnLButtonDown","(III)Z", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnLButtonDownListener(OnLButtonDownListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnLButtonDown","(III)Z",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnLButtonUpListener { public boolean OnLButtonUp(int Flags,int X,int Y); }

    public void addOnLButtonUpListener(OnLButtonUpListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnLButtonUp","(III)Z", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnLButtonUpListener(OnLButtonUpListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnLButtonUp","(III)Z",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnMButtonDownListener { public boolean OnMButtonDown(int Flags,int X,int Y); }

    public void addOnMButtonDownListener(OnMButtonDownListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnMButtonDown","(III)Z", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnMButtonDownListener(OnMButtonDownListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnMButtonDown","(III)Z",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnMButtonUpListener { public boolean OnMButtonUp(int Flags,int X,int Y); }

    public void addOnMButtonUpListener(OnMButtonUpListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnMButtonUp","(III)Z", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnMButtonUpListener(OnMButtonUpListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnMButtonUp","(III)Z",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnRButtonDownListener { public boolean OnRButtonDown(int Flags,int X,int Y); }

    public void addOnRButtonDownListener(OnRButtonDownListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnRButtonDown","(III)Z", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnRButtonDownListener(OnRButtonDownListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnRButtonDown","(III)Z",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnRButtonUpListener { public boolean OnRButtonUp(int Flags,int X,int Y); }

    public void addOnRButtonUpListener(OnRButtonUpListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnRButtonUp","(III)Z", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnRButtonUpListener(OnRButtonUpListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnRButtonUp","(III)Z",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnMouseWheelListener { public boolean OnMouseWheel(int Flags,int zDelta,int X,int Y); }

    public void addOnMouseWheelListener(OnMouseWheelListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnMouseWheel","(IIII)Z", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnMouseWheelListener(OnMouseWheelListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnMouseWheel","(IIII)Z",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnProjectTreeActionListener { public void OnProjectTreeAction(String ID,IAction Action); }

    public void addOnProjectTreeActionListener(OnProjectTreeActionListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnProjectTreeAction","(Ljava/lang/String;Lcom/skyline/teapi/IAction;)V", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnProjectTreeActionListener(OnProjectTreeActionListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnProjectTreeAction","(Ljava/lang/String;Lcom/skyline/teapi/IAction;)V",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnSGWorldListener { public void OnSGWorld(int EventID,Object EventParam); }

    public void addOnSGWorldListener(OnSGWorldListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnSGWorld","(ILjava/lang/Object;)V", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnSGWorldListener(OnSGWorldListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnSGWorld","(ILjava/lang/Object;)V",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnDrawHUDListener { public void OnDrawHUD(); }

    public void addOnDrawHUDListener(OnDrawHUDListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnDrawHUD","()V", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnDrawHUDListener(OnDrawHUDListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnDrawHUD","()V",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnLButtonDblClkListener { public boolean OnLButtonDblClk(int Flags,int X,int Y); }

    public void addOnLButtonDblClkListener(OnLButtonDblClkListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnLButtonDblClk","(III)Z", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnLButtonDblClkListener(OnLButtonDblClkListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnLButtonDblClk","(III)Z",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnRButtonDblClkListener { public boolean OnRButtonDblClk(int Flags,int X,int Y); }

    public void addOnRButtonDblClkListener(OnRButtonDblClkListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnRButtonDblClk","(III)Z", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnRButtonDblClkListener(OnRButtonDblClkListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnRButtonDblClk","(III)Z",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnMButtonDblClkListener { public boolean OnMButtonDblClk(int Flags,int X,int Y); }

    public void addOnMButtonDblClkListener(OnMButtonDblClkListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnMButtonDblClk","(III)Z", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnMButtonDblClkListener(OnMButtonDblClkListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnMButtonDblClk","(III)Z",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnCreateBasicKitListener { public void OnCreateBasicKit(String BasicKitFolder,String BasicKitFileName); }

    public void addOnCreateBasicKitListener(OnCreateBasicKitListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnCreateBasicKit","(Ljava/lang/String;Ljava/lang/String;)V", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnCreateBasicKitListener(OnCreateBasicKitListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnCreateBasicKit","(Ljava/lang/String;Ljava/lang/String;)V",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnLayerStreamingListener { public void OnLayerStreaming(String LayerGroupID,boolean bStreaming); }

    public void addOnLayerStreamingListener(OnLayerStreamingListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnLayerStreaming","(Ljava/lang/String;Z)V", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnLayerStreamingListener(OnLayerStreamingListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnLayerStreaming","(Ljava/lang/String;Z)V",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnDateTimeChangedListener { public void OnDateTimeChanged(Object DateTime); }

    public void addOnDateTimeChangedListener(OnDateTimeChangedListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnDateTimeChanged","(Ljava/lang/Object;)V", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnDateTimeChangedListener(OnDateTimeChangedListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnDateTimeChanged","(Ljava/lang/Object;)V",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnContainerChangedListener { public void OnContainerChanged(int Operation,IContainerItem ContainerItem); }

    public void addOnContainerChangedListener(OnContainerChangedListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnContainerChanged","(ILcom/skyline/teapi/IContainerItem;)V", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnContainerChangedListener(OnContainerChangedListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnContainerChanged","(ILcom/skyline/teapi/IContainerItem;)V",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnCommandValueChangedListener { public void OnCommandValueChanged(int CommandID,Object newVal); }

    public void addOnCommandValueChangedListener(OnCommandValueChangedListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnCommandValueChanged","(ILjava/lang/Object;)V", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnCommandValueChangedListener(OnCommandValueChangedListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnCommandValueChanged","(ILjava/lang/Object;)V",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnFileClosedListener { public void OnFileClosed(); }

    public void addOnFileClosedListener(OnFileClosedListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnFileClosed","()V", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnFileClosedListener(OnFileClosedListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnFileClosed","()V",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnPresentationStatusChangedListener { public void OnPresentationStatusChanged(String PresentationID,int Status); }

    public void addOnPresentationStatusChangedListener(OnPresentationStatusChangedListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnPresentationStatusChanged","(Ljava/lang/String;I)V", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnPresentationStatusChangedListener(OnPresentationStatusChangedListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnPresentationStatusChanged","(Ljava/lang/String;I)V",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnPresentationFlyToReachedDestinationListener { public void OnPresentationFlyToReachedDestination(String PresentationID,IPresentationStep Step); }

    public void addOnPresentationFlyToReachedDestinationListener(OnPresentationFlyToReachedDestinationListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnPresentationFlyToReachedDestination","(Ljava/lang/String;Lcom/skyline/teapi/IPresentationStep;)V", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnPresentationFlyToReachedDestinationListener(OnPresentationFlyToReachedDestinationListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnPresentationFlyToReachedDestination","(Ljava/lang/String;Lcom/skyline/teapi/IPresentationStep;)V",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnBeforePresentationItemActivationListener { public void OnBeforePresentationItemActivation(String PresentationID,IPresentationStep Step); }

    public void addOnBeforePresentationItemActivationListener(OnBeforePresentationItemActivationListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnBeforePresentationItemActivation","(Ljava/lang/String;Lcom/skyline/teapi/IPresentationStep;)V", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnBeforePresentationItemActivationListener(OnBeforePresentationItemActivationListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnBeforePresentationItemActivation","(Ljava/lang/String;Lcom/skyline/teapi/IPresentationStep;)V",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnPresentationPlayTimeAnimationEndedListener { public void OnPresentationPlayTimeAnimationEnded(String PresentationID,IPresentationStep Step); }

    public void addOnPresentationPlayTimeAnimationEndedListener(OnPresentationPlayTimeAnimationEndedListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnPresentationPlayTimeAnimationEnded","(Ljava/lang/String;Lcom/skyline/teapi/IPresentationStep;)V", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnPresentationPlayTimeAnimationEndedListener(OnPresentationPlayTimeAnimationEndedListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnPresentationPlayTimeAnimationEnded","(Ljava/lang/String;Lcom/skyline/teapi/IPresentationStep;)V",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnAnalysisProgressListener { public boolean OnAnalysisProgress(int CurrPos,int Range); }

    public void addOnAnalysisProgressListener(OnAnalysisProgressListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnAnalysisProgress","(II)Z", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnAnalysisProgressListener(OnAnalysisProgressListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnAnalysisProgress","(II)Z",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnAnalysisDistancePointAddedListener { public void OnAnalysisDistancePointAdded(IGeometry pRuler,double AerialDist,double HorizontalDist,double Slope,double ElevationDifference); }

    public void addOnAnalysisDistancePointAddedListener(OnAnalysisDistancePointAddedListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnAnalysisDistancePointAdded","(Lcom/skyline/teapi/IGeometry;DDDD)V", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnAnalysisDistancePointAddedListener(OnAnalysisDistancePointAddedListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnAnalysisDistancePointAdded","(Lcom/skyline/teapi/IGeometry;DDDD)V",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnLButtonClickedListener { public boolean OnLButtonClicked(int Flags,int X,int Y); }

    public void addOnLButtonClickedListener(OnLButtonClickedListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnLButtonClicked","(III)Z", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnLButtonClickedListener(OnLButtonClickedListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnLButtonClicked","(III)Z",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
public interface OnFeatureIdChangedListener { public void OnFeatureIdChanged(String ObjectID,String oldFeatureId,String newFeatureId); }

    public void addOnFeatureIdChangedListener(OnFeatureIdChangedListener listener) throws ApiException
    {
        checkDisposed();
        NativeAttachEventEx(getHandle(),"OnFeatureIdChanged","(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", listener);
        TEErrorHelper.ThrowExceptionOnError();
    }
    public void removeOnFeatureIdChangedListener(OnFeatureIdChangedListener listener) throws ApiException
    {
        checkDisposed();
        NativeDetachEventEx(getHandle(),"OnFeatureIdChanged","(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V",listener);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IProject getProject() throws ApiException
    {
        checkDisposed();
        IProject result = IProject.fromHandle(NativeGetProject(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public INavigate getNavigate() throws ApiException
    {
        checkDisposed();
        INavigate result = INavigate.fromHandle(NativeGetNavigate(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ICreator getCreator() throws ApiException
    {
        checkDisposed();
        ICreator result = ICreator.fromHandle(NativeGetCreator(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IProjectTree getProjectTree() throws ApiException
    {
        checkDisposed();
        IProjectTree result = IProjectTree.fromHandle(NativeGetProjectTree(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IWindow getWindow() throws ApiException
    {
        checkDisposed();
        IWindow result = IWindow.fromHandle(NativeGetWindow(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrain getTerrain() throws ApiException
    {
        checkDisposed();
        ITerrain result = ITerrain.fromHandle(NativeGetTerrain(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ILicenseManager getLicenseManager() throws ApiException
    {
        checkDisposed();
        ILicenseManager result = ILicenseManager.fromHandle(NativeGetLicenseManager(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ICoordServices getCoordServices() throws ApiException
    {
        checkDisposed();
        ICoordServices result = ICoordServices.fromHandle(NativeGetCoordServices(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IDateTime getDateTime() throws ApiException
    {
        checkDisposed();
        IDateTime result = IDateTime.fromHandle(NativeGetDateTime(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITEVersionInfo getVersion() throws ApiException
    {
        checkDisposed();
        ITEVersionInfo result = ITEVersionInfo.fromHandle(NativeGetVersion(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IApplication getApplication() throws ApiException
    {
        checkDisposed();
        IApplication result = IApplication.fromHandle(NativeGetApplication(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ICommand getCommand() throws ApiException
    {
        checkDisposed();
        ICommand result = ICommand.fromHandle(NativeGetCommand(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IAnalysis getAnalysis() throws ApiException
    {
        checkDisposed();
        IAnalysis result = IAnalysis.fromHandle(NativeGetAnalysis(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public boolean getIgnoreAccelerators() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetIgnoreAccelerators(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setIgnoreAccelerators(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetIgnoreAccelerators(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void AttachEvent(String bstrEventName,Object dispFunc) throws ApiException
    {
        checkDisposed();
        NativeAttachEvent(this.getHandle(),bstrEventName,dispFunc);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void DetachEvent(String bstrEventName,Object dispFunc) throws ApiException
    {
        checkDisposed();
        NativeDetachEvent(this.getHandle(),bstrEventName,dispFunc);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public Object GetOptionParam(String paramName) throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetOptionParam(this.getHandle(),paramName));
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

    public void Open(String ProjectURL) throws ApiException
    {
        checkDisposed();
        NativeOpen(this.getHandle(),ProjectURL);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SetOptionParam(String paramName,Object paramVal) throws ApiException
    {
        checkDisposed();
        NativeSetOptionParam(this.getHandle(),paramName,paramVal);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SetParam(int Code,Object Param) throws ApiException
    {
        checkDisposed();
        NativeSetParam(this.getHandle(),Code,Param);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public Object SetParamEx(int Code,Object Param1,Object Param2) throws ApiException
    {
        checkDisposed();
        Object result = (NativeSetParamEx(this.getHandle(),Code,Param1,Param2));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public Object SetParamEx(int Code) throws ApiException
    {
        return SetParamEx(Code,0,0);
    }

    public Object SetParamEx(int Code,Object Param1) throws ApiException
    {
        return SetParamEx(Code,Param1,0);
    }

private static native int NativeGetProject(int instance);

private static native int NativeGetNavigate(int instance);

private static native int NativeGetCreator(int instance);

private static native int NativeGetProjectTree(int instance);

private static native int NativeGetWindow(int instance);

private static native int NativeGetTerrain(int instance);

private static native int NativeGetLicenseManager(int instance);

private static native int NativeGetCoordServices(int instance);

private static native int NativeGetDateTime(int instance);

private static native int NativeGetVersion(int instance);

private static native int NativeGetApplication(int instance);

private static native int NativeGetCommand(int instance);

private static native int NativeGetAnalysis(int instance);

private static native boolean NativeGetIgnoreAccelerators(int instance);

private static native void NativeSetIgnoreAccelerators(int instance,boolean pVal);

private static native void NativeAttachEvent(int instance,String bstrEventName,Object dispFunc);

private static native void NativeDetachEvent(int instance,String bstrEventName,Object dispFunc);

private static native Object NativeGetOptionParam(int instance,String paramName);

private static native Object NativeGetParam(int instance,int Code);

private static native void NativeOpen(int instance,String ProjectURL);

private static native void NativeSetOptionParam(int instance,String paramName,Object paramVal);

private static native void NativeSetParam(int instance,int Code,Object Param);

private static native Object NativeSetParamEx(int instance,int Code,Object Param1,Object Param2);
};
