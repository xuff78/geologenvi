package com.skyline.teapi;

public final class IPresentation extends TEIUnknownHandle {
    private IPresentation(int handle)
    {
        super(handle);
    }    
    public static IPresentation fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IPresentation(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("775FDF4A-7296-458F-A280-16F97C93ACBF");

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

    public int getPlayAlgorithm() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetPlayAlgorithm(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setPlayAlgorithm(int value) throws ApiException
    {
        checkDisposed();
        NativeSetPlayAlgorithm(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getPlayMode() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetPlayMode(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setPlayMode(int value) throws ApiException
    {
        checkDisposed();
        NativeSetPlayMode(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getCaptionSizeType() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetCaptionSizeType(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setCaptionSizeType(int value) throws ApiException
    {
        checkDisposed();
        NativeSetCaptionSizeType(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getCaptionPosition() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetCaptionPosition(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setCaptionPosition(int value) throws ApiException
    {
        checkDisposed();
        NativeSetCaptionPosition(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getPresentationStatus() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetPresentationStatus(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public int getPlaySpeedFactor() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetPlaySpeedFactor(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setPlaySpeedFactor(int value) throws ApiException
    {
        checkDisposed();
        NativeSetPlaySpeedFactor(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public boolean getLoopRoute() throws ApiException
    {
        checkDisposed();
        boolean result = (NativeGetLoopRoute(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setLoopRoute(boolean value) throws ApiException
    {
        checkDisposed();
        NativeSetLoopRoute(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getCaptionWidth() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetCaptionWidth(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setCaptionWidth(int value) throws ApiException
    {
        checkDisposed();
        NativeSetCaptionWidth(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public int getCaptionHeight() throws ApiException
    {
        checkDisposed();
        int result = (NativeGetCaptionHeight(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void setCaptionHeight(int value) throws ApiException
    {
        checkDisposed();
        NativeSetCaptionHeight(this.getHandle(),value);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IPresentationSteps getSteps() throws ApiException
    {
        checkDisposed();
        IPresentationSteps result = IPresentationSteps.fromHandle(NativeGetSteps(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IAviWriter getAviWriter() throws ApiException
    {
        checkDisposed();
        IAviWriter result = IAviWriter.fromHandle(NativeGetAviWriter(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void Continue() throws ApiException
    {
        checkDisposed();
        NativeContinue(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public IPresentationStep CreateCaptionStep(int AdvancedType,int WaitTime,String Description,String CaptionText,int CaptionTimeout) throws ApiException
    {
        checkDisposed();
        IPresentationStep result = IPresentationStep.fromHandle(NativeCreateCaptionStep(this.getHandle(),AdvancedType,WaitTime,Description,CaptionText,CaptionTimeout));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPresentationStep CreateClearCaptionStep(int AdvancedType,int WaitTime,String Description) throws ApiException
    {
        checkDisposed();
        IPresentationStep result = IPresentationStep.fromHandle(NativeCreateClearCaptionStep(this.getHandle(),AdvancedType,WaitTime,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPresentationStep CreateFlightSpeedFactorStep(int AdvancedType,int WaitTime,String Description,int FlightSpeedFactor) throws ApiException
    {
        checkDisposed();
        IPresentationStep result = IPresentationStep.fromHandle(NativeCreateFlightSpeedFactorStep(this.getHandle(),AdvancedType,WaitTime,Description,FlightSpeedFactor));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPresentationStep CreateFollowDynamicObjectStep(int AdvancedType,int WaitTime,String Description,String ObjectID) throws ApiException
    {
        checkDisposed();
        IPresentationStep result = IPresentationStep.fromHandle(NativeCreateFollowDynamicObjectStep(this.getHandle(),AdvancedType,WaitTime,Description,ObjectID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPresentationStep CreateGroupTimeRangeStep(int AdvancedType,int WaitTime,String Description,String GroupID) throws ApiException
    {
        checkDisposed();
        IPresentationStep result = IPresentationStep.fromHandle(NativeCreateGroupTimeRangeStep(this.getHandle(),AdvancedType,WaitTime,Description,GroupID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPresentationStep CreateLocationStep(int AdvancedType,int WaitTime,String Description,Object Position) throws ApiException
    {
        checkDisposed();
        IPresentationStep result = IPresentationStep.fromHandle(NativeCreateLocationStep(this.getHandle(),AdvancedType,WaitTime,Description,Position));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPresentationStep CreateMessageStep(int AdvancedType,int WaitTime,String Description,String MessageID) throws ApiException
    {
        checkDisposed();
        IPresentationStep result = IPresentationStep.fromHandle(NativeCreateMessageStep(this.getHandle(),AdvancedType,WaitTime,Description,MessageID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPresentationStep CreateOpenToolStep(int AdvancedType,int WaitTime,String Description,int tool) throws ApiException
    {
        checkDisposed();
        IPresentationStep result = IPresentationStep.fromHandle(NativeCreateOpenToolStep(this.getHandle(),AdvancedType,WaitTime,Description,tool));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPresentationStep CreatePlayTimeAnimationCustomStep(int AdvancedType,int WaitTime,String Description,int AnimationLength,Object StartTime,Object EndTime) throws ApiException
    {
        checkDisposed();
        IPresentationStep result = IPresentationStep.fromHandle(NativeCreatePlayTimeAnimationCustomStep(this.getHandle(),AdvancedType,WaitTime,Description,AnimationLength,StartTime,EndTime));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPresentationStep CreatePlayTimeAnimationGroupStep(int AdvancedType,int WaitTime,String Description,int AnimationLength,String GroupID) throws ApiException
    {
        checkDisposed();
        IPresentationStep result = IPresentationStep.fromHandle(NativeCreatePlayTimeAnimationGroupStep(this.getHandle(),AdvancedType,WaitTime,Description,AnimationLength,GroupID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPresentationStep CreatePlayTimeAnimationProjectStep(int AdvancedType,int WaitTime,String Description,int AnimationLength) throws ApiException
    {
        checkDisposed();
        IPresentationStep result = IPresentationStep.fromHandle(NativeCreatePlayTimeAnimationProjectStep(this.getHandle(),AdvancedType,WaitTime,Description,AnimationLength));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPresentationStep CreateRestartDynamicObjectStep(int AdvancedType,int WaitTime,String Description,String ObjectID) throws ApiException
    {
        checkDisposed();
        IPresentationStep result = IPresentationStep.fromHandle(NativeCreateRestartDynamicObjectStep(this.getHandle(),AdvancedType,WaitTime,Description,ObjectID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPresentationStep CreateSetTimeStep(int AdvancedType,int WaitTime,String Description,Object Time) throws ApiException
    {
        checkDisposed();
        IPresentationStep result = IPresentationStep.fromHandle(NativeCreateSetTimeStep(this.getHandle(),AdvancedType,WaitTime,Description,Time));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPresentationStep CreateShowGroupStep(int AdvancedType,int WaitTime,String Description,String GroupID,boolean Show) throws ApiException
    {
        checkDisposed();
        IPresentationStep result = IPresentationStep.fromHandle(NativeCreateShowGroupStep(this.getHandle(),AdvancedType,WaitTime,Description,GroupID,Show));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPresentationStep CreateShowObjectStep(int AdvancedType,int WaitTime,String Description,String ObjectID,boolean Show) throws ApiException
    {
        checkDisposed();
        IPresentationStep result = IPresentationStep.fromHandle(NativeCreateShowObjectStep(this.getHandle(),AdvancedType,WaitTime,Description,ObjectID,Show));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPresentationStep CreateShowUndergroundModeStep(int AdvancedType,int WaitTime,String Description,boolean Show) throws ApiException
    {
        checkDisposed();
        IPresentationStep result = IPresentationStep.fromHandle(NativeCreateShowUndergroundModeStep(this.getHandle(),AdvancedType,WaitTime,Description,Show));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void DeleteStep(int Index) throws ApiException
    {
        checkDisposed();
        NativeDeleteStep(this.getHandle(),Index);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public Object GetParam(int Code) throws ApiException
    {
        checkDisposed();
        Object result = (NativeGetParam(this.getHandle(),Code));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void MoveStepTo(int fromIndex,int toIndex) throws ApiException
    {
        checkDisposed();
        NativeMoveStepTo(this.getHandle(),fromIndex,toIndex);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void NextStep() throws ApiException
    {
        checkDisposed();
        NativeNextStep(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void Pause() throws ApiException
    {
        checkDisposed();
        NativePause(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void Play(int startIndex) throws ApiException
    {
        checkDisposed();
        NativePlay(this.getHandle(),startIndex);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void PlayStep(int Index) throws ApiException
    {
        checkDisposed();
        NativePlayStep(this.getHandle(),Index);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void PreviousStep() throws ApiException
    {
        checkDisposed();
        NativePreviousStep(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void ResetPresentation() throws ApiException
    {
        checkDisposed();
        NativeResetPresentation(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void Resume() throws ApiException
    {
        checkDisposed();
        NativeResume(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void SetParam(int Code,Object Param) throws ApiException
    {
        checkDisposed();
        NativeSetParam(this.getHandle(),Code,Param);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void ShowEditor() throws ApiException
    {
        checkDisposed();
        NativeShowEditor(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void StartRecord() throws ApiException
    {
        checkDisposed();
        NativeStartRecord(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void Stop() throws ApiException
    {
        checkDisposed();
        NativeStop(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void StopRecord() throws ApiException
    {
        checkDisposed();
        NativeStopRecord(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

private static native String NativeGetID(int instance);

private static native int NativeGetObjectType(int instance);

private static native String NativeGetClientData(int instance,String Namespace);

private static native void NativeSetClientData(int instance,String Namespace,String pVal);

private static native boolean NativeGetSaveInFlyFile(int instance);

private static native void NativeSetSaveInFlyFile(int instance,boolean pVal);

private static native int NativeGetTreeItem(int instance);

private static native int NativeGetPlayAlgorithm(int instance);

private static native void NativeSetPlayAlgorithm(int instance,int pVal);

private static native int NativeGetPlayMode(int instance);

private static native void NativeSetPlayMode(int instance,int pVal);

private static native int NativeGetCaptionSizeType(int instance);

private static native void NativeSetCaptionSizeType(int instance,int pVal);

private static native int NativeGetCaptionPosition(int instance);

private static native void NativeSetCaptionPosition(int instance,int pVal);

private static native int NativeGetPresentationStatus(int instance);

private static native int NativeGetPlaySpeedFactor(int instance);

private static native void NativeSetPlaySpeedFactor(int instance,int pVal);

private static native boolean NativeGetLoopRoute(int instance);

private static native void NativeSetLoopRoute(int instance,boolean pVal);

private static native int NativeGetCaptionWidth(int instance);

private static native void NativeSetCaptionWidth(int instance,int pVal);

private static native int NativeGetCaptionHeight(int instance);

private static native void NativeSetCaptionHeight(int instance,int pVal);

private static native int NativeGetSteps(int instance);

private static native int NativeGetAviWriter(int instance);

private static native void NativeContinue(int instance);

private static native int NativeCreateCaptionStep(int instance,int AdvancedType,int WaitTime,String Description,String CaptionText,int CaptionTimeout);

private static native int NativeCreateClearCaptionStep(int instance,int AdvancedType,int WaitTime,String Description);

private static native int NativeCreateFlightSpeedFactorStep(int instance,int AdvancedType,int WaitTime,String Description,int FlightSpeedFactor);

private static native int NativeCreateFollowDynamicObjectStep(int instance,int AdvancedType,int WaitTime,String Description,String ObjectID);

private static native int NativeCreateGroupTimeRangeStep(int instance,int AdvancedType,int WaitTime,String Description,String GroupID);

private static native int NativeCreateLocationStep(int instance,int AdvancedType,int WaitTime,String Description,Object Position);

private static native int NativeCreateMessageStep(int instance,int AdvancedType,int WaitTime,String Description,String MessageID);

private static native int NativeCreateOpenToolStep(int instance,int AdvancedType,int WaitTime,String Description,int tool);

private static native int NativeCreatePlayTimeAnimationCustomStep(int instance,int AdvancedType,int WaitTime,String Description,int AnimationLength,Object StartTime,Object EndTime);

private static native int NativeCreatePlayTimeAnimationGroupStep(int instance,int AdvancedType,int WaitTime,String Description,int AnimationLength,String GroupID);

private static native int NativeCreatePlayTimeAnimationProjectStep(int instance,int AdvancedType,int WaitTime,String Description,int AnimationLength);

private static native int NativeCreateRestartDynamicObjectStep(int instance,int AdvancedType,int WaitTime,String Description,String ObjectID);

private static native int NativeCreateSetTimeStep(int instance,int AdvancedType,int WaitTime,String Description,Object Time);

private static native int NativeCreateShowGroupStep(int instance,int AdvancedType,int WaitTime,String Description,String GroupID,boolean Show);

private static native int NativeCreateShowObjectStep(int instance,int AdvancedType,int WaitTime,String Description,String ObjectID,boolean Show);

private static native int NativeCreateShowUndergroundModeStep(int instance,int AdvancedType,int WaitTime,String Description,boolean Show);

private static native void NativeDeleteStep(int instance,int Index);

private static native Object NativeGetParam(int instance,int Code);

private static native void NativeMoveStepTo(int instance,int fromIndex,int toIndex);

private static native void NativeNextStep(int instance);

private static native void NativePause(int instance);

private static native void NativePlay(int instance,int startIndex);

private static native void NativePlayStep(int instance,int Index);

private static native void NativePreviousStep(int instance);

private static native void NativeResetPresentation(int instance);

private static native void NativeResume(int instance);

private static native void NativeSetParam(int instance,int Code,Object Param);

private static native void NativeShowEditor(int instance);

private static native void NativeStartRecord(int instance);

private static native void NativeStop(int instance);

private static native void NativeStopRecord(int instance);
};
