package com.skyline.teapi;

public final class ICreator extends TEIUnknownHandle {
    private ICreator(int handle)
    {
        super(handle);
    }    
    public static ICreator fromHandle(int handle)
    {
        if(handle == 0)
            return null;
        return new ICreator(handle);
    }
    private final static java.util.UUID IID = java.util.UUID.fromString("29B49E40-4E81-4856-ABD2-C0AED3AFDC96");

    public IGeometryCreator getGeometryCreator() throws ApiException
    {
        checkDisposed();
        IGeometryCreator result = IGeometryCreator.fromHandle(NativeGetGeometryCreator(this.getHandle()));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrain3DArrow Create3DArrow(IPosition Position,double Length,int Style,double ObjectHeight,Object LineColor,Object FillColor,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrain3DArrow result = ITerrain3DArrow.fromHandle(NativeCreate3DArrow(this.getHandle(),Position,Length,Style,ObjectHeight,LineColor,FillColor,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrain3DArrow Create3DArrow(IPosition Position,double Length) throws ApiException
    {
        return Create3DArrow(Position,Length,4,10,-16711936,-10197916,"","");
    }

    public ITerrain3DArrow Create3DArrow(IPosition Position,double Length,int Style) throws ApiException
    {
        return Create3DArrow(Position,Length,Style,10,-16711936,-10197916,"","");
    }

    public ITerrain3DArrow Create3DArrow(IPosition Position,double Length,int Style,double ObjectHeight) throws ApiException
    {
        return Create3DArrow(Position,Length,Style,ObjectHeight,-16711936,-10197916,"","");
    }

    public ITerrain3DArrow Create3DArrow(IPosition Position,double Length,int Style,double ObjectHeight,Object LineColor) throws ApiException
    {
        return Create3DArrow(Position,Length,Style,ObjectHeight,LineColor,-10197916,"","");
    }

    public ITerrain3DArrow Create3DArrow(IPosition Position,double Length,int Style,double ObjectHeight,Object LineColor,Object FillColor) throws ApiException
    {
        return Create3DArrow(Position,Length,Style,ObjectHeight,LineColor,FillColor,"","");
    }

    public ITerrain3DArrow Create3DArrow(IPosition Position,double Length,int Style,double ObjectHeight,Object LineColor,Object FillColor,String GroupID) throws ApiException
    {
        return Create3DArrow(Position,Length,Style,ObjectHeight,LineColor,FillColor,GroupID,"");
    }

    public ITerrain3DPolygon Create3DPolygon(IGeometry pIGeometry,double ObjectHeight,Object LineColor,Object FillColor,int AltitudeType,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrain3DPolygon result = ITerrain3DPolygon.fromHandle(NativeCreate3DPolygon(this.getHandle(),pIGeometry,ObjectHeight,LineColor,FillColor,AltitudeType,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrain3DPolygon Create3DPolygon(IGeometry pIGeometry) throws ApiException
    {
        return Create3DPolygon(pIGeometry,20,-16711936,-10197916,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,"","");
    }

    public ITerrain3DPolygon Create3DPolygon(IGeometry pIGeometry,double ObjectHeight) throws ApiException
    {
        return Create3DPolygon(pIGeometry,ObjectHeight,-16711936,-10197916,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,"","");
    }

    public ITerrain3DPolygon Create3DPolygon(IGeometry pIGeometry,double ObjectHeight,Object LineColor) throws ApiException
    {
        return Create3DPolygon(pIGeometry,ObjectHeight,LineColor,-10197916,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,"","");
    }

    public ITerrain3DPolygon Create3DPolygon(IGeometry pIGeometry,double ObjectHeight,Object LineColor,Object FillColor) throws ApiException
    {
        return Create3DPolygon(pIGeometry,ObjectHeight,LineColor,FillColor,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,"","");
    }

    public ITerrain3DPolygon Create3DPolygon(IGeometry pIGeometry,double ObjectHeight,Object LineColor,Object FillColor,int AltitudeType) throws ApiException
    {
        return Create3DPolygon(pIGeometry,ObjectHeight,LineColor,FillColor,AltitudeType,"","");
    }

    public ITerrain3DPolygon Create3DPolygon(IGeometry pIGeometry,double ObjectHeight,Object LineColor,Object FillColor,int AltitudeType,String GroupID) throws ApiException
    {
        return Create3DPolygon(pIGeometry,ObjectHeight,LineColor,FillColor,AltitudeType,GroupID,"");
    }

    public ITerrainArc CreateArc(IPosition Position,double RadiusX,double RadiusY,double StartAngle,double EndAngle,Object LineColor,Object FillColor,int NumOfSegments,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainArc result = ITerrainArc.fromHandle(NativeCreateArc(this.getHandle(),Position,RadiusX,RadiusY,StartAngle,EndAngle,LineColor,FillColor,NumOfSegments,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainArc CreateArc(IPosition Position,double RadiusX,double RadiusY) throws ApiException
    {
        return CreateArc(Position,RadiusX,RadiusY,-90,90,-16711936,-10197916,-1,"","");
    }

    public ITerrainArc CreateArc(IPosition Position,double RadiusX,double RadiusY,double StartAngle) throws ApiException
    {
        return CreateArc(Position,RadiusX,RadiusY,StartAngle,90,-16711936,-10197916,-1,"","");
    }

    public ITerrainArc CreateArc(IPosition Position,double RadiusX,double RadiusY,double StartAngle,double EndAngle) throws ApiException
    {
        return CreateArc(Position,RadiusX,RadiusY,StartAngle,EndAngle,-16711936,-10197916,-1,"","");
    }

    public ITerrainArc CreateArc(IPosition Position,double RadiusX,double RadiusY,double StartAngle,double EndAngle,Object LineColor) throws ApiException
    {
        return CreateArc(Position,RadiusX,RadiusY,StartAngle,EndAngle,LineColor,-10197916,-1,"","");
    }

    public ITerrainArc CreateArc(IPosition Position,double RadiusX,double RadiusY,double StartAngle,double EndAngle,Object LineColor,Object FillColor) throws ApiException
    {
        return CreateArc(Position,RadiusX,RadiusY,StartAngle,EndAngle,LineColor,FillColor,-1,"","");
    }

    public ITerrainArc CreateArc(IPosition Position,double RadiusX,double RadiusY,double StartAngle,double EndAngle,Object LineColor,Object FillColor,int NumOfSegments) throws ApiException
    {
        return CreateArc(Position,RadiusX,RadiusY,StartAngle,EndAngle,LineColor,FillColor,NumOfSegments,"","");
    }

    public ITerrainArc CreateArc(IPosition Position,double RadiusX,double RadiusY,double StartAngle,double EndAngle,Object LineColor,Object FillColor,int NumOfSegments,String GroupID) throws ApiException
    {
        return CreateArc(Position,RadiusX,RadiusY,StartAngle,EndAngle,LineColor,FillColor,NumOfSegments,GroupID,"");
    }

    public ITerrainArrow CreateArrow(IPosition Position,double Length,int Style,Object LineColor,Object FillColor,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainArrow result = ITerrainArrow.fromHandle(NativeCreateArrow(this.getHandle(),Position,Length,Style,LineColor,FillColor,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainArrow CreateArrow(IPosition Position,double Length) throws ApiException
    {
        return CreateArrow(Position,Length,4,-16711936,-10197916,"","");
    }

    public ITerrainArrow CreateArrow(IPosition Position,double Length,int Style) throws ApiException
    {
        return CreateArrow(Position,Length,Style,-16711936,-10197916,"","");
    }

    public ITerrainArrow CreateArrow(IPosition Position,double Length,int Style,Object LineColor) throws ApiException
    {
        return CreateArrow(Position,Length,Style,LineColor,-10197916,"","");
    }

    public ITerrainArrow CreateArrow(IPosition Position,double Length,int Style,Object LineColor,Object FillColor) throws ApiException
    {
        return CreateArrow(Position,Length,Style,LineColor,FillColor,"","");
    }

    public ITerrainArrow CreateArrow(IPosition Position,double Length,int Style,Object LineColor,Object FillColor,String GroupID) throws ApiException
    {
        return CreateArrow(Position,Length,Style,LineColor,FillColor,GroupID,"");
    }

    public ITerrain3DRectBase CreateBox(IPosition Position,double ObjectWidth,double ObjectDepth,double ObjectHeight,Object LineColor,Object FillColor,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrain3DRectBase result = ITerrain3DRectBase.fromHandle(NativeCreateBox(this.getHandle(),Position,ObjectWidth,ObjectDepth,ObjectHeight,LineColor,FillColor,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrain3DRectBase CreateBox(IPosition Position,double ObjectWidth,double ObjectDepth,double ObjectHeight) throws ApiException
    {
        return CreateBox(Position,ObjectWidth,ObjectDepth,ObjectHeight,-16711936,-10197916,"","");
    }

    public ITerrain3DRectBase CreateBox(IPosition Position,double ObjectWidth,double ObjectDepth,double ObjectHeight,Object LineColor) throws ApiException
    {
        return CreateBox(Position,ObjectWidth,ObjectDepth,ObjectHeight,LineColor,-10197916,"","");
    }

    public ITerrain3DRectBase CreateBox(IPosition Position,double ObjectWidth,double ObjectDepth,double ObjectHeight,Object LineColor,Object FillColor) throws ApiException
    {
        return CreateBox(Position,ObjectWidth,ObjectDepth,ObjectHeight,LineColor,FillColor,"","");
    }

    public ITerrain3DRectBase CreateBox(IPosition Position,double ObjectWidth,double ObjectDepth,double ObjectHeight,Object LineColor,Object FillColor,String GroupID) throws ApiException
    {
        return CreateBox(Position,ObjectWidth,ObjectDepth,ObjectHeight,LineColor,FillColor,GroupID,"");
    }

    public ITerrainBuilding CreateBuilding(IGeometry pIGeometry,double RoofHeight,int AltitudeType,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainBuilding result = ITerrainBuilding.fromHandle(NativeCreateBuilding(this.getHandle(),pIGeometry,RoofHeight,AltitudeType,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainBuilding CreateBuilding(IGeometry pIGeometry) throws ApiException
    {
        return CreateBuilding(pIGeometry,20,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,"","");
    }

    public ITerrainBuilding CreateBuilding(IGeometry pIGeometry,double RoofHeight) throws ApiException
    {
        return CreateBuilding(pIGeometry,RoofHeight,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,"","");
    }

    public ITerrainBuilding CreateBuilding(IGeometry pIGeometry,double RoofHeight,int AltitudeType) throws ApiException
    {
        return CreateBuilding(pIGeometry,RoofHeight,AltitudeType,"","");
    }

    public ITerrainBuilding CreateBuilding(IGeometry pIGeometry,double RoofHeight,int AltitudeType,String GroupID) throws ApiException
    {
        return CreateBuilding(pIGeometry,RoofHeight,AltitudeType,GroupID,"");
    }

    public ITerrainRegularPolygon CreateCircle(IPosition Position,double Radius,Object LineColor,Object FillColor,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainRegularPolygon result = ITerrainRegularPolygon.fromHandle(NativeCreateCircle(this.getHandle(),Position,Radius,LineColor,FillColor,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainRegularPolygon CreateCircle(IPosition Position,double Radius) throws ApiException
    {
        return CreateCircle(Position,Radius,-16711936,-10197916,"","");
    }

    public ITerrainRegularPolygon CreateCircle(IPosition Position,double Radius,Object LineColor) throws ApiException
    {
        return CreateCircle(Position,Radius,LineColor,-10197916,"","");
    }

    public ITerrainRegularPolygon CreateCircle(IPosition Position,double Radius,Object LineColor,Object FillColor) throws ApiException
    {
        return CreateCircle(Position,Radius,LineColor,FillColor,"","");
    }

    public ITerrainRegularPolygon CreateCircle(IPosition Position,double Radius,Object LineColor,Object FillColor,String GroupID) throws ApiException
    {
        return CreateCircle(Position,Radius,LineColor,FillColor,GroupID,"");
    }

    public IColor CreateColor(int Red,int Green,int Blue,int Alpha) throws ApiException
    {
        checkDisposed();
        IColor result = IColor.fromHandle(NativeCreateColor(this.getHandle(),Red,Green,Blue,Alpha));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IColor CreateColor() throws ApiException
    {
        return CreateColor(255,255,255,255);
    }

    public IColor CreateColor(int Red) throws ApiException
    {
        return CreateColor(Red,255,255,255);
    }

    public IColor CreateColor(int Red,int Green) throws ApiException
    {
        return CreateColor(Red,Green,255,255);
    }

    public IColor CreateColor(int Red,int Green,int Blue) throws ApiException
    {
        return CreateColor(Red,Green,Blue,255);
    }

    public ITerrain3DRegBase CreateCone(IPosition Position,double Radius,double ObjectHeight,Object LineColor,Object FillColor,int NumOfSegments,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrain3DRegBase result = ITerrain3DRegBase.fromHandle(NativeCreateCone(this.getHandle(),Position,Radius,ObjectHeight,LineColor,FillColor,NumOfSegments,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrain3DRegBase CreateCone(IPosition Position,double Radius) throws ApiException
    {
        return CreateCone(Position,Radius,20,-16711936,-10197916,-1,"","");
    }

    public ITerrain3DRegBase CreateCone(IPosition Position,double Radius,double ObjectHeight) throws ApiException
    {
        return CreateCone(Position,Radius,ObjectHeight,-16711936,-10197916,-1,"","");
    }

    public ITerrain3DRegBase CreateCone(IPosition Position,double Radius,double ObjectHeight,Object LineColor) throws ApiException
    {
        return CreateCone(Position,Radius,ObjectHeight,LineColor,-10197916,-1,"","");
    }

    public ITerrain3DRegBase CreateCone(IPosition Position,double Radius,double ObjectHeight,Object LineColor,Object FillColor) throws ApiException
    {
        return CreateCone(Position,Radius,ObjectHeight,LineColor,FillColor,-1,"","");
    }

    public ITerrain3DRegBase CreateCone(IPosition Position,double Radius,double ObjectHeight,Object LineColor,Object FillColor,int NumOfSegments) throws ApiException
    {
        return CreateCone(Position,Radius,ObjectHeight,LineColor,FillColor,NumOfSegments,"","");
    }

    public ITerrain3DRegBase CreateCone(IPosition Position,double Radius,double ObjectHeight,Object LineColor,Object FillColor,int NumOfSegments,String GroupID) throws ApiException
    {
        return CreateCone(Position,Radius,ObjectHeight,LineColor,FillColor,NumOfSegments,GroupID,"");
    }

    public ITerrain3DRegBase CreateCylinder(IPosition Position,double Radius,double ObjectHeight,Object LineColor,Object FillColor,int NumOfSegments,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrain3DRegBase result = ITerrain3DRegBase.fromHandle(NativeCreateCylinder(this.getHandle(),Position,Radius,ObjectHeight,LineColor,FillColor,NumOfSegments,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrain3DRegBase CreateCylinder(IPosition Position,double Radius) throws ApiException
    {
        return CreateCylinder(Position,Radius,20,-16711936,-10197916,-1,"","");
    }

    public ITerrain3DRegBase CreateCylinder(IPosition Position,double Radius,double ObjectHeight) throws ApiException
    {
        return CreateCylinder(Position,Radius,ObjectHeight,-16711936,-10197916,-1,"","");
    }

    public ITerrain3DRegBase CreateCylinder(IPosition Position,double Radius,double ObjectHeight,Object LineColor) throws ApiException
    {
        return CreateCylinder(Position,Radius,ObjectHeight,LineColor,-10197916,-1,"","");
    }

    public ITerrain3DRegBase CreateCylinder(IPosition Position,double Radius,double ObjectHeight,Object LineColor,Object FillColor) throws ApiException
    {
        return CreateCylinder(Position,Radius,ObjectHeight,LineColor,FillColor,-1,"","");
    }

    public ITerrain3DRegBase CreateCylinder(IPosition Position,double Radius,double ObjectHeight,Object LineColor,Object FillColor,int NumOfSegments) throws ApiException
    {
        return CreateCylinder(Position,Radius,ObjectHeight,LineColor,FillColor,NumOfSegments,"","");
    }

    public ITerrain3DRegBase CreateCylinder(IPosition Position,double Radius,double ObjectHeight,Object LineColor,Object FillColor,int NumOfSegments,String GroupID) throws ApiException
    {
        return CreateCylinder(Position,Radius,ObjectHeight,LineColor,FillColor,NumOfSegments,GroupID,"");
    }

    public ITerrainDynamicObject CreateDynamicObject(Object Waypoints,int MotionStyle,int ObjectType,String FileNameOrText,double ScaleFactor,int AltitudeType,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainDynamicObject result = ITerrainDynamicObject.fromHandle(NativeCreateDynamicObject(this.getHandle(),Waypoints,MotionStyle,ObjectType,FileNameOrText,ScaleFactor,AltitudeType,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainDynamicObject CreateDynamicObject() throws ApiException
    {
        return CreateDynamicObject(0,DynamicMotionStyle.MOTION_AIRPLANE,DynamicObjectType.DYNAMIC_VIRTUAL,"",1,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,"","");
    }

    public ITerrainDynamicObject CreateDynamicObject(Object Waypoints) throws ApiException
    {
        return CreateDynamicObject(Waypoints,DynamicMotionStyle.MOTION_AIRPLANE,DynamicObjectType.DYNAMIC_VIRTUAL,"",1,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,"","");
    }

    public ITerrainDynamicObject CreateDynamicObject(Object Waypoints,int MotionStyle) throws ApiException
    {
        return CreateDynamicObject(Waypoints,MotionStyle,DynamicObjectType.DYNAMIC_VIRTUAL,"",1,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,"","");
    }

    public ITerrainDynamicObject CreateDynamicObject(Object Waypoints,int MotionStyle,int ObjectType) throws ApiException
    {
        return CreateDynamicObject(Waypoints,MotionStyle,ObjectType,"",1,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,"","");
    }

    public ITerrainDynamicObject CreateDynamicObject(Object Waypoints,int MotionStyle,int ObjectType,String FileNameOrText) throws ApiException
    {
        return CreateDynamicObject(Waypoints,MotionStyle,ObjectType,FileNameOrText,1,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,"","");
    }

    public ITerrainDynamicObject CreateDynamicObject(Object Waypoints,int MotionStyle,int ObjectType,String FileNameOrText,double ScaleFactor) throws ApiException
    {
        return CreateDynamicObject(Waypoints,MotionStyle,ObjectType,FileNameOrText,ScaleFactor,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,"","");
    }

    public ITerrainDynamicObject CreateDynamicObject(Object Waypoints,int MotionStyle,int ObjectType,String FileNameOrText,double ScaleFactor,int AltitudeType) throws ApiException
    {
        return CreateDynamicObject(Waypoints,MotionStyle,ObjectType,FileNameOrText,ScaleFactor,AltitudeType,"","");
    }

    public ITerrainDynamicObject CreateDynamicObject(Object Waypoints,int MotionStyle,int ObjectType,String FileNameOrText,double ScaleFactor,int AltitudeType,String GroupID) throws ApiException
    {
        return CreateDynamicObject(Waypoints,MotionStyle,ObjectType,FileNameOrText,ScaleFactor,AltitudeType,GroupID,"");
    }

    public ITerrainEffect CreateEffect(IPosition Position,String EffectsXML,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainEffect result = ITerrainEffect.fromHandle(NativeCreateEffect(this.getHandle(),Position,EffectsXML,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainEffect CreateEffect(IPosition Position) throws ApiException
    {
        return CreateEffect(Position,"","","");
    }

    public ITerrainEffect CreateEffect(IPosition Position,String EffectsXML) throws ApiException
    {
        return CreateEffect(Position,EffectsXML,"","");
    }

    public ITerrainEffect CreateEffect(IPosition Position,String EffectsXML,String GroupID) throws ApiException
    {
        return CreateEffect(Position,EffectsXML,GroupID,"");
    }

    public ITerrainRasterLayer CreateElevationLayer(String ElevationFileName,double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY,Object InitParam,Object PlugName,String GroupID,String Description,double HScale,double HOffset) throws ApiException
    {
        checkDisposed();
        ITerrainRasterLayer result = ITerrainRasterLayer.fromHandle(NativeCreateElevationLayer(this.getHandle(),ElevationFileName,UpperLeftX,UpperLeftY,LowerRightX,LowerRightY,InitParam,PlugName,GroupID,Description,HScale,HOffset));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainRasterLayer CreateElevationLayer(String ElevationFileName,double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY) throws ApiException
    {
        return CreateElevationLayer(ElevationFileName,UpperLeftX,UpperLeftY,LowerRightX,LowerRightY,0,"","","",1,0);
    }

    public ITerrainRasterLayer CreateElevationLayer(String ElevationFileName,double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY,Object InitParam) throws ApiException
    {
        return CreateElevationLayer(ElevationFileName,UpperLeftX,UpperLeftY,LowerRightX,LowerRightY,InitParam,"","","",1,0);
    }

    public ITerrainRasterLayer CreateElevationLayer(String ElevationFileName,double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY,Object InitParam,Object PlugName) throws ApiException
    {
        return CreateElevationLayer(ElevationFileName,UpperLeftX,UpperLeftY,LowerRightX,LowerRightY,InitParam,PlugName,"","",1,0);
    }

    public ITerrainRasterLayer CreateElevationLayer(String ElevationFileName,double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY,Object InitParam,Object PlugName,String GroupID) throws ApiException
    {
        return CreateElevationLayer(ElevationFileName,UpperLeftX,UpperLeftY,LowerRightX,LowerRightY,InitParam,PlugName,GroupID,"",1,0);
    }

    public ITerrainRasterLayer CreateElevationLayer(String ElevationFileName,double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY,Object InitParam,Object PlugName,String GroupID,String Description) throws ApiException
    {
        return CreateElevationLayer(ElevationFileName,UpperLeftX,UpperLeftY,LowerRightX,LowerRightY,InitParam,PlugName,GroupID,Description,1,0);
    }

    public ITerrainRasterLayer CreateElevationLayer(String ElevationFileName,double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY,Object InitParam,Object PlugName,String GroupID,String Description,double HScale) throws ApiException
    {
        return CreateElevationLayer(ElevationFileName,UpperLeftX,UpperLeftY,LowerRightX,LowerRightY,InitParam,PlugName,GroupID,Description,HScale,0);
    }

    public ITerrainEllipse CreateEllipse(IPosition Position,double RadiusX,double RadiusY,Object LineColor,Object FillColor,int NumOfSegments,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainEllipse result = ITerrainEllipse.fromHandle(NativeCreateEllipse(this.getHandle(),Position,RadiusX,RadiusY,LineColor,FillColor,NumOfSegments,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainEllipse CreateEllipse(IPosition Position,double RadiusX,double RadiusY) throws ApiException
    {
        return CreateEllipse(Position,RadiusX,RadiusY,-16711936,-10197916,-1,"","");
    }

    public ITerrainEllipse CreateEllipse(IPosition Position,double RadiusX,double RadiusY,Object LineColor) throws ApiException
    {
        return CreateEllipse(Position,RadiusX,RadiusY,LineColor,-10197916,-1,"","");
    }

    public ITerrainEllipse CreateEllipse(IPosition Position,double RadiusX,double RadiusY,Object LineColor,Object FillColor) throws ApiException
    {
        return CreateEllipse(Position,RadiusX,RadiusY,LineColor,FillColor,-1,"","");
    }

    public ITerrainEllipse CreateEllipse(IPosition Position,double RadiusX,double RadiusY,Object LineColor,Object FillColor,int NumOfSegments) throws ApiException
    {
        return CreateEllipse(Position,RadiusX,RadiusY,LineColor,FillColor,NumOfSegments,"","");
    }

    public ITerrainEllipse CreateEllipse(IPosition Position,double RadiusX,double RadiusY,Object LineColor,Object FillColor,int NumOfSegments,String GroupID) throws ApiException
    {
        return CreateEllipse(Position,RadiusX,RadiusY,LineColor,FillColor,NumOfSegments,GroupID,"");
    }

    public IFeatureLayer CreateFeatureLayer(String layerName,String sConnectionString,String GroupID) throws ApiException
    {
        checkDisposed();
        IFeatureLayer result = IFeatureLayer.fromHandle(NativeCreateFeatureLayer(this.getHandle(),layerName,sConnectionString,GroupID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IFeatureLayer CreateFeatureLayer(String layerName,String sConnectionString) throws ApiException
    {
        return CreateFeatureLayer(layerName,sConnectionString,"");
    }

    public ITerraExplorerObject CreateFromStream(Object Stream,String GroupID) throws ApiException
    {
        checkDisposed();
        ITerraExplorerObject result = ITerraExplorerObject.fromHandle(NativeCreateFromStream(this.getHandle(),Stream,GroupID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerraExplorerObject CreateFromStream(Object Stream) throws ApiException
    {
        return CreateFromStream(Stream,"");
    }

    public ITerrainHole CreateHoleOnTerrain(IGeometry pIGeometry,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainHole result = ITerrainHole.fromHandle(NativeCreateHoleOnTerrain(this.getHandle(),pIGeometry,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainHole CreateHoleOnTerrain(IGeometry pIGeometry) throws ApiException
    {
        return CreateHoleOnTerrain(pIGeometry,"","");
    }

    public ITerrainHole CreateHoleOnTerrain(IGeometry pIGeometry,String GroupID) throws ApiException
    {
        return CreateHoleOnTerrain(pIGeometry,GroupID,"");
    }

    public ITerrainImageLabel CreateImageLabel(IPosition Position,String ImageFileName,ILabelStyle LabelStyle,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainImageLabel result = ITerrainImageLabel.fromHandle(NativeCreateImageLabel(this.getHandle(),Position,ImageFileName,LabelStyle,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainImageLabel CreateImageLabel(IPosition Position,String ImageFileName) throws ApiException
    {
        return CreateImageLabel(Position,ImageFileName,null,"","");
    }

    public ITerrainImageLabel CreateImageLabel(IPosition Position,String ImageFileName,ILabelStyle LabelStyle) throws ApiException
    {
        return CreateImageLabel(Position,ImageFileName,LabelStyle,"","");
    }

    public ITerrainImageLabel CreateImageLabel(IPosition Position,String ImageFileName,ILabelStyle LabelStyle,String GroupID) throws ApiException
    {
        return CreateImageLabel(Position,ImageFileName,LabelStyle,GroupID,"");
    }

    public ITerrainRasterLayer CreateImageryLayer(String ImageryFileName,double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY,Object InitParam,Object PlugName,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainRasterLayer result = ITerrainRasterLayer.fromHandle(NativeCreateImageryLayer(this.getHandle(),ImageryFileName,UpperLeftX,UpperLeftY,LowerRightX,LowerRightY,InitParam,PlugName,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainRasterLayer CreateImageryLayer(String ImageryFileName,double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY) throws ApiException
    {
        return CreateImageryLayer(ImageryFileName,UpperLeftX,UpperLeftY,LowerRightX,LowerRightY,0,"","","");
    }

    public ITerrainRasterLayer CreateImageryLayer(String ImageryFileName,double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY,Object InitParam) throws ApiException
    {
        return CreateImageryLayer(ImageryFileName,UpperLeftX,UpperLeftY,LowerRightX,LowerRightY,InitParam,"","","");
    }

    public ITerrainRasterLayer CreateImageryLayer(String ImageryFileName,double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY,Object InitParam,Object PlugName) throws ApiException
    {
        return CreateImageryLayer(ImageryFileName,UpperLeftX,UpperLeftY,LowerRightX,LowerRightY,InitParam,PlugName,"","");
    }

    public ITerrainRasterLayer CreateImageryLayer(String ImageryFileName,double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY,Object InitParam,Object PlugName,String GroupID) throws ApiException
    {
        return CreateImageryLayer(ImageryFileName,UpperLeftX,UpperLeftY,LowerRightX,LowerRightY,InitParam,PlugName,GroupID,"");
    }

    public IKMLLayer CreateKMLLayer(String Path,String GroupID) throws ApiException
    {
        checkDisposed();
        IKMLLayer result = IKMLLayer.fromHandle(NativeCreateKMLLayer(this.getHandle(),Path,GroupID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IKMLLayer CreateKMLLayer(String Path) throws ApiException
    {
        return CreateKMLLayer(Path,"");
    }

    public ITerrainLabel CreateLabel(IPosition Position,String Text,String ImageFileName,ILabelStyle LabelStyle,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainLabel result = ITerrainLabel.fromHandle(NativeCreateLabel(this.getHandle(),Position,Text,ImageFileName,LabelStyle,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainLabel CreateLabel(IPosition Position,String Text,String ImageFileName) throws ApiException
    {
        return CreateLabel(Position,Text,ImageFileName,null,"","");
    }

    public ITerrainLabel CreateLabel(IPosition Position,String Text,String ImageFileName,ILabelStyle LabelStyle) throws ApiException
    {
        return CreateLabel(Position,Text,ImageFileName,LabelStyle,"","");
    }

    public ITerrainLabel CreateLabel(IPosition Position,String Text,String ImageFileName,ILabelStyle LabelStyle,String GroupID) throws ApiException
    {
        return CreateLabel(Position,Text,ImageFileName,LabelStyle,GroupID,"");
    }

    public ILabelStyle CreateLabelStyle(int ls) throws ApiException
    {
        checkDisposed();
        ILabelStyle result = ILabelStyle.fromHandle(NativeCreateLabelStyle(this.getHandle(),ls));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ILabelStyle CreateLabelStyle() throws ApiException
    {
        return CreateLabelStyle(SGLabelStyle.LS_DEFAULT);
    }

    public ITerrainLocation CreateLocation(IPosition Position,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainLocation result = ITerrainLocation.fromHandle(NativeCreateLocation(this.getHandle(),Position,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainLocation CreateLocation() throws ApiException
    {
        return CreateLocation(null,"","");
    }

    public ITerrainLocation CreateLocation(IPosition Position) throws ApiException
    {
        return CreateLocation(Position,"","");
    }

    public ITerrainLocation CreateLocation(IPosition Position,String GroupID) throws ApiException
    {
        return CreateLocation(Position,GroupID,"");
    }

    public ITerrainLocation CreateLocationHere(String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainLocation result = ITerrainLocation.fromHandle(NativeCreateLocationHere(this.getHandle(),GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainLocation CreateLocationHere() throws ApiException
    {
        return CreateLocationHere("","");
    }

    public ITerrainLocation CreateLocationHere(String GroupID) throws ApiException
    {
        return CreateLocationHere(GroupID,"");
    }

    public IMeshLayer CreateMeshLayerFromFile(String FilePath,String ParentGroupID) throws ApiException
    {
        checkDisposed();
        IMeshLayer result = IMeshLayer.fromHandle(NativeCreateMeshLayerFromFile(this.getHandle(),FilePath,ParentGroupID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IMeshLayer CreateMeshLayerFromFile(String FilePath) throws ApiException
    {
        return CreateMeshLayerFromFile(FilePath,"");
    }

    public IMeshLayer CreateMeshLayerFromSFS(String ServerPath,String layerName,String ParentGroupID) throws ApiException
    {
        checkDisposed();
        IMeshLayer result = IMeshLayer.fromHandle(NativeCreateMeshLayerFromSFS(this.getHandle(),ServerPath,layerName,ParentGroupID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IMeshLayer CreateMeshLayerFromSFS(String ServerPath,String layerName) throws ApiException
    {
        return CreateMeshLayerFromSFS(ServerPath,layerName,"");
    }

    public ITerraExplorerMessage CreateMessage(int TargetPosition,String msgData,int Type,boolean BringToFront) throws ApiException
    {
        checkDisposed();
        ITerraExplorerMessage result = ITerraExplorerMessage.fromHandle(NativeCreateMessage(this.getHandle(),TargetPosition,msgData,Type,BringToFront));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerraExplorerMessage CreateMessage(int TargetPosition,String msgData,int Type) throws ApiException
    {
        return CreateMessage(TargetPosition,msgData,Type,false);
    }

    public ITerrainModel CreateModel(IPosition Position,String FileName,double Scale,int ModelType,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainModel result = ITerrainModel.fromHandle(NativeCreateModel(this.getHandle(),Position,FileName,Scale,ModelType,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainModel CreateModel(IPosition Position,String FileName) throws ApiException
    {
        return CreateModel(Position,FileName,1,ModelTypeCode.MT_NORMAL,"","");
    }

    public ITerrainModel CreateModel(IPosition Position,String FileName,double Scale) throws ApiException
    {
        return CreateModel(Position,FileName,Scale,ModelTypeCode.MT_NORMAL,"","");
    }

    public ITerrainModel CreateModel(IPosition Position,String FileName,double Scale,int ModelType) throws ApiException
    {
        return CreateModel(Position,FileName,Scale,ModelType,"","");
    }

    public ITerrainModel CreateModel(IPosition Position,String FileName,double Scale,int ModelType,String GroupID) throws ApiException
    {
        return CreateModel(Position,FileName,Scale,ModelType,GroupID,"");
    }

    public IFeatureLayer CreateNewFeatureLayer(String layerName,int LayerGeomType,String sConnectionString,String GroupID) throws ApiException
    {
        checkDisposed();
        IFeatureLayer result = IFeatureLayer.fromHandle(NativeCreateNewFeatureLayer(this.getHandle(),layerName,LayerGeomType,sConnectionString,GroupID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IFeatureLayer CreateNewFeatureLayer(String layerName,int LayerGeomType,String sConnectionString) throws ApiException
    {
        return CreateNewFeatureLayer(layerName,LayerGeomType,sConnectionString,"");
    }

    public ITerrainPointCloudModel CreatePointCloudModel(String ModelFileName,IPosition Position,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainPointCloudModel result = ITerrainPointCloudModel.fromHandle(NativeCreatePointCloudModel(this.getHandle(),ModelFileName,Position,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainPointCloudModel CreatePointCloudModel(String ModelFileName,IPosition Position) throws ApiException
    {
        return CreatePointCloudModel(ModelFileName,Position,"","");
    }

    public ITerrainPointCloudModel CreatePointCloudModel(String ModelFileName,IPosition Position,String GroupID) throws ApiException
    {
        return CreatePointCloudModel(ModelFileName,Position,GroupID,"");
    }

    public ITerrainPolygon CreatePolygon(IGeometry pIGeometry,Object LineColor,Object FillColor,int AltitudeType,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainPolygon result = ITerrainPolygon.fromHandle(NativeCreatePolygon(this.getHandle(),pIGeometry,LineColor,FillColor,AltitudeType,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainPolygon CreatePolygon(IGeometry pIGeometry) throws ApiException
    {
        return CreatePolygon(pIGeometry,-16711936,-10197916,AltitudeTypeCode.ATC_ON_TERRAIN,"","");
    }

    public ITerrainPolygon CreatePolygon(IGeometry pIGeometry,Object LineColor) throws ApiException
    {
        return CreatePolygon(pIGeometry,LineColor,-10197916,AltitudeTypeCode.ATC_ON_TERRAIN,"","");
    }

    public ITerrainPolygon CreatePolygon(IGeometry pIGeometry,Object LineColor,Object FillColor) throws ApiException
    {
        return CreatePolygon(pIGeometry,LineColor,FillColor,AltitudeTypeCode.ATC_ON_TERRAIN,"","");
    }

    public ITerrainPolygon CreatePolygon(IGeometry pIGeometry,Object LineColor,Object FillColor,int AltitudeType) throws ApiException
    {
        return CreatePolygon(pIGeometry,LineColor,FillColor,AltitudeType,"","");
    }

    public ITerrainPolygon CreatePolygon(IGeometry pIGeometry,Object LineColor,Object FillColor,int AltitudeType,String GroupID) throws ApiException
    {
        return CreatePolygon(pIGeometry,LineColor,FillColor,AltitudeType,GroupID,"");
    }

    public ITerrainPolygon CreatePolygonFromArray(Object verticesArray,Object LineColor,Object FillColor,int AltitudeType,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainPolygon result = ITerrainPolygon.fromHandle(NativeCreatePolygonFromArray(this.getHandle(),verticesArray,LineColor,FillColor,AltitudeType,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainPolygon CreatePolygonFromArray(Object verticesArray) throws ApiException
    {
        return CreatePolygonFromArray(verticesArray,-16711936,-10197916,AltitudeTypeCode.ATC_ON_TERRAIN,"","");
    }

    public ITerrainPolygon CreatePolygonFromArray(Object verticesArray,Object LineColor) throws ApiException
    {
        return CreatePolygonFromArray(verticesArray,LineColor,-10197916,AltitudeTypeCode.ATC_ON_TERRAIN,"","");
    }

    public ITerrainPolygon CreatePolygonFromArray(Object verticesArray,Object LineColor,Object FillColor) throws ApiException
    {
        return CreatePolygonFromArray(verticesArray,LineColor,FillColor,AltitudeTypeCode.ATC_ON_TERRAIN,"","");
    }

    public ITerrainPolygon CreatePolygonFromArray(Object verticesArray,Object LineColor,Object FillColor,int AltitudeType) throws ApiException
    {
        return CreatePolygonFromArray(verticesArray,LineColor,FillColor,AltitudeType,"","");
    }

    public ITerrainPolygon CreatePolygonFromArray(Object verticesArray,Object LineColor,Object FillColor,int AltitudeType,String GroupID) throws ApiException
    {
        return CreatePolygonFromArray(verticesArray,LineColor,FillColor,AltitudeType,GroupID,"");
    }

    public ITerrainPolyline CreatePolyline(IGeometry pIGeometry,Object LineColor,int AltitudeType,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainPolyline result = ITerrainPolyline.fromHandle(NativeCreatePolyline(this.getHandle(),pIGeometry,LineColor,AltitudeType,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainPolyline CreatePolyline(IGeometry pIGeometry) throws ApiException
    {
        return CreatePolyline(pIGeometry,-16711936,AltitudeTypeCode.ATC_ON_TERRAIN,"","");
    }

    public ITerrainPolyline CreatePolyline(IGeometry pIGeometry,Object LineColor) throws ApiException
    {
        return CreatePolyline(pIGeometry,LineColor,AltitudeTypeCode.ATC_ON_TERRAIN,"","");
    }

    public ITerrainPolyline CreatePolyline(IGeometry pIGeometry,Object LineColor,int AltitudeType) throws ApiException
    {
        return CreatePolyline(pIGeometry,LineColor,AltitudeType,"","");
    }

    public ITerrainPolyline CreatePolyline(IGeometry pIGeometry,Object LineColor,int AltitudeType,String GroupID) throws ApiException
    {
        return CreatePolyline(pIGeometry,LineColor,AltitudeType,GroupID,"");
    }

    public ITerrainPolyline CreatePolylineFromArray(Object verticesArray,Object LineColor,int AltitudeType,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainPolyline result = ITerrainPolyline.fromHandle(NativeCreatePolylineFromArray(this.getHandle(),verticesArray,LineColor,AltitudeType,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainPolyline CreatePolylineFromArray(Object verticesArray) throws ApiException
    {
        return CreatePolylineFromArray(verticesArray,-16711936,AltitudeTypeCode.ATC_ON_TERRAIN,"","");
    }

    public ITerrainPolyline CreatePolylineFromArray(Object verticesArray,Object LineColor) throws ApiException
    {
        return CreatePolylineFromArray(verticesArray,LineColor,AltitudeTypeCode.ATC_ON_TERRAIN,"","");
    }

    public ITerrainPolyline CreatePolylineFromArray(Object verticesArray,Object LineColor,int AltitudeType) throws ApiException
    {
        return CreatePolylineFromArray(verticesArray,LineColor,AltitudeType,"","");
    }

    public ITerrainPolyline CreatePolylineFromArray(Object verticesArray,Object LineColor,int AltitudeType,String GroupID) throws ApiException
    {
        return CreatePolylineFromArray(verticesArray,LineColor,AltitudeType,GroupID,"");
    }

    public IPopupMessage CreatePopupMessage(String Caption,String Src,int Left,int Top,int Width,int Height,int Timeout) throws ApiException
    {
        checkDisposed();
        IPopupMessage result = IPopupMessage.fromHandle(NativeCreatePopupMessage(this.getHandle(),Caption,Src,Left,Top,Width,Height,Timeout));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPopupMessage CreatePopupMessage() throws ApiException
    {
        return CreatePopupMessage("","",0,0,0,0,-1);
    }

    public IPopupMessage CreatePopupMessage(String Caption) throws ApiException
    {
        return CreatePopupMessage(Caption,"",0,0,0,0,-1);
    }

    public IPopupMessage CreatePopupMessage(String Caption,String Src) throws ApiException
    {
        return CreatePopupMessage(Caption,Src,0,0,0,0,-1);
    }

    public IPopupMessage CreatePopupMessage(String Caption,String Src,int Left) throws ApiException
    {
        return CreatePopupMessage(Caption,Src,Left,0,0,0,-1);
    }

    public IPopupMessage CreatePopupMessage(String Caption,String Src,int Left,int Top) throws ApiException
    {
        return CreatePopupMessage(Caption,Src,Left,Top,0,0,-1);
    }

    public IPopupMessage CreatePopupMessage(String Caption,String Src,int Left,int Top,int Width) throws ApiException
    {
        return CreatePopupMessage(Caption,Src,Left,Top,Width,0,-1);
    }

    public IPopupMessage CreatePopupMessage(String Caption,String Src,int Left,int Top,int Width,int Height) throws ApiException
    {
        return CreatePopupMessage(Caption,Src,Left,Top,Width,Height,-1);
    }

    public IPosition CreatePosition(double X,double Y,double Altitude,int AltitudeType,double Yaw,double Pitch,double Roll,double Distance) throws ApiException
    {
        checkDisposed();
        IPosition result = IPosition.fromHandle(NativeCreatePosition(this.getHandle(),X,Y,Altitude,AltitudeType,Yaw,Pitch,Roll,Distance));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPosition CreatePosition() throws ApiException
    {
        return CreatePosition(0,0,0,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,0,0,0,0);
    }

    public IPosition CreatePosition(double X) throws ApiException
    {
        return CreatePosition(X,0,0,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,0,0,0,0);
    }

    public IPosition CreatePosition(double X,double Y) throws ApiException
    {
        return CreatePosition(X,Y,0,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,0,0,0,0);
    }

    public IPosition CreatePosition(double X,double Y,double Altitude) throws ApiException
    {
        return CreatePosition(X,Y,Altitude,AltitudeTypeCode.ATC_TERRAIN_RELATIVE,0,0,0,0);
    }

    public IPosition CreatePosition(double X,double Y,double Altitude,int AltitudeType) throws ApiException
    {
        return CreatePosition(X,Y,Altitude,AltitudeType,0,0,0,0);
    }

    public IPosition CreatePosition(double X,double Y,double Altitude,int AltitudeType,double Yaw) throws ApiException
    {
        return CreatePosition(X,Y,Altitude,AltitudeType,Yaw,0,0,0);
    }

    public IPosition CreatePosition(double X,double Y,double Altitude,int AltitudeType,double Yaw,double Pitch) throws ApiException
    {
        return CreatePosition(X,Y,Altitude,AltitudeType,Yaw,Pitch,0,0);
    }

    public IPosition CreatePosition(double X,double Y,double Altitude,int AltitudeType,double Yaw,double Pitch,double Roll) throws ApiException
    {
        return CreatePosition(X,Y,Altitude,AltitudeType,Yaw,Pitch,Roll,0);
    }

    public IPresentation CreatePresentation(String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        IPresentation result = IPresentation.fromHandle(NativeCreatePresentation(this.getHandle(),GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IPresentation CreatePresentation() throws ApiException
    {
        return CreatePresentation("","");
    }

    public IPresentation CreatePresentation(String GroupID) throws ApiException
    {
        return CreatePresentation(GroupID,"");
    }

    public ITerrain3DRectBase CreatePyramid(IPosition Position,double ObjectWidth,double ObjectDepth,double ObjectHeight,Object LineColor,Object FillColor,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrain3DRectBase result = ITerrain3DRectBase.fromHandle(NativeCreatePyramid(this.getHandle(),Position,ObjectWidth,ObjectDepth,ObjectHeight,LineColor,FillColor,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrain3DRectBase CreatePyramid(IPosition Position,double ObjectWidth,double ObjectDepth,double ObjectHeight) throws ApiException
    {
        return CreatePyramid(Position,ObjectWidth,ObjectDepth,ObjectHeight,-16711936,-10197916,"","");
    }

    public ITerrain3DRectBase CreatePyramid(IPosition Position,double ObjectWidth,double ObjectDepth,double ObjectHeight,Object LineColor) throws ApiException
    {
        return CreatePyramid(Position,ObjectWidth,ObjectDepth,ObjectHeight,LineColor,-10197916,"","");
    }

    public ITerrain3DRectBase CreatePyramid(IPosition Position,double ObjectWidth,double ObjectDepth,double ObjectHeight,Object LineColor,Object FillColor) throws ApiException
    {
        return CreatePyramid(Position,ObjectWidth,ObjectDepth,ObjectHeight,LineColor,FillColor,"","");
    }

    public ITerrain3DRectBase CreatePyramid(IPosition Position,double ObjectWidth,double ObjectDepth,double ObjectHeight,Object LineColor,Object FillColor,String GroupID) throws ApiException
    {
        return CreatePyramid(Position,ObjectWidth,ObjectDepth,ObjectHeight,LineColor,FillColor,GroupID,"");
    }

    public ITerrainRectangle CreateRectangle(IPosition Position,double ObjectWidth,double ObjectDepth,Object LineColor,Object FillColor,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainRectangle result = ITerrainRectangle.fromHandle(NativeCreateRectangle(this.getHandle(),Position,ObjectWidth,ObjectDepth,LineColor,FillColor,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainRectangle CreateRectangle(IPosition Position,double ObjectWidth,double ObjectDepth) throws ApiException
    {
        return CreateRectangle(Position,ObjectWidth,ObjectDepth,-16711936,-10197916,"","");
    }

    public ITerrainRectangle CreateRectangle(IPosition Position,double ObjectWidth,double ObjectDepth,Object LineColor) throws ApiException
    {
        return CreateRectangle(Position,ObjectWidth,ObjectDepth,LineColor,-10197916,"","");
    }

    public ITerrainRectangle CreateRectangle(IPosition Position,double ObjectWidth,double ObjectDepth,Object LineColor,Object FillColor) throws ApiException
    {
        return CreateRectangle(Position,ObjectWidth,ObjectDepth,LineColor,FillColor,"","");
    }

    public ITerrainRectangle CreateRectangle(IPosition Position,double ObjectWidth,double ObjectDepth,Object LineColor,Object FillColor,String GroupID) throws ApiException
    {
        return CreateRectangle(Position,ObjectWidth,ObjectDepth,LineColor,FillColor,GroupID,"");
    }

    public ITerrainRegularPolygon CreateRegularPolygon(IPosition Position,double Radius,int NumOfSegments,Object LineColor,Object FillColor,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainRegularPolygon result = ITerrainRegularPolygon.fromHandle(NativeCreateRegularPolygon(this.getHandle(),Position,Radius,NumOfSegments,LineColor,FillColor,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainRegularPolygon CreateRegularPolygon(IPosition Position,double Radius,int NumOfSegments) throws ApiException
    {
        return CreateRegularPolygon(Position,Radius,NumOfSegments,-16711936,-10197916,"","");
    }

    public ITerrainRegularPolygon CreateRegularPolygon(IPosition Position,double Radius,int NumOfSegments,Object LineColor) throws ApiException
    {
        return CreateRegularPolygon(Position,Radius,NumOfSegments,LineColor,-10197916,"","");
    }

    public ITerrainRegularPolygon CreateRegularPolygon(IPosition Position,double Radius,int NumOfSegments,Object LineColor,Object FillColor) throws ApiException
    {
        return CreateRegularPolygon(Position,Radius,NumOfSegments,LineColor,FillColor,"","");
    }

    public ITerrainRegularPolygon CreateRegularPolygon(IPosition Position,double Radius,int NumOfSegments,Object LineColor,Object FillColor,String GroupID) throws ApiException
    {
        return CreateRegularPolygon(Position,Radius,NumOfSegments,LineColor,FillColor,GroupID,"");
    }

    public IRouteWaypoint CreateRouteWaypoint(double X,double Y,double Altitude,double Speed,double Yaw,double Pitch,double Roll,double CameraDeltaYaw,double CameraDeltaPitch,String MessageID) throws ApiException
    {
        checkDisposed();
        IRouteWaypoint result = IRouteWaypoint.fromHandle(NativeCreateRouteWaypoint(this.getHandle(),X,Y,Altitude,Speed,Yaw,Pitch,Roll,CameraDeltaYaw,CameraDeltaPitch,MessageID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public IRouteWaypoint CreateRouteWaypoint() throws ApiException
    {
        return CreateRouteWaypoint(0,0,0,300,0,0,0,0,0,"");
    }

    public IRouteWaypoint CreateRouteWaypoint(double X) throws ApiException
    {
        return CreateRouteWaypoint(X,0,0,300,0,0,0,0,0,"");
    }

    public IRouteWaypoint CreateRouteWaypoint(double X,double Y) throws ApiException
    {
        return CreateRouteWaypoint(X,Y,0,300,0,0,0,0,0,"");
    }

    public IRouteWaypoint CreateRouteWaypoint(double X,double Y,double Altitude) throws ApiException
    {
        return CreateRouteWaypoint(X,Y,Altitude,300,0,0,0,0,0,"");
    }

    public IRouteWaypoint CreateRouteWaypoint(double X,double Y,double Altitude,double Speed) throws ApiException
    {
        return CreateRouteWaypoint(X,Y,Altitude,Speed,0,0,0,0,0,"");
    }

    public IRouteWaypoint CreateRouteWaypoint(double X,double Y,double Altitude,double Speed,double Yaw) throws ApiException
    {
        return CreateRouteWaypoint(X,Y,Altitude,Speed,Yaw,0,0,0,0,"");
    }

    public IRouteWaypoint CreateRouteWaypoint(double X,double Y,double Altitude,double Speed,double Yaw,double Pitch) throws ApiException
    {
        return CreateRouteWaypoint(X,Y,Altitude,Speed,Yaw,Pitch,0,0,0,"");
    }

    public IRouteWaypoint CreateRouteWaypoint(double X,double Y,double Altitude,double Speed,double Yaw,double Pitch,double Roll) throws ApiException
    {
        return CreateRouteWaypoint(X,Y,Altitude,Speed,Yaw,Pitch,Roll,0,0,"");
    }

    public IRouteWaypoint CreateRouteWaypoint(double X,double Y,double Altitude,double Speed,double Yaw,double Pitch,double Roll,double CameraDeltaYaw) throws ApiException
    {
        return CreateRouteWaypoint(X,Y,Altitude,Speed,Yaw,Pitch,Roll,CameraDeltaYaw,0,"");
    }

    public IRouteWaypoint CreateRouteWaypoint(double X,double Y,double Altitude,double Speed,double Yaw,double Pitch,double Roll,double CameraDeltaYaw,double CameraDeltaPitch) throws ApiException
    {
        return CreateRouteWaypoint(X,Y,Altitude,Speed,Yaw,Pitch,Roll,CameraDeltaYaw,CameraDeltaPitch,"");
    }

    public ITerrainSphere CreateSphere(IPosition Position,double Radius,int Style,Object LineColor,Object FillColor,int SegmentDensity,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainSphere result = ITerrainSphere.fromHandle(NativeCreateSphere(this.getHandle(),Position,Radius,Style,LineColor,FillColor,SegmentDensity,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainSphere CreateSphere(IPosition Position,double Radius) throws ApiException
    {
        return CreateSphere(Position,Radius,SphereStyle.SPHERE_NORMAL,-16711936,-10197916,-1,"","");
    }

    public ITerrainSphere CreateSphere(IPosition Position,double Radius,int Style) throws ApiException
    {
        return CreateSphere(Position,Radius,Style,-16711936,-10197916,-1,"","");
    }

    public ITerrainSphere CreateSphere(IPosition Position,double Radius,int Style,Object LineColor) throws ApiException
    {
        return CreateSphere(Position,Radius,Style,LineColor,-10197916,-1,"","");
    }

    public ITerrainSphere CreateSphere(IPosition Position,double Radius,int Style,Object LineColor,Object FillColor) throws ApiException
    {
        return CreateSphere(Position,Radius,Style,LineColor,FillColor,-1,"","");
    }

    public ITerrainSphere CreateSphere(IPosition Position,double Radius,int Style,Object LineColor,Object FillColor,int SegmentDensity) throws ApiException
    {
        return CreateSphere(Position,Radius,Style,LineColor,FillColor,SegmentDensity,"","");
    }

    public ITerrainSphere CreateSphere(IPosition Position,double Radius,int Style,Object LineColor,Object FillColor,int SegmentDensity,String GroupID) throws ApiException
    {
        return CreateSphere(Position,Radius,Style,LineColor,FillColor,SegmentDensity,GroupID,"");
    }

    public ITerrainModifier CreateTerrainModifier(IGeometry pIGeometry,int Mode,boolean Flat,double Feather,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainModifier result = ITerrainModifier.fromHandle(NativeCreateTerrainModifier(this.getHandle(),pIGeometry,Mode,Flat,Feather,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainModifier CreateTerrainModifier(IGeometry pIGeometry) throws ApiException
    {
        return CreateTerrainModifier(pIGeometry,ElevationBehaviorMode.EB_REPLACE,true,0,"","");
    }

    public ITerrainModifier CreateTerrainModifier(IGeometry pIGeometry,int Mode) throws ApiException
    {
        return CreateTerrainModifier(pIGeometry,Mode,true,0,"","");
    }

    public ITerrainModifier CreateTerrainModifier(IGeometry pIGeometry,int Mode,boolean Flat) throws ApiException
    {
        return CreateTerrainModifier(pIGeometry,Mode,Flat,0,"","");
    }

    public ITerrainModifier CreateTerrainModifier(IGeometry pIGeometry,int Mode,boolean Flat,double Feather) throws ApiException
    {
        return CreateTerrainModifier(pIGeometry,Mode,Flat,Feather,"","");
    }

    public ITerrainModifier CreateTerrainModifier(IGeometry pIGeometry,int Mode,boolean Flat,double Feather,String GroupID) throws ApiException
    {
        return CreateTerrainModifier(pIGeometry,Mode,Flat,Feather,GroupID,"");
    }

    public ITerrainLabel CreateTextLabel(IPosition Position,String Text,ILabelStyle LabelStyle,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainLabel result = ITerrainLabel.fromHandle(NativeCreateTextLabel(this.getHandle(),Position,Text,LabelStyle,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainLabel CreateTextLabel(IPosition Position,String Text) throws ApiException
    {
        return CreateTextLabel(Position,Text,null,"","");
    }

    public ITerrainLabel CreateTextLabel(IPosition Position,String Text,ILabelStyle LabelStyle) throws ApiException
    {
        return CreateTextLabel(Position,Text,LabelStyle,"","");
    }

    public ITerrainLabel CreateTextLabel(IPosition Position,String Text,ILabelStyle LabelStyle,String GroupID) throws ApiException
    {
        return CreateTextLabel(Position,Text,LabelStyle,GroupID,"");
    }

    public ITreeHotlink CreateTreeHotlink(String MessageID,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITreeHotlink result = ITreeHotlink.fromHandle(NativeCreateTreeHotlink(this.getHandle(),MessageID,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITreeHotlink CreateTreeHotlink(String MessageID) throws ApiException
    {
        return CreateTreeHotlink(MessageID,"","");
    }

    public ITreeHotlink CreateTreeHotlink(String MessageID,String GroupID) throws ApiException
    {
        return CreateTreeHotlink(MessageID,GroupID,"");
    }

    public ITerrainVideo CreateVideoOnTerrain(String VideoFileName,IPosition Position,String GroupID,String Description) throws ApiException
    {
        checkDisposed();
        ITerrainVideo result = ITerrainVideo.fromHandle(NativeCreateVideoOnTerrain(this.getHandle(),VideoFileName,Position,GroupID,Description));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

    public ITerrainVideo CreateVideoOnTerrain(String VideoFileName,IPosition Position) throws ApiException
    {
        return CreateVideoOnTerrain(VideoFileName,Position,"","");
    }

    public ITerrainVideo CreateVideoOnTerrain(String VideoFileName,IPosition Position,String GroupID) throws ApiException
    {
        return CreateVideoOnTerrain(VideoFileName,Position,GroupID,"");
    }

    public void DeleteObject(String ObjectID) throws ApiException
    {
        checkDisposed();
        NativeDeleteObject(this.getHandle(),ObjectID);
        TEErrorHelper.ThrowExceptionOnError();
    }

    public ITerraExplorerObject GetObject(String ObjectID) throws ApiException
    {
        checkDisposed();
        ITerraExplorerObject result = ITerraExplorerObject.fromHandle(NativeGetObject(this.getHandle(),ObjectID));
        TEErrorHelper.ThrowExceptionOnError();
        return result;
    }

private static native int NativeGetGeometryCreator(int instance);

private static native int NativeCreate3DArrow(int instance,IPosition Position,double Length,int Style,double ObjectHeight,Object LineColor,Object FillColor,String GroupID,String Description);

private static native int NativeCreate3DPolygon(int instance,IGeometry pIGeometry,double ObjectHeight,Object LineColor,Object FillColor,int AltitudeType,String GroupID,String Description);

private static native int NativeCreateArc(int instance,IPosition Position,double RadiusX,double RadiusY,double StartAngle,double EndAngle,Object LineColor,Object FillColor,int NumOfSegments,String GroupID,String Description);

private static native int NativeCreateArrow(int instance,IPosition Position,double Length,int Style,Object LineColor,Object FillColor,String GroupID,String Description);

private static native int NativeCreateBox(int instance,IPosition Position,double ObjectWidth,double ObjectDepth,double ObjectHeight,Object LineColor,Object FillColor,String GroupID,String Description);

private static native int NativeCreateBuilding(int instance,IGeometry pIGeometry,double RoofHeight,int AltitudeType,String GroupID,String Description);

private static native int NativeCreateCircle(int instance,IPosition Position,double Radius,Object LineColor,Object FillColor,String GroupID,String Description);

private static native int NativeCreateColor(int instance,int Red,int Green,int Blue,int Alpha);

private static native int NativeCreateCone(int instance,IPosition Position,double Radius,double ObjectHeight,Object LineColor,Object FillColor,int NumOfSegments,String GroupID,String Description);

private static native int NativeCreateCylinder(int instance,IPosition Position,double Radius,double ObjectHeight,Object LineColor,Object FillColor,int NumOfSegments,String GroupID,String Description);

private static native int NativeCreateDynamicObject(int instance,Object Waypoints,int MotionStyle,int ObjectType,String FileNameOrText,double ScaleFactor,int AltitudeType,String GroupID,String Description);

private static native int NativeCreateEffect(int instance,IPosition Position,String EffectsXML,String GroupID,String Description);

private static native int NativeCreateElevationLayer(int instance,String ElevationFileName,double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY,Object InitParam,Object PlugName,String GroupID,String Description,double HScale,double HOffset);

private static native int NativeCreateEllipse(int instance,IPosition Position,double RadiusX,double RadiusY,Object LineColor,Object FillColor,int NumOfSegments,String GroupID,String Description);

private static native int NativeCreateFeatureLayer(int instance,String layerName,String sConnectionString,String GroupID);

private static native int NativeCreateFromStream(int instance,Object Stream,String GroupID);

private static native int NativeCreateHoleOnTerrain(int instance,IGeometry pIGeometry,String GroupID,String Description);

private static native int NativeCreateImageLabel(int instance,IPosition Position,String ImageFileName,ILabelStyle LabelStyle,String GroupID,String Description);

private static native int NativeCreateImageryLayer(int instance,String ImageryFileName,double UpperLeftX,double UpperLeftY,double LowerRightX,double LowerRightY,Object InitParam,Object PlugName,String GroupID,String Description);

private static native int NativeCreateKMLLayer(int instance,String Path,String GroupID);

private static native int NativeCreateLabel(int instance,IPosition Position,String Text,String ImageFileName,ILabelStyle LabelStyle,String GroupID,String Description);

private static native int NativeCreateLabelStyle(int instance,int ls);

private static native int NativeCreateLocation(int instance,IPosition Position,String GroupID,String Description);

private static native int NativeCreateLocationHere(int instance,String GroupID,String Description);

private static native int NativeCreateMeshLayerFromFile(int instance,String FilePath,String ParentGroupID);

private static native int NativeCreateMeshLayerFromSFS(int instance,String ServerPath,String layerName,String ParentGroupID);

private static native int NativeCreateMessage(int instance,int TargetPosition,String msgData,int Type,boolean BringToFront);

private static native int NativeCreateModel(int instance,IPosition Position,String FileName,double Scale,int ModelType,String GroupID,String Description);

private static native int NativeCreateNewFeatureLayer(int instance,String layerName,int LayerGeomType,String sConnectionString,String GroupID);

private static native int NativeCreatePointCloudModel(int instance,String ModelFileName,IPosition Position,String GroupID,String Description);

private static native int NativeCreatePolygon(int instance,IGeometry pIGeometry,Object LineColor,Object FillColor,int AltitudeType,String GroupID,String Description);

private static native int NativeCreatePolygonFromArray(int instance,Object verticesArray,Object LineColor,Object FillColor,int AltitudeType,String GroupID,String Description);

private static native int NativeCreatePolyline(int instance,IGeometry pIGeometry,Object LineColor,int AltitudeType,String GroupID,String Description);

private static native int NativeCreatePolylineFromArray(int instance,Object verticesArray,Object LineColor,int AltitudeType,String GroupID,String Description);

private static native int NativeCreatePopupMessage(int instance,String Caption,String Src,int Left,int Top,int Width,int Height,int Timeout);

private static native int NativeCreatePosition(int instance,double X,double Y,double Altitude,int AltitudeType,double Yaw,double Pitch,double Roll,double Distance);

private static native int NativeCreatePresentation(int instance,String GroupID,String Description);

private static native int NativeCreatePyramid(int instance,IPosition Position,double ObjectWidth,double ObjectDepth,double ObjectHeight,Object LineColor,Object FillColor,String GroupID,String Description);

private static native int NativeCreateRectangle(int instance,IPosition Position,double ObjectWidth,double ObjectDepth,Object LineColor,Object FillColor,String GroupID,String Description);

private static native int NativeCreateRegularPolygon(int instance,IPosition Position,double Radius,int NumOfSegments,Object LineColor,Object FillColor,String GroupID,String Description);

private static native int NativeCreateRouteWaypoint(int instance,double X,double Y,double Altitude,double Speed,double Yaw,double Pitch,double Roll,double CameraDeltaYaw,double CameraDeltaPitch,String MessageID);

private static native int NativeCreateSphere(int instance,IPosition Position,double Radius,int Style,Object LineColor,Object FillColor,int SegmentDensity,String GroupID,String Description);

private static native int NativeCreateTerrainModifier(int instance,IGeometry pIGeometry,int Mode,boolean Flat,double Feather,String GroupID,String Description);

private static native int NativeCreateTextLabel(int instance,IPosition Position,String Text,ILabelStyle LabelStyle,String GroupID,String Description);

private static native int NativeCreateTreeHotlink(int instance,String MessageID,String GroupID,String Description);

private static native int NativeCreateVideoOnTerrain(int instance,String VideoFileName,IPosition Position,String GroupID,String Description);

private static native void NativeDeleteObject(int instance,String ObjectID);

private static native int NativeGetObject(int instance,String ObjectID);
};
