package com.nwyun.birdegg;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class SplashActivity extends Activity {
	private static final String TAG = "SplashActivity";

	private static final int GO_GUIDE = 101;
	private static final int GO_MAIN = 102;
	private static final long SPLASH_DELAY_MILLIS = 1000 * 3;

	private static final String PREFERENCE_NAME = "splash_activity";

	private ImageView _welcome_img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_main);
		findView();
		init();
	}

	private void findView() {
		_welcome_img = (ImageView) findViewById(R.id.welcome_img);
		// 默认隐藏
		_welcome_img.setVisibility(View.INVISIBLE);
	}

	private void init() {
		Log.d(TAG, "init() starting.");

		if (isFirstUse()) {
			_handler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
			return;
		}

		_handler.sendEmptyMessageDelayed(GO_MAIN, SPLASH_DELAY_MILLIS);
	}

	/**
	 * 首次使用
	 * 
	 * @return
	 */
	private boolean isFirstUse() {
		SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME,
				MODE_PRIVATE);
		return preferences.getBoolean("isFirstUse", true);
	}

	/**
	 * 改变首次使用状态
	 */
	private void setFirstUse() {
		SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME,
				MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putBoolean("isFirstUse", false);
		editor.commit();
	}

	@SuppressLint("HandlerLeak")
	private Handler _handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GO_GUIDE:
				goGuide();
				break;
			case GO_MAIN:
				goMain();
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
	};

	private void goGuide() {
		// TODO
		setFirstUse();
		goMain();
	}

	private void goMain() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}
