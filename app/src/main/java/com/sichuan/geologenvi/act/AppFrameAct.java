package com.sichuan.geologenvi.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.utils.ActUtil;


/**
 * 应用界面的框架。在本应用中，有：吉林银行图片头、头下的标题横条、最底部的两个按钮。另外，加入了退出菜单。
 * 
 * @author heyongbo@infohold.com.cn
 */

public abstract class AppFrameAct extends AppCompatActivity {


	public ImageButton btnHome, btnBack;
	
	public Button rightTxtBtn;
	
	public boolean flag;

	private ClickListener listener;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		StatusBarCompat.compat(this, getResources().getColor(R.color.hard_gray));

		super.setContentView(R.layout.of_app_frame);
		listener = new ClickListener();
		btnHome=(ImageButton) findViewById(R.id.of_back_home_imagebtn);
		btnHome.setOnClickListener(listener);
		btnBack=(ImageButton)findViewById(R.id.of_back_imagebtn);
		btnBack.setOnClickListener(listener);

		ActUtil.initImageLoader(this);
	}
	
	
	@Override
	public void setContentView(int layoutResID) {

		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		View content = inflater.inflate(layoutResID, null);

		FrameLayout frameLayout = (FrameLayout) findViewById(R.id.app_frame_content);
		FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT);

		frameLayout.addView(content, -1, layoutParams);
	}

	private final class ClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.of_back_home_imagebtn) {
				
			} else if (id == R.id.of_back_imagebtn) {
				KeyBoardCancle();
				finish();
			}
		}
	}

	protected static final int Menu_ItemId_Exit = 0x80000000;

	/**
	 * <p>
	 * 设置最顶部“吉林银行”图片上的文字.
	 *
	 * <p>
	 * 注意：方法名以“_”之目的是为了与其它的set方法区分，即以下划线开头的set方法是为了设置当前应用界面的.
	 */
	protected void _setHeaderTitle(String title) {
		TextView tv = (TextView) findViewById(R.id.of_header_title_tv);
		tv.setVisibility(View.VISIBLE);
		tv.setText(title);
	}

	protected View _setHeaderGone() {
		LinearLayout rl = (LinearLayout) findViewById(R.id.app_heder_layout);
		rl.setVisibility(View.GONE);
		return rl;
	}

	protected View _setHeaderShown() {
		LinearLayout rl = (LinearLayout) findViewById(R.id.app_heder_layout);
		rl.setVisibility(View.VISIBLE);
		return rl;
	}

	/** 隐藏左边后退按钮 */
	protected void _setLeftBackGone() {
		ImageButton btn = (ImageButton) findViewById(R.id.of_back_imagebtn);
		btn.setVisibility(View.GONE);
		flag = true;
	}

	protected void _setLeftBackListener(OnClickListener listener) {
		ImageButton btn = (ImageButton) findViewById(R.id.of_back_imagebtn);
		btn.setOnClickListener(listener);
	}

	protected void _setRightHomeListener(OnClickListener listener) {
		ImageButton btn = (ImageButton) findViewById(R.id.of_back_home_imagebtn);
		btn.setOnClickListener(listener);
	}

	protected void _setRightHome(int res, OnClickListener listener) {
		ImageButton btn = (ImageButton) findViewById(R.id.of_back_home_imagebtn);
		btn.setVisibility(View.VISIBLE);
		btn.setOnClickListener(listener);
		btn.setImageResource(res);
	}

	/** 隐藏右边主页按钮 */
	protected void _setRightHomeGone() {
		btnHome.setVisibility(View.GONE);
		flag = true;
	}
	/** 显示右边主页文字按钮 */
	protected void _setRightHomeText(String name, OnClickListener listener) {
		btnHome.setVisibility(View.GONE);
		rightTxtBtn.setVisibility(View.VISIBLE);
		rightTxtBtn.setText(name);
		flag = false;
		rightTxtBtn.setOnClickListener(listener);
	}
	/** 显示右边主页文字按钮 */
	protected void _setRightHomeText(String name) {
		rightTxtBtn.setVisibility(View.VISIBLE);
		rightTxtBtn.setText(name);
		flag = false;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (KeyEvent.KEYCODE_BACK == keyCode && flag) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		ActUtil.initImageLoader(this);
	}
	
	public void KeyBoardCancle() {

		View view = getWindow().peekDecorView();
		if (view != null) {

			InputMethodManager inputmanger = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}
	
}
