package net.foreworld.gpsservice;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class GpsUtil {

	public final static String TAG = "GpsUtil";

	LocationManager _locationManager;
	Context _ctx;

	public GpsUtil(Context ctx) {
		_ctx = ctx;
		_locationManager = (LocationManager) ctx
				.getSystemService(Context.LOCATION_SERVICE);
	}

	public Location getGeographyInfo() {
		Criteria c = new Criteria();
		c.setAccuracy(Criteria.ACCURACY_FINE); // 高精度
		c.setAltitudeRequired(false);
		c.setBearingRequired(false);
		c.setCostAllowed(true);
		c.setPowerRequirement(Criteria.POWER_LOW); // 低功耗

		String provider = _locationManager.getBestProvider(c, true);

		_locationManager.requestLocationUpdates(provider, 1000 * 60, 200,
				listener);
		Location l = _locationManager.getLastKnownLocation(provider);
		return l;
	}

	/**
	 * 判断是否启动定位服务
	 * 
	 * @param ctx
	 * @return
	 */
	public boolean isOpenGps() {
		if (null != _locationManager) {
			// 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
			boolean isGps = _locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);
			if (isGps)
				return true;

			// 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
			boolean isNetwork = _locationManager
					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			if (isNetwork)
				return true;
		}
		return false;
	}

	/**
	 * 判断是否启动全部网络连接，包括WIFI和流量
	 * 
	 * @param ctx
	 * @return
	 */
	public boolean isNetworkConnected() {
		ConnectivityManager manager = (ConnectivityManager) _ctx
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo network = manager.getActiveNetworkInfo();

		if (null == network)
			return false;
		return network.isAvailable();
	}

	/**
	 * 判断是否启动WIFI连接
	 * 
	 * @param ctx
	 * @return
	 */
	public boolean isWifiConnected() {
		WifiManager wifi = (WifiManager) _ctx
				.getSystemService(Context.WIFI_SERVICE);

		if (null == wifi)
			return false;
		return wifi.isWifiEnabled();
	}

	private LocationListener listener = new LocationListener() {

		@Override
		public void onLocationChanged(Location location) {
			Log.i(TAG, "时间: " + location.getTime());
			Log.i(TAG, "海拔: " + location.getAltitude());
			Log.i(TAG, "经度: " + location.getLongitude());
			Log.i(TAG, "纬度: " + location.getLatitude());
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO
		}

		@Override
		public void onProviderEnabled(String provider) {
			Location l = _locationManager.getLastKnownLocation(provider);
			if (null != l) {
				// TODO
			}
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			switch (status) {
			case LocationProvider.AVAILABLE: // GPS状态为可见时
				Log.i(TAG, "当前GPS状态为可见状态");
				break;
			case LocationProvider.OUT_OF_SERVICE: // GPS状态为服务区外时
				Log.i(TAG, "当前GPS状态为服务区外状态");
				break;
			case LocationProvider.TEMPORARILY_UNAVAILABLE: // GPS状态为暂停服务时
				Log.i(TAG, "当前GPS状态为暂停服务状态");
				break;
			}
		}

	};
}
