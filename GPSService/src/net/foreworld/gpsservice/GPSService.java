package net.foreworld.gpsservice;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class GPSService extends Service {

	private static final String TAG = "GPSService";

	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "onBind() executed");
		return null;
	}

	public void onCreate() {
		Log.d(TAG, "onCreate() executed");
		super.onCreate();
		gps();
		registerScreenActionReceiver();
	}

	public void gps() {
		new GpsUtil(GPSService.this);
	}

	public void onDestroy() {
		super.onDestroy();
	}

	private void registerScreenActionReceiver() {
		final IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		filter.addAction(Intent.ACTION_SCREEN_ON);
		registerReceiver(receiver, filter);
	}

	private final BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(final Context ctx, final Intent intent) {
			Log.v(TAG, "receiver ...");

			new Thread() {
				@Override
				public void run() {
					doGet();
				}
			}.start();
		}

	};

	public String doGet() {
		try {
			URL localURL = new URL(
					"http://192.168.0.108:3013/gps?command=insert");
			URLConnection connection = localURL.openConnection();
			HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

			httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");

			if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream in = httpURLConnection.getInputStream();
				in.close();
			}
			httpURLConnection.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.v(TAG, e.getMessage());
		}

		return "";
	}

}
