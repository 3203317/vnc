package com.nwyun.birdegg;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class LoginActivity extends Activity {

	private final static String TAG = "LoginActivity";

	private TextView _text_username;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_main);
	}

	@Override
	public void onStart() {
		Log.d(TAG, "onStart() starting.");
		super.onStart();
		findView();
	}

	private void findView() {
		_text_username = (TextView) findViewById(R.id.text_username);

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				popKeyboard();
			}

			/**
			 * 弹出软键盘
			 */
			private void popKeyboard() {
				InputMethodManager manager = (InputMethodManager) _text_username
						.getContext().getSystemService(
								Context.INPUT_METHOD_SERVICE);
				manager.showSoftInput(_text_username, 0);
			}
		}, 1000 * 1);
	}
}
