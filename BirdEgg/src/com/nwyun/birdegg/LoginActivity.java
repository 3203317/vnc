package com.nwyun.birdegg;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class LoginActivity extends Activity {

	private final static String TAG = "LoginActivity";

	private EditText _text_username;
	private EditText _text_userpass;
	private TextView _link_register;
	private Button _btn_showpass;

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
		_text_username = (EditText) findViewById(R.id.text_username);
		_text_userpass = (EditText) findViewById(R.id.text_userpass);
		_link_register = (TextView) findViewById(R.id.link_register);
		_btn_showpass = (Button) findViewById(R.id.btn_showpass);

		_btn_showpass.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				if (getString(R.string.login_main_btn_show).equals(
						_btn_showpass.getText())) {
					_text_userpass
							.setTransformationMethod(HideReturnsTransformationMethod
									.getInstance());
					_btn_showpass.setText(R.string.login_main_btn_hide);
				} else {
					_text_userpass
							.setTransformationMethod(PasswordTransformationMethod
									.getInstance());
					_btn_showpass.setText(R.string.login_main_btn_show);
				}
			}
		});

		_link_register.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d(TAG, "clicked register link.");
			}
		});

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
