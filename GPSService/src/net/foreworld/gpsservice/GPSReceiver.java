package net.foreworld.gpsservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class GPSReceiver extends BroadcastReceiver {

	private static final String TAG = "GPSReceiver";
	private final String ACTION = "android.intent.action.USER_PRESENT";

	@Override
	public void onReceive(Context ctx, Intent intent) {
		// TODO
		if (ACTION.equals(intent.getAction())) {
			Intent service = new Intent("net.foreworld.gpsservice.GPSService");
			ctx.startService(service);
			Log.v(TAG, "system onboot");

		}
	}
}
