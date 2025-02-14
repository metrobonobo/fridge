package com.tcn.drivers.control;

import android.content.ComponentName;
import android.os.IBinder;

public interface ITcnConnection {
	public void onServiceConnected(ComponentName name, IBinder service);

	public void onServiceDisconnected(ComponentName name);
}
