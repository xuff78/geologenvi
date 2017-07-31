package com.skyline.terraexplorer.models;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Handler;
import android.os.Looper;

import com.skyline.terraexplorer.TEAppException;
import com.skyline.terraexplorer.tools.*;
import com.skyline.terraexplorer.views.ToolContainer;
import com.skyline.terraexplorer.views.ToolContainer.OnContainerStartCloseListener;

public class ToolManager {
	public final static ToolManager INSTANCE = new ToolManager();
	private HashMap<String, ToolProtocol> tools = new HashMap<String, ToolProtocol>();
	private boolean toolsRegistered = false;
	private ToolManager() {
	}

	public void registerTools()
	{
		if(toolsRegistered)
			return;		
//	    registerTool(new SearchTool());
	    registerTool(new LayersTool());
	    registerTool(new ProjectsTool());
//	    registerTool(new PlacesTool());
	    registerTool(new GpsTool());
//	    registerTool(new CaptureShareTool());
//	    registerTool(new AboutTool());
//	    registerTool(new SettingsTool());
	    registerTool(new TutorialTool());
	    registerTool(new AreaTool());
	    registerTool(new DistanceTool());
	    registerTool(new LosTool());
//	    registerTool(new ShadowTool());
	    registerTool(new ProfileTool());
	    registerTool(new ViewshedTool());
	    registerTool(new EditFavoriteTool());
	    registerTool(new PresentationTool());
	    registerTool(new PresentationStepsTool());
	    registerTool(new QueryTool());
	    registerTool(new EditFeatureLayerTool());
	    registerTool(new EditFeatureTool());
	    registerTool(new AddFeatureTool());
//	    registerTool(new WhiteboardTool());
//	    registerTool(new WhiteboardAddFeatureTool());
//	    registerTool(new WhiteboardEditFeatureTool());
		toolsRegistered = true;
	}
	
	public void registerTool(ToolProtocol tool)
	{
	    String toolId = tool.getId();
	    if(tools.get(toolId) != null)
	    	throw new TEAppException(String.format("Tool with id %s already registered", toolId));
	    tools.put(toolId, tool);
	}
	
	public ArrayList<MenuEntry> getMenuEntries() {
	    ArrayList<MenuEntry> menuEntries = new ArrayList<MenuEntry>();
	    for (ToolProtocol tool : tools.values()) {
	    	MenuEntry me = tool.getMenuEntry();
	    	if(me != null)
	    		menuEntries.add(me);
	    }	    
	    return menuEntries;
	}
	
	public void openTool(String toolId)
	{
		openTool(toolId,null);
	}
	public void openTool(String toolId, Object param)
	{
		openTool(toolId, param, null, null);
	}
	public void openTool(String toolId, Object param, final String returnToTool, final Object returnToToolParam)
	{
	    ToolProtocol tool = tools.get(toolId);
	    tool.open(param);
	    if(tool instanceof ToolContainerDelegate)
	    {
	    	ToolContainer.INSTANCE.showWithDelegate((ToolContainerDelegate)tool, new OnContainerStartCloseListener() {
				@Override
				public void OnCloseStart() {
					if(returnToTool != null)
					{
						new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
							
							@Override
							public void run() {
								openTool(returnToTool, returnToToolParam);
							}
						}, 1);
					}
				}});
	    }
	}
}
