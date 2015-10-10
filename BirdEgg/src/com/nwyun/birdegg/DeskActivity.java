package com.nwyun.birdegg;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * 
 * @author Administrator
 * 
 */
public class DeskActivity extends Activity {

	private static final String TAG = "DeskActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate() starting.");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.desk_main);
	}

	@Override
	public void onStart() {
		super.onStart();
	}
}
