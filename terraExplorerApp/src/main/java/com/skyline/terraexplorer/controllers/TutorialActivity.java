package com.skyline.terraexplorer.controllers;

import com.skyline.terraexplorer.R;
import com.skyline.terraexplorer.models.ToolManager;
import com.skyline.terraexplorer.tools.TutorialTool;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TutorialActivity extends MatchParentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tutorial);
		Button button = (Button)findViewById(R.id.tutorial_skip);
		button.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				skipButtonClicked();
			}
		});
		button = (Button)findViewById(R.id.tutorial_watch);
		button.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				watchButtonClicked();
			}
		});
	}
	
	private void skipButtonClicked()
	{
		finish();
	}
	
	private void watchButtonClicked()
	{
		ToolManager.INSTANCE.openTool(TutorialTool.class.getName());
		finish();
	}
}
