package com.nwyun.birdegg;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class SplashActivity extends Activity {
	private static final String TAG = "SplashActivity";
	// 首次使用
	boolean isFirstUse = false;

	private static final int GO_GUIDE = 101;
	private static final int GO_MAIN = 102;
	private static final long SPLASH_DELAY_MILLIS = 1000 * 3;

	private static final String PREFERENCES_NAME = "splash";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_main);
		init();
	}

	private void init() {
		Log.d(TAG, "init() starting.");
		SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME,
				MODE_PRIVATE);

		isFirstUse = preferences.getBoolean("isFirstUse", true);
		if (isFirstUse) {
			_handler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
			return;
		}

		_handler.sendEmptyMessageDelayed(GO_MAIN, SPLASH_DELAY_MILLIS);
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
		changeUsed();
	}

	private void changeUsed() {
		SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME,
				MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putBoolean("isFirstUse", false);
		editor.commit();
	}

	private void goMain() {
		// TODO
	}
}
