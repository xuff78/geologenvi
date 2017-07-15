package com.skyline.terraexplorer.tools;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.preference.PreferenceManager;

import com.skyline.terraexplorer.TEApp;
import com.skyline.terraexplorer.controllers.TutorialActivity;
import com.skyline.terraexplorer.models.AppLinks;

public class TutorialTool extends BaseTool{
	
	public TutorialTool()
	{
		boolean tutorialWasShown = PreferenceManager.getDefaultSharedPreferences(TEApp.getAppContext()).getBoolean("tutorial_was_shown", false);
		if(tutorialWasShown == false)
		{
			 Editor editor = PreferenceManager.getDefaultSharedPreferences(TEApp.getAppContext()).edit();
			 editor.putBoolean("tutorial_was_shown", true);
			 editor.apply();
			 showSplashScreen();
		}
	}
	
	private void showSplashScreen()
	{
		TEApp.getCurrentActivityContext().startActivity(new Intent(TEApp.getCurrentActivityContext(),TutorialActivity.class));
	}

	@Override
	public void open(Object param) {
		TEApp.getCurrentActivityContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(AppLinks.getTutorialUrl())));
	}
}
