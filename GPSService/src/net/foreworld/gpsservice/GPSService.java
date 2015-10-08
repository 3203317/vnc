package net.foreworld.gpsservice;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.IBinder;
import android.util.Log;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class GPSService extends Service {

	private static final String TAG = "GPSService";
	private GpsUtil _gpsUtil = null;

	private SQLiteDatabase _db;

	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "onBind() executed");
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		startDB();
		registerScreenActionReceiver();
		startGpsService();
	}

	private void startDB() {
		DBHelper database = new DBHelper(this);
		_db = database.getReadableDatabase();
	}

	private void startGpsService() {
		_gpsUtil = new GpsUtil(GPSService.this);
	}

	@Override
	public void onDestroy() {
		_gpsUtil.close();
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
			_gpsUtil.refresh();
		}
	};

	public void insertDB(Location location) {
		ContentValues cv = new ContentValues();
		cv.put("info", location.getLatitude() + " " + location.getLongitude());
		_db.insert("user", null, cv);
	}

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
