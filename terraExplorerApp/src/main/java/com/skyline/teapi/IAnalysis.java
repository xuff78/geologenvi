package com.skyline.teapi;

public final class IAnalysis extends TEIUnknownHandle {
    private IAnalysis(int handle)
    {
        super(handle);
    }    
    public static IAnalysis fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new IAnalysis(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("0E98B54E-8741-4E86-A1ED-5E902ED5C817");

    public IVolumeAnalysisInfo CalculateVolume(Object arrObjectID,double SampleInterval) throws ApiException
    {
        checkDisposed();
        IVolumeAnalysisInfo result = IVolumeAnalysisInfo.fromHandle(NativeCalculateVolume(this.getHandle(),arrObjectID,SampleInterval));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public I3DViewshed Create3DViewshed(IPosition ViewerPosition,double FieldOfViewX,double FieldOfViewY,double Distance,String ParentGroupID,String Description) throws ApiException
    {
        checkDisposed();
        I3DViewshed result = I3DViewshed.fromHandle(NativeCreate3DViewshed(this.getHandle(),ViewerPosition,FieldOfViewX,FieldOfViewY,Distance,ParentGroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public I3DViewshed Create3DViewshed(IPosition ViewerPosition,double FieldOfViewX,double FieldOfViewY,double Distance) throws ApiException
    {
        return Create3DViewshed(ViewerPosition,FieldOfViewX,FieldOfViewY,Distance,"","");
    }

    public I3DViewshed Create3DViewshed(IPosition ViewerPosition,double FieldOfViewX,double FieldOfViewY,double Distance,String ParentGroupID) throws ApiException
    {
        return Create3DViewshed(ViewerPosition,FieldOfViewX,FieldOfViewY,Distance,ParentGroupID,"");
    }

    public IContourMap CreateContourMap(double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY,int DisplayStyle,String PaletteID,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        IContourMap result = IContourMap.fromHandle(NativeCreateContourMap(this.getHandle(),UpperLeftX,UpperLeftY,LowerRightX,LowerRightY,DisplayStyle,PaletteID,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IContourMap CreateContourMap(double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY) throws ApiException
    {
        return CreateContourMap(UpperLeftX,UpperLeftY,LowerRightX,LowerRightY,ContourDisplayStyle.CDS_CONTOUR_STYLE_COLORS,"","","");
    }

    public IContourMap CreateContourMap(double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY,int DisplayStyle) throws ApiException
    {
        return CreateContourMap(UpperLeftX,UpperLeftY,LowerRightX,LowerRightY,DisplayStyle,"","","");
    }

    public IContourMap CreateContourMap(double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY,int DisplayStyle,String PaletteID) throws ApiException
    {
        return CreateContourMap(UpperLeftX,UpperLeftY,LowerRightX,LowerRightY,DisplayStyle,PaletteID,"","");
    }

    public IContourMap CreateContourMap(double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY,int DisplayStyle,String PaletteID,String GroupID) throws ApiException
    {
        return CreateContourMap(UpperLeftX,UpperLeftY,LowerRightX,LowerRightY,DisplayStyle,PaletteID,GroupID,"");
    }

    public String CreateFloodContinuousWaterRise(double OriginX,double OriginY,double Radius,double WaterRiseRate,double SampleInterval,double TimeSampleIntervalHours,Object StartTime,Object EndTime,String ParentGroupID,String Description) throws ApiException
    {
        checkDisposed();
        String result = (NativeCreateFloodContinuousWaterRise(this.getHandle(),OriginX,OriginY,Radius,WaterRiseRate,SampleInterval,TimeSampleIntervalHours,StartTime,EndTime,ParentGroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String CreateFloodContinuousWaterRise(double OriginX,double OriginY,double Radius,double WaterRiseRate,double SampleInterval,double TimeSampleIntervalHours,Object StartTime,Object EndTime) throws ApiException
    {
        return CreateFloodContinuousWaterRise(OriginX,OriginY,Radius,WaterRiseRate,SampleInterval,TimeSampleIntervalHours,StartTime,EndTime,"","");
    }

    public String CreateFloodContinuousWaterRise(double OriginX,double OriginY,double Radius,double WaterRiseRate,double SampleInterval,double TimeSampleIntervalHours,Object StartTime,Object EndTime,String ParentGroupID) throws ApiException
    {
        return CreateFloodContinuousWaterRise(OriginX,OriginY,Radius,WaterRiseRate,SampleInterval,TimeSampleIntervalHours,StartTime,EndTime,ParentGroupID,"");
    }

    public String CreateFloodSingleWaterRise(double OriginX,double OriginY,double Radius,double TotalWaterLevelRaise,double SampleInterval,String ParentGroupID,String Description) throws ApiException
    {
        checkDisposed();
        String result = (NativeCreateFloodSingleWaterRise(this.getHandle(),OriginX,OriginY,Radius,TotalWaterLevelRaise,SampleInterval,ParentGroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String CreateFloodSingleWaterRise(double OriginX,double OriginY,double Radius,double TotalWaterLevelRaise,double SampleInterval) throws ApiException
    {
        return CreateFloodSingleWaterRise(OriginX,OriginY,Radius,TotalWaterLevelRaise,SampleInterval,"","");
    }

    public String CreateFloodSingleWaterRise(double OriginX,double OriginY,double Radius,double TotalWaterLevelRaise,double SampleInterval,String ParentGroupID) throws ApiException
    {
        return CreateFloodSingleWaterRise(OriginX,OriginY,Radius,TotalWaterLevelRaise,SampleInterval,ParentGroupID,"");
    }

    public ILineOfSight CreateLineOfSight(IPosition ViewerPosition,double SampleInterval,Object arrTargetPosition,String ParentGroupID,String Description) throws ApiException
    {
        checkDisposed();
        ILineOfSight result = ILineOfSight.fromHandle(NativeCreateLineOfSight(this.getHandle(),ViewerPosition,SampleInterval,arrTargetPosition,ParentGroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ILineOfSight CreateLineOfSight(IPosition ViewerPosition,double SampleInterval,Object arrTargetPosition) throws ApiException
    {
        return CreateLineOfSight(ViewerPosition,SampleInterval,arrTargetPosition,"","");
    }

    public ILineOfSight CreateLineOfSight(IPosition ViewerPosition,double SampleInterval,Object arrTargetPosition,String ParentGroupID) throws ApiException
    {
        return CreateLineOfSight(ViewerPosition,SampleInterval,arrTargetPosition,ParentGroupID,"");
    }

    public ISlopeMap CreateSlopeMap(double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY,int DisplayStyle,String PaletteID,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ISlopeMap result = ISlopeMap.fromHandle(NativeCreateSlopeMap(this.getHandle(),UpperLeftX,UpperLeftY,LowerRightX,LowerRightY,DisplayStyle,PaletteID,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ISlopeMap CreateSlopeMap(double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY) throws ApiException
    {
        return CreateSlopeMap(UpperLeftX,UpperLeftY,LowerRightX,LowerRightY,SlopeDisplayStyle.SDS_SLOPE_STYLE_COLORS,"","","");
    }

    public ISlopeMap CreateSlopeMap(double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY,int DisplayStyle) throws ApiException
    {
        return CreateSlopeMap(UpperLeftX,UpperLeftY,LowerRightX,LowerRightY,DisplayStyle,"","","");
    }

    public ISlopeMap CreateSlopeMap(double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY,int DisplayStyle,String PaletteID) throws ApiException
    {
        return CreateSlopeMap(UpperLeftX,UpperLeftY,LowerRightX,LowerRightY,DisplayStyle,PaletteID,"","");
    }

    public ISlopeMap CreateSlopeMap(double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY,int DisplayStyle,String PaletteID,String GroupID) throws ApiException
    {
        return CreateSlopeMap(UpperLeftX,UpperLeftY,LowerRightX,LowerRightY,DisplayStyle,PaletteID,GroupID,"");
    }

    public void CreateTerrainProfile(Object arrPoints) throws ApiException
    {
        checkDisposed();
        NativeCreateTerrainProfile(this.getHandle(),arrPoints);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public ITerrainThreatDome CreateThreatDome(IPosition Position,double Range,double AngularStep,double RadialStep,double Direction,double HorizontalFOV,double ElevationAngle,Object Color,String ParentGroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainThreatDome result = ITerrainThreatDome.fromHandle(NativeCreateThreatDome(this.getHandle(),Position,Range,AngularStep,RadialStep,Direction,HorizontalFOV,ElevationAngle,Color,ParentGroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainThreatDome CreateThreatDome(IPosition Position,double Range,double AngularStep,double RadialStep,double Direction,double HorizontalFOV,double ElevationAngle) throws ApiException
    {
        return CreateThreatDome(Position,Range,AngularStep,RadialStep,Direction,HorizontalFOV,ElevationAngle,-16711936,"","");
    }

    public ITerrainThreatDome CreateThreatDome(IPosition Position,double Range,double AngularStep,double RadialStep,double Direction,double HorizontalFOV,double ElevationAngle,Object Color) throws ApiException
    {
        return CreateThreatDome(Position,Range,AngularStep,RadialStep,Direction,HorizontalFOV,ElevationAngle,Color,"","");
    }

    public ITerrainThreatDome CreateThreatDome(IPosition Position,double Range,double AngularStep,double RadialStep,double Direction,double HorizontalFOV,double ElevationAngle,Object Color,String ParentGroupID) throws ApiException
    {
        return CreateThreatDome(Position,Range,AngularStep,RadialStep,Direction,HorizontalFOV,ElevationAngle,Color,ParentGroupID,"");
    }

    public String CreateViewshed(IPosition ViewerPosition,double FieldOfView,double SampleInterval,double RaySpacing,double TargetHeightAboveGround,Object TimeStart,Object TimeEnd,String ParentGroupID,String Description) throws ApiException
    {
        checkDisposed();
        String result = (NativeCreateViewshed(this.getHandle(),ViewerPosition,FieldOfView,SampleInterval,RaySpacing,TargetHeightAboveGround,TimeStart,TimeEnd,ParentGroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String CreateViewshed(IPosition ViewerPosition,double FieldOfView,double SampleInterval,double RaySpacing,double TargetHeightAboveGround,Object TimeStart,Object TimeEnd) throws ApiException
    {
        return CreateViewshed(ViewerPosition,FieldOfView,SampleInterval,RaySpacing,TargetHeightAboveGround,TimeStart,TimeEnd,"","");
    }

    public String CreateViewshed(IPosition ViewerPosition,double FieldOfView,double SampleInterval,double RaySpacing,double TargetHeightAboveGround,Object TimeStart,Object TimeEnd,String ParentGroupID) throws ApiException
    {
        return CreateViewshed(ViewerPosition,FieldOfView,SampleInterval,RaySpacing,TargetHeightAboveGround,TimeStart,TimeEnd,ParentGroupID,"");
    }

    public String CreateViewshedOnRoute(ILineString Route,int AnalysisType,double ViewerHeight,double Distance,double SampleInterval,double RaySpacing,double DistanceBetweenWaypoints,Object TimeStart,Object TimeEnd,String ParentGroupID,String Description) throws ApiException
    {
        checkDisposed();
        String result = (NativeCreateViewshedOnRoute(this.getHandle(),Route,AnalysisType,ViewerHeight,Distance,SampleInterval,RaySpacing,DistanceBetweenWaypoints,TimeStart,TimeEnd,ParentGroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public String CreateViewshedOnRoute(ILineString Route,int AnalysisType,double ViewerHeight,double Distance,double SampleInterval,double RaySpacing,double DistanceBetweenWaypoints,Object TimeStart,Object TimeEnd) throws ApiException
    {
        return CreateViewshedOnRoute(Route,AnalysisType,ViewerHeight,Distance,SampleInterval,RaySpacing,DistanceBetweenWaypoints,TimeStart,TimeEnd,"","");
    }

    public String CreateViewshedOnRoute(ILineString Route,int AnalysisType,double ViewerHeight,double Distance,double SampleInterval,double RaySpacing,double DistanceBetweenWaypoints,Object TimeStart,Object TimeEnd,String ParentGroupID) throws ApiException
    {
        return CreateViewshedOnRoute(Route,AnalysisType,ViewerHeight,Distance,SampleInterval,RaySpacing,DistanceBetweenWaypoints,TimeStart,TimeEnd,ParentGroupID,"");
    }

    public void EndVisibilityQuery() throws ApiException
    {
        checkDisposed();
        NativeEndVisibilityQuery(this.getHandle());
        TEErrorHelper.ThrowExceptionOnError();
    }

    public double MeasureTerrainArea(IGeometry pIGeometry) throws ApiException
    {
        checkDisposed();
        double result = (NativeMeasureTerrainArea(this.getHandle(),pIGeometry));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double MeasureTerrainGroundDistance(IGeometry pIGeometry,double SampleInterval,boolean IncludeGroundObjects) throws ApiException
    {
        checkDisposed();
        double result = (NativeMeasureTerrainGroundDistance(this.getHandle(),pIGeometry,SampleInterval,IncludeGroundObjects));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double MeasureTerrainGroundDistance(IGeometry pIGeometry,double SampleInterval) throws ApiException
    {
        return MeasureTerrainGroundDistance(pIGeometry,SampleInterval,false);
    }

    public double MeasureTerrainPerimeter(IGeometry pIGeometry) throws ApiException
    {
        checkDisposed();
        double result = (NativeMeasureTerrainPerimeter(this.getHandle(),pIGeometry));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IGeometry MeasureTerrainProfile(IGeometry KeyPoints,double SampleInterval,boolean IncludeGroundObjects) throws ApiException
    {
        checkDisposed();
        IGeometry result = IGeometry.fromHandle(NativeMeasureTerrainProfile(this.getHandle(),KeyPoints,SampleInterval,IncludeGroundObjects));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IGeometry MeasureTerrainProfile(IGeometry KeyPoints,double SampleInterval) throws ApiException
    {
        return MeasureTerrainProfile(KeyPoints,SampleInterval,false);
    }

    public double MeasureTerrainSurface(IGeometry pIGeometry,double SampleInterval) throws ApiException
    {
        checkDisposed();
        double result = (NativeMeasureTerrainSurface(this.getHandle(),pIGeometry,SampleInterval));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double MeasureTerrainSurfacePerimeter(IGeometry pIGeometry,double SampleInterval) throws ApiException
    {
        checkDisposed();
        double result = (NativeMeasureTerrainSurfacePerimeter(this.getHandle(),pIGeometry,SampleInterval));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public Object QueryElevationBuffer(double UpperLeftX,double UpperLeftY,double ResX,double ResY,int DimensionX,int DimensionY) throws ApiException
    {
        checkDisposed();
        Object result = (NativeQueryElevationBuffer(this.getHandle(),UpperLeftX,UpperLeftY,ResX,ResY,DimensionX,DimensionY));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double QueryPointVisibility(IPosition QueryPosition) throws ApiException
    {
        checkDisposed();
        double result = (NativeQueryPointVisibility(this.getHandle(),QueryPosition));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public double QueryVisibilityDistance(IPosition QueryPosition) throws ApiException
    {
        checkDisposed();
        double result = (NativeQueryVisibilityDistance(this.getHandle(),QueryPosition));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public void StartShadowVisibilityQuery(IPosition QuerySpherePosition,double QuerySphereRadius,int Type) throws ApiException
    {
        checkDisposed();
        NativeStartShadowVisibilityQuery(this.getHandle(),QuerySpherePosition,QuerySphereRadius,Type);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void StartViewshedVisibilityQuery(String ViewshedID,int Quality) throws ApiException
    {
        checkDisposed();
        NativeStartViewshedVisibilityQuery(this.getHandle(),ViewshedID,Quality);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public void StartViewshedVisibilityQuery(String ViewshedID) throws ApiException
    {
        StartViewshedVisibilityQuery(ViewshedID,ViewshedQuality.VSQ_QUALITY_HIGH);
    }

private static native int NativeCalculateVolume(int instance,Object arrObjectID,double SampleInterval);

private static native int NativeCreate3DViewshed(int instance,IPosition ViewerPosition,double FieldOfViewX,double FieldOfViewY,double Distance,String ParentGroupID,String Description);

private static native int NativeCreateContourMap(int instance,double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY,int DisplayStyle,String PaletteID,String GroupID,String Description);

private static native String NativeCreateFloodContinuousWaterRise(int instance,double OriginX,double OriginY,double Radius,double WaterRiseRate,double SampleInterval,double TimeSampleIntervalHours,Object StartTime,Object EndTime,String ParentGroupID,String Description);

private static native String NativeCreateFloodSingleWaterRise(int instance,double OriginX,double OriginY,double Radius,double TotalWaterLevelRaise,double SampleInterval,String ParentGroupID,String Description);

private static native int NativeCreateLineOfSight(int instance,IPosition ViewerPosition,double SampleInterval,Object arrTargetPosition,String ParentGroupID,String Description);

private static native int NativeCreateSlopeMap(int instance,double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY,int DisplayStyle,String PaletteID,String GroupID,String Description);

private static native void NativeCreateTerrainProfile(int instance,Object arrPoints);

private static native int NativeCreateThreatDome(int instance,IPosition Position,double Range,double AngularStep,double RadialStep,double Direction,double HorizontalFOV,double ElevationAngle,Object Color,String ParentGroupID,String Description);

private static native String NativeCreateViewshed(int instance,IPosition ViewerPosition,double FieldOfView,double SampleInterval,double RaySpacing,double TargetHeightAboveGround,Object TimeStart,Object TimeEnd,String ParentGroupID,String Description);

private static native String NativeCreateViewshedOnRoute(int instance,ILineString Route,int AnalysisType,double ViewerHeight,double Distance,double SampleInterval,double RaySpacing,double DistanceBetweenWaypoints,Object TimeStart,Object TimeEnd,String ParentGroupID,String Description);

private static native void NativeEndVisibilityQuery(int instance);

private static native double NativeMeasureTerrainArea(int instance,IGeometry pIGeometry);

private static native double NativeMeasureTerrainGroundDistance(int instance,IGeometry pIGeometry,double SampleInterval,boolean IncludeGroundObjects);

private static native double NativeMeasureTerrainPerimeter(int instance,IGeometry pIGeometry);

private static native int NativeMeasureTerrainProfile(int instance,IGeometry KeyPoints,double SampleInterval,boolean IncludeGroundObjects);

private static native double NativeMeasureTerrainSurface(int instance,IGeometry pIGeometry,double SampleInterval);

private static native double NativeMeasureTerrainSurfacePerimeter(int instance,IGeometry pIGeometry,double SampleInterval);

private static native Object NativeQueryElevationBuffer(int instance,double UpperLeftX,double UpperLeftY,double ResX,double ResY,int DimensionX,int DimensionY);

private static native double NativeQueryPointVisibility(int instance,IPosition QueryPosition);

private static native double NativeQueryVisibilityDistance(int instance,IPosition QueryPosition);

private static native void NativeStartShadowVisibilityQuery(int instance,IPosition QuerySpherePosition,double QuerySphereRadius,int Type);

private static native void NativeStartViewshedVisibilityQuery(int instance,String ViewshedID,int Quality);
};
