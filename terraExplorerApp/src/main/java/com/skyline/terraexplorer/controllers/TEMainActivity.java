package com.skyline.terraexplorer.controllers;

import java.util.concurrent.Callable;

import com.skyline.core.CoreServices;
import com.skyline.teapi.*;
import com.skyline.teapi.ISGWorld.OnLoadFinishedListener;
import com.skyline.teapi.ISGWorld.OnSGWorldMessageListener;
import com.skyline.terraexplorer.R;
import com.skyline.terraexplorer.TEApp;
import com.skyline.terraexplorer.models.AppLinks;
import com.skyline.terraexplorer.models.ControlDragGestures;
import com.skyline.terraexplorer.models.ControlDragGestures.DragDirection;
import com.skyline.terraexplorer.models.LocalBroadcastManager;
import com.skyline.terraexplorer.models.MainButtonDragGestures;
import com.skyline.terraexplorer.models.MenuEntry;
import com.skyline.terraexplorer.models.ToolManager;
import com.skyline.terraexplorer.models.ControlDragGestures.ControlDragGesturesDelegate;
import com.skyline.terraexplorer.models.UI;
import com.skyline.terraexplorer.tools.ProjectsTool;
import com.skyline.terraexplorer.tools.SettingsTool;
import com.skyline.terraexplorer.views.MainMenuView;
import com.skyline.terraexplorer.views.MessageView;
import com.skyline.terraexplorer.views.MainMenuView.MainMenuViewDelegate;
import com.skyline.terraexplorer.views.ModalDialog.ModalDialogDelegate;
import com.skyline.terraexplorer.views.GLSurfaceView;
import com.skyline.terraexplorer.views.ModalDialog;
import com.skyline.terraexplorer.views.TEGLRenderer;
import com.skyline.terraexplorer.views.TEView;
import com.skyline.terraexplorer.views.ToolContainer;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class TEMainActivity extends Activity implements MainMenuViewDelegate, ControlDragGesturesDelegate, OnSGWorldMessageListener, ModalDialogDelegate {

	private MainMenuView mainMenu;
	private ImageButton mainButton;
	private View loadingView;
	private MessageView messageView;
	private ControlDragGestures menuButtonDragGestures;
	private TEView teView;
	public static final String USER_STATE_PROJECT = "com.skyline.terraexplorer.UserState.Project";
	public static final String USER_STATE_LOCATION = "com.skyline.terraexplorer.UserState.Location";
	private boolean engineInitialized = false;
	
	public native void teOnClose();
	
	private BroadcastReceiver engineInitializedReciever = new BroadcastReceiver()
    {
		@Override
		public void onReceive(Context context, Intent intent) {
					OnEngineInitialized();
					LocalBroadcastManager.getInstance(TEMainActivity.this).unregisterReceiver(engineInitializedReciever);
		}
    };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setupStrictMode();
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_temain);
		TEApp.setMainActivityContext(this);
		getWindow().setBackgroundDrawable(null);
		AppLinks.initializeAsync();		

		CoreServices.Init(this);
		
		mainMenu = (MainMenuView)findViewById(R.id.main_menu);
		mainMenu.setDelegate(this);
				
		mainButton = (ImageButton)findViewById(R.id.mainButton);
		mainMenu.setAnchor(mainButton);
		mainButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// for some reason main button is clickable through loading screen, which is bad.
				// so prevent main menu while loading fly
				if(TEMainActivity.this.loadingView.getVisibility() == View.VISIBLE)
					return;
				toggleMainMenu();
			}
		});
		
		ToolContainer.INSTANCE.attachRootViewTo(this.mainButton);
		
		messageView = (MessageView)findViewById(R.id.main_message_view);
		
		loadingView = findViewById(R.id.loadingView);
		
		menuButtonDragGestures = new ControlDragGestures(mainButton, this);
		
		teView = (TEView)findViewById(R.id.main_teview);
		teView.setOnTouchListener(new OnTouchListener() {			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				messageView.hide();
				mainMenu.hide();
				return false;
			}
		});
		
	    // show loading screen while TE initializes
		loadingView.setVisibility(View.VISIBLE);
		((TextView)findViewById(R.id.loadingView_loading)).setText(R.string.initializing);
	    ((TextView)findViewById(R.id.loadingView_projectName)).setText("");

		LocalBroadcastManager.getInstance(this).registerReceiver(engineInitializedReciever, new IntentFilter(TEGLRenderer.ENGINE_INITIALIZED));
	}
	
	
	private void setupStrictMode() {
		 if (TEApp.isDebug()) {
	         StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
	                 .detectDiskReads()
	                 .detectDiskWrites()
	                 .detectNetwork()   // or .detectAll() for all detectable problems
	                 .detectCustomSlowCalls()
	                 .penaltyLog()
	                 .penaltyDialog()
	                 .build());
	         StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
	                 .detectLeakedSqlLiteObjects()
	                 .detectLeakedClosableObjects()
	                 .detectActivityLeaks()
	                 .penaltyLog()
	                 .penaltyDeath()
	                 .build());
	     }
	}


	private void OnEngineInitialized()
	{
		engineInitialized = true;
		// restore loading screen text
		((TextView)findViewById(R.id.loadingView_loading)).setText(R.string.loading);

		// register tools
		ToolManager.INSTANCE.registerTools();

		// set views order
		mainButton.bringToFront();
		mainMenu.bringToFront();
		loadingView.bringToFront();
		messageView.bringToFront();
		
		
		// subscribe to load project from notification event
		LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				openProjectFromNotification(intent);				
			}}, ProjectsTool.LoadProjectFilter);
		
	    UI.runOnRenderThread(new Runnable() {			
			@Override
			public void run() {
				// subscribe to SGWorldMessages events
				ISGWorld.getInstance().addOnSGWorldMessageListener(TEMainActivity.this);
			    // we talk to TE only in latlon, to smiplify talking to search, flying to favorites and warkign with query tool
				ISGWorld.getInstance().getCoordServices().getSourceCoordinateSystem().InitLatLong();
			    // set shadow color
				ISGWorld.getInstance().SetOptionParam("GlobalShadowColor", 0x99000000);
			    if(TEApp.isDebug())
			    {
			    	ISGWorld.getInstance().SetParam(8360, null);
			    }
			    // set ui and screen scale factors
			    
			    ISGWorld.getInstance().SetParam(8350, getResources().getDisplayMetrics().density); // Pass the screen factor to TE
 			    ISGWorld.getInstance().SetParam(8370, UI.scaleFactor()); // Pass the UI scale factor to TE			    
			}
		});
	    String lastOpenedProject = getSharedPreferences(SettingsTool.PREFERENCES_NAME, Context.MODE_PRIVATE).getString(USER_STATE_PROJECT, null);
	    IPosition lastPosition = null;
	    if (lastOpenedProject == null)
	    {
	        lastOpenedProject = AppLinks.getDefaultFlyFile();
	    }
	    else
	    {
	    	final String position = getSharedPreferences(SettingsTool.PREFERENCES_NAME, Context.MODE_PRIVATE).getString(USER_STATE_LOCATION, null);
	        if(position != null)
	        {
	        		lastPosition = UI.runOnRenderThread(new Callable<IPosition>() {
	        			public IPosition call() throws Exception
	        			{
	        	        	try {
	        	        	String[] positionParts = position.split("_");
	        				return ISGWorld.getInstance().getCreator().CreatePosition(
	    							Double.parseDouble(positionParts[0]),
	    							Double.parseDouble(positionParts[1]),
	    							Double.parseDouble(positionParts[2]),
	    							Integer.parseInt(positionParts[3]),
	    							Double.parseDouble(positionParts[4]),
	    							Double.parseDouble(positionParts[5]),
	    							Double.parseDouble(positionParts[6]));
		    				} catch (NumberFormatException e) {
		    					// ignore the error
		    					e.printStackTrace();
		    				}
	        	        	return null;
	        			}
					});
	        }
	    }
	    openProject(lastOpenedProject, lastPosition);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		messageView.hide();
		mainMenu.hide();
        return super.onTouchEvent(event);		
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {		
        if (keyCode == KeyEvent.KEYCODE_MENU) {
        	mainMenu.show(ToolManager.INSTANCE.getMenuEntries());
            return true;
        }
        else if(keyCode == KeyEvent.KEYCODE_BACK)
        {
        	if(messageView.getVisibility() != View.GONE)
        	{
        		messageView.hide();
        		return true;
        	}
        	if(mainMenu.getVisibility() != View.GONE)
        	{
        		mainMenu.hide();
        		return true;
        	}
        }
        return super.onKeyDown(keyCode, event); 
    } 
	
	 @Override
	  protected void onStart() 
	 {
	    super.onStart();
	    
	    //teView.onStart();
	    //ToolManager.INSTANCE.openTool(ProfileTool.class.getName());
	  }

	 @Override
	  protected void onStop() {
	    super.onStop();
	    /*
	    teOnClose();
		finish();
		System.exit(0);
		*/
	  }

	 @Override
	  protected void onResume() {
	    super.onResume();
	    
	    // Return to the normal rendering mode (see comment in the onPause() call).
	    teView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);	    
	  }
	 
	  @Override
	  protected void onPause() 
	  {		  		  		 
	    super.onPause();
	    	    
	    // Instead of calling teView.onPause(), which will destroy the GL context and will stop the render thread (and will cause problems to TE) we simply "pause" the renderer by switching to "render when dirty" 
	    teView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	    
	    // it is possible to get onPause before engine is initialized, 
	    // so don't do nothing if we do not have engine
	    if(engineInitialized == false)
	    	return;
	    
	    UI.runOnRenderThreadAsync(new Runnable() {			
			@Override
			public void run() {
				String projectName = ISGWorld.getInstance().getProject().getName();
				if(TextUtils.isEmpty(projectName))
					return;
				IPosition pos = ISGWorld.getInstance().getNavigate().GetPosition();
				String positionStr = 
						Double.toString(pos.getX()) + "_" +
						Double.toString(pos.getY()) + "_" +
						Double.toString(pos.getAltitude()) + "_" +
						Integer.toString(pos.getAltitudeType()) + "_" +
						Double.toString(pos.getYaw()) + "_" +
						Double.toString(pos.getPitch()) + "_" +
						Double.toString(pos.getRoll());
			    getSharedPreferences(SettingsTool.PREFERENCES_NAME, Context.MODE_PRIVATE)
		    	.edit()
		    	.putString(USER_STATE_PROJECT, projectName)
		    	.putString(USER_STATE_LOCATION, positionStr)
		    	.apply();
			}
		});
	    
	  }
	  
	  @Override
	protected void onDestroy() {
		super.onDestroy();
		
		//fix bug #19718 
		System.exit(0);
		//////
		
	}

	  @Override
	public void onLowMemory() {
		UI.runOnRenderThread(new Runnable() {			
			@Override
			public void run() {
				teView.teOnLowMemory();
			}
		});
		super.onLowMemory();
	}
	  
	private void toggleMainMenu() {
		if(menuButtonDragGestures.isDragInProgress())
			return;

		if(mainMenu.getVisibility() == View.VISIBLE)
			mainMenu.hide();
		else
			mainMenu.show(ToolManager.INSTANCE.getMenuEntries());
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		mainMenu.updateHeight();
		super.onConfigurationChanged(newConfig);
	}

	
		@Override
		public void onMainMenuShow() {
			ToolContainer.INSTANCE.setEnabled(false);
		    menuButtonDragGestures.setEnabled(false);
		}

		@Override
		public void onMainMenuHide() {
			ToolContainer.INSTANCE.setEnabled(true);
		    menuButtonDragGestures.setEnabled(true);
		}

		@Override
		public void onMainMenuAction(MenuEntry menuEntry) {
			  ToolManager.INSTANCE.openTool(menuEntry.toolId, menuEntry.param);			
		}

		@Override
		public void dragGestureRecognizerFinishedWithDirection(ControlDragGestures recognizer, DragDirection dragDirection) {
		    int actionIndex = -1;
		    if(dragDirection == DragDirection.Right)
		    {
		    	actionIndex = getSharedPreferences(SettingsTool.PREFERENCES_NAME, MODE_PRIVATE).getInt(getString(R.string.key_menubutton_slide_right), -1);
		        if(actionIndex == -1)
		        	actionIndex = MainButtonDragGestures.instance.defaultRight();
		    }
		    if(dragDirection == DragDirection.Up)
		    {
		    	actionIndex = getSharedPreferences(SettingsTool.PREFERENCES_NAME, MODE_PRIVATE).getInt(getString(R.string.key_menubutton_slide_up), -1);
		        if(actionIndex == -1)
		        	actionIndex = MainButtonDragGestures.instance.defaultUp();
		    }
		    MainButtonDragGestures.instance.preformAction(actionIndex);
		}

		@Override
		public boolean OnSGWorldMessage(final String MessageID, final String SourceObjectID) {
		    String[] unsupportedSourceIds = new String[] {"MessageBarText", "ContainerMessage", "LoadFlyContainer"};
		    for(String unsupportedSourceId : unsupportedSourceIds)
		    {
		    	if(SourceObjectID.equalsIgnoreCase(unsupportedSourceId))
		    		return false;
		    }
			return messageView.showMessage(MessageID, SourceObjectID);
		}
		
		private void openProjectFromNotification(Intent intent) {
			String projectPath = intent.getStringExtra(ProjectsTool.PROJECT_PATH);
			openProject(projectPath, null);
		}

		private class ValueHolder
		{
			ApiException ex;
			OnLoadFinishedListener listener;
		}
		private void openProject(final String projectPath,final IPosition startPosition) {
		    // first try to close tool container
			if(ToolContainer.INSTANCE.hideAndClearDelegate() == false)
				return;
		    // create loading screen over the main view
			loadingView.setVisibility(View.VISIBLE);
			
		    String[] friendlyName = ProjectsActivity.getFriendlyName(projectPath);
		    ((TextView)findViewById(R.id.loadingView_projectName)).setText(String.format("%s/%s", friendlyName[0],friendlyName[1]));
		    
		    UI.runOnRenderThreadAsync(new Runnable() {					
		    	@Override
		    	public void run() {
		    		final ValueHolder vh = new ValueHolder();
		    		vh.listener = new OnLoadFinishedListener() {							
		    			@Override
		    			public void OnLoadFinished(boolean bSuccess) {
		    				ISGWorld.getInstance().removeOnLoadFinishedListener(vh.listener);
		    				UI.runOnUiThreadAsync(new Runnable() {							
		    					@Override
		    					public void run() {
		    						TEMainActivity.this.onLoadFinished(projectPath,startPosition,vh.ex);
		    					}
		    				});
		    			};
		    		};
		    		try
		    		{
		    			ISGWorld.getInstance().addOnLoadFinishedListener(vh.listener);
		    			ISGWorld.getInstance().getProject().Open(projectPath);						
		    		}
		    		catch(ApiException ex)
		    		{
		    			vh.ex = ex;
		    		}
		    	}
		    });

		}
		
		private void onLoadFinished(String projectPath,final IPosition startPosition,ApiException error)
		{
		    if(error == null)
		    {
		    	loadingView.setVisibility(View.GONE);
			    	UI.runOnRenderThreadAsync(new Runnable() {					
						@Override
						public void run() {
					    	if(startPosition != null)
					    	{
					    		ISGWorld.getInstance().getNavigate().SetPosition(startPosition);
					    	}
					        // disable sunlight. Big fix #18383
					        if(ISGWorld.getInstance().getCommand().IsChecked(1026, 0))
					        	ISGWorld.getInstance().getCommand().Execute(1026, 0);
						}
					});
		    }
		    else
		    {
	            // first of all, show error message
			    String[] friendlyName = ProjectsActivity.getFriendlyName(projectPath);
			    String friendlyNameText = String.format("%s/%s", friendlyName[0],friendlyName[1]);
		    	String errorMessage = String.format(getString(R.string.loading_project_error), friendlyNameText, error.getLocalizedMessage());
                // if this was an network project, assume that error was in network and suggest to reload
				ModalDialog modalDialog = new ModalDialog(R.string.loading_error_title, this);
				modalDialog.setContentMessage(errorMessage);
				if(projectPath.startsWith("/"))
				{
					modalDialog.setOneButtonMode();
				}
				else
				{
					modalDialog.setOkButtonTitle(R.string.retry);
					modalDialog.setTag(new Object[] { projectPath, startPosition });
				}
				modalDialog.show();
		    }
		}

		@Override
		public void modalDialogDidDismissWithOk(ModalDialog dlg) {
		    Object[] data = (Object[])dlg.getTag();
		    if(data != null)
		    {
			    String projectPath = (String)data[0];
			    IPosition position = (IPosition)data[1];
			    openProject(projectPath, position);
		    }
		    else
		    {
		    	modalDialogDidDismissWithCancel(dlg);
		    }
		}

		@Override
		public void modalDialogDidDismissWithCancel(ModalDialog dlg) {
			// ProjectsTool will call loadProject with delay, so clear the name of old project
			((TextView)findViewById(R.id.loadingView_projectName)).setText("");
      		ToolManager.INSTANCE.openTool(ProjectsTool.class.getName(), true);
		}


}
