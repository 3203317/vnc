package net.foreworld.gpsservice;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;

/**
 * 
 * @author huangxin (3203317@qq.com)
 * 
 */
public class GpsUtil {

	private final static String TAG = "GpsUtil";

	private LocationManager _locationManager;

	private long _minTime = 1000 * 60;
	private float _minDistance = 1;

	private Location location;

	private void setLocation(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}

	public GpsUtil(Context ctx) {
		_locationManager = (LocationManager) ctx
				.getSystemService(Context.LOCATION_SERVICE);
		if (null != _locationManager)
			init();
	}

	private String getProvider() {
		Criteria c = new Criteria();
		c.setAccuracy(Criteria.ACCURACY_FINE); // 高精度
		c.setAltitudeRequired(true);
		c.setBearingRequired(true);
		c.setSpeedRequired(true);
		c.setCostAllowed(true);
		c.setPowerRequirement(Criteria.POWER_LOW); // 低功耗
		return _locationManager.getBestProvider(c, true);
	}

	private void init() {
		String provider = getProvider();
		Location l = _locationManager.getLastKnownLocation(provider);

		while (null == l) {
			_locationManager.requestLocationUpdates(provider, _minTime,
					_minDistance, _locationListener);
		}

		setLocation(l);

		Log.i(TAG, "init() started.");
	}

	public void refresh() {
		Log.d(TAG, "refresh() started");
	}

	public void stop() {
		if (null != _locationManager) {
			if (null != _locationListener) {
				_locationManager.removeUpdates(_locationListener);
				_locationListener = null;
			}
			_locationManager = null;
		}
	}

	private LocationListener _locationListener = new LocationListener() {

		@Override
		public void onLocationChanged(Location location) {
			if (null != location)
				setLocation(location);
		}

		@Override
		public void onProviderDisabled(String provider) {
			setLocation(null);
		}

		@Override
		public void onProviderEnabled(String provider) {
			Location l = _locationManager.getLastKnownLocation(provider);
			if (null != l)
				setLocation(l);
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			switch (status) {
			case LocationProvider.AVAILABLE:
				break;
			case LocationProvider.OUT_OF_SERVICE:
				break;
			case LocationProvider.TEMPORARILY_UNAVAILABLE:
				break;
			}
		}

	};
}
