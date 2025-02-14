package com.tcn.drivers.control;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AidlBroadcastReceiver extends BroadcastReceiver {
	private static final String TAG = "AidlBroadcastReceiver";

	public static final String ACTION_DRIVES_APP_START="Tcn.action.drives.start";

	private Context m_context;


	public AidlBroadcastReceiver(Context context) {
		m_context = context;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "onReceive() getAction: " + intent.getAction());

		if(ACTION_DRIVES_APP_START.equals(intent.getAction())) {
			BinderPool.getInsance().connectBinderPoolService();
		}
	}
}
