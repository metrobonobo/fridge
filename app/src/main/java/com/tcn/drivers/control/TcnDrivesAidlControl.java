package com.tcn.drivers.control;

import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tcn.drivers.aidl.IDriversInterface;
import com.tcn.drivers.aidl.TcnOnDriversArrivedListener;

import java.util.concurrent.CopyOnWriteArrayList;

public class TcnDrivesAidlControl {
	private static TcnDrivesAidlControl m_Instance = null;
	private static final String TAG = "TcnDrivesAidlControl";
	private volatile boolean m_isInited = false;
	private Handler m_ReceiveHandler = null;
//	private TcnServiceConnection m_ServiceConnection = null;
	public IDriversInterface iIDriversInterface = null;
	private TcnOnDriversArrivedListener m_TcnOnDriversArrivedListener = null;
	private Context m_context;
	private TcnConnection m_TcnConnection = null;
	private AidlBroadcastReceiver m_AidlBroadcastReceiver;

	public static synchronized TcnDrivesAidlControl getInstance() {
		if (null == m_Instance) {
			m_Instance = new TcnDrivesAidlControl();
		}
		return m_Instance;
	}

	public void init(Handler handlerReceive, Context context) {
		if (!m_isInited) {
			m_isInited = true;
			m_ReceiveHandler = handlerReceive;
			m_context = context;
			Log.i(TAG, "init()");

			m_TcnOnDriversArrivedListener = new TcnOnDriversArrivedListener(handlerReceive);

			/*m_ServiceConnection = new TcnServiceConnection(context,tcnOnDriversArrivedListener);
				/*Intent mIntent = new Intent();
				mIntent.setAction("com.tcn.drives.DeviceInterface");//你定义的service的action
				mIntent.setPackage("com.tcn.drivers");//这里你需要设置你应用的包名
				context.bindService(mIntent, m_ServiceConnection, BIND_AUTO_CREATE);
			*/
			m_TcnConnection = new TcnConnection();
			BinderPool.getInsance().init(context,m_TcnConnection);

			//动态注册广播
			m_AidlBroadcastReceiver = new AidlBroadcastReceiver(m_context);
			IntentFilter intentFilter = new IntentFilter(m_AidlBroadcastReceiver.ACTION_DRIVES_APP_START);
			m_context.registerReceiver(m_AidlBroadcastReceiver, intentFilter);
		}
	}

	public void reqQueryInfo() {
		if (null == iIDriversInterface) {
			return;
		}
		try {
			iIDriversInterface.reqQueryInfo();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void reqQueryMachineInfo(int machineIndex) {
		if (null == iIDriversInterface) {
			return;
		}
		try {
			iIDriversInterface.reqQueryMachineInfo(machineIndex);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void reqQueryMachineStatus(int machineIndex) {
		if (null == iIDriversInterface) {
			return;
		}
		try {
			iIDriversInterface.reqQueryMachineStatus(machineIndex);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void reqQueryWorkStatus(int machineIndex,int type) {
		if (null == iIDriversInterface) {
			return;
		}
		try {
			iIDriversInterface.reqQueryWorkStatus(machineIndex,type,null);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void reqCleanDriveFaults(int machineIndex) {
		if (null == iIDriversInterface) {
			return;
		}
		try {
			iIDriversInterface.reqCleanDriveFaults(machineIndex,null);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void ship(int slotNo, int heartTimeSeconds,String shipMethod, String amount, String tradeNo, String jsonData) {
		if (null == iIDriversInterface) {
			return;
		}
		try {
			Log.i(TAG, "ship() slotNo: " + slotNo + " shipMethod: " +shipMethod
					+ " amount: " +amount+ " tradeNo: " +tradeNo+ " jsonData: " +jsonData);
			iIDriversInterface.ship(slotNo,heartTimeSeconds,shipMethod,amount,tradeNo,jsonData);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void shipTest(int startSlotNo, int endSlotNo, int heartTimeSeconds, CopyOnWriteArrayList<Integer> slotNoList) {
		if (null == iIDriversInterface) {
			return;
		}
		try {
			JsonObject mJsonObject = new JsonObject();
			Gson g = new Gson();
			if (null == slotNoList) {

			} else {
				String jsonSlotList = g.toJson(slotNoList);
				mJsonObject.addProperty("slotNoList",jsonSlotList);
			}

			Log.i(TAG, "shipTest() startSlotNo: " + startSlotNo + " endSlotNo: " +endSlotNo
					+ " mJsonObject: " +mJsonObject);
			iIDriversInterface.shipTest(startSlotNo,endSlotNo,heartTimeSeconds,mJsonObject.toString());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void reqSelectSlotNo(int slotNo, boolean queryUsable) {
		if (null == iIDriversInterface) {
			return;
		}
		try {
			JsonObject mJsonObject = new JsonObject();
			mJsonObject.addProperty("queryUsable",queryUsable);
			Log.i(TAG, "reqSelectSlotNo() slotNo: " + slotNo + " jsonData: " +mJsonObject);
			iIDriversInterface.reqSelectSlotNo(slotNo,mJsonObject.toString());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void reqQueryParameters(int machineIndex,int address, int data1, int data2, String jsonData) {
		if (null == iIDriversInterface) {
			return;
		}
		try {
			iIDriversInterface.reqQueryParameters(machineIndex,address,data1,data2,jsonData);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void reqSetParameters(int machineIndex,int address, int data1, int data2, int data3, int data4, String jsonData) {
		if (null == iIDriversInterface) {
			return;
		}
		try {
			Log.i(TAG, "reqSetParameters() machineIndex: " + machineIndex + " address: " +address
					+ " data1: " +data1+ " data2: " +data2+ " data3: " +data3+ " data4: " +data4+ " jsonData: " +jsonData);

			iIDriversInterface.reqSetParameters(machineIndex,address,data1,data2,data3,data4,1,jsonData);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void reqActionDo(int machineIndex,int actionType, int data1, int data2, int data3, int data4, String jsonData) {
		if (null == iIDriversInterface) {
			return;
		}
		try {
			Log.i(TAG, "reqActionDo() machineIndex: " + machineIndex + " actionType: " +actionType
					+ " data1: " +data1+ " data2: " +data2+ " data3: " +data3+ " data4: " +data4+ " jsonData: " +jsonData);
			iIDriversInterface.reqActionDo(machineIndex,actionType,data1,data2,data3,data4,jsonData);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/*
	 * type:0 更新驱动板程序
	 * BoardId： data1
	 * SlaveBoardId：data2　　从1开始，第一块从驱动板
	 */
	public void reqUpdate(int machineIndex,int type, int data1, int data2, String jsonData) {
		if (null == iIDriversInterface) {
			return;
		}
		try {
			Log.i(TAG, "reqUpdate() machineIndex: " + machineIndex + " type: " +type
					+ " data1: " +data1+ " data2: " +data2+ " jsonData: " +jsonData);

			iIDriversInterface.reqUpdate(machineIndex,type,data1,data2,jsonData);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public int getShipStatus(String tradeNo) {
		int iRet = -1;
		if (null == iIDriversInterface) {
			return iRet;
		}
		try {
			iRet = iIDriversInterface.getShipStatus(tradeNo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return iRet;
	}

	public void setConfigJsonData(String jsonData) {
		if (null == iIDriversInterface) {
			return;
		}
		try {
			iIDriversInterface.setConfigJsonData(jsonData);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public String getConfigJsonData() {
		String strRet = null;
		if (null == iIDriversInterface) {
			return strRet;
		}
		try {
			strRet = iIDriversInterface.getConfigJsonData();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return strRet;
	}

	public boolean isCanUse() {
		boolean isCanUse = false;
		if (m_TcnOnDriversArrivedListener != null) {
			isCanUse = m_TcnOnDriversArrivedListener.isCanUse();
		}

		return isCanUse;
	}

	public boolean isServiceConnected() {
		boolean bRet = false;
		if (iIDriversInterface != null) {
			bRet = true;
		}

		return bRet;
	}

	public void reConnectService() {

		if (isServiceConnected()) {
			return;
		}

		BinderPool.getInsance().connectBinderPoolService();

	}


	private class TcnConnection implements ITcnConnection {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {




			Log.i(TAG, "onServiceConnected() name: " + name+" service: "+service);


			try {

				iIDriversInterface = BinderPool.getInsance().getIDriversInterface();

				Log.i(TAG, "onServiceConnected() iIDriversInterface: " + iIDriversInterface);

				if (iIDriversInterface != null) {
					iIDriversInterface.registerListener(m_TcnOnDriversArrivedListener);
					iIDriversInterface.reqAllInfo();
				}
			} catch (RemoteException e) {
				e.printStackTrace();
				Log.e(TAG, "onServiceConnected() RemoteException e: " + e);
			} catch (Exception e) {
				Log.e(TAG, "onServiceConnected() Exception e: " + e);
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i(TAG, "onServiceDisconnected() name: " + name+" isCanUse: "+isCanUse());
			iIDriversInterface = null;
			if (isCanUse()) {
				// 重新绑定服务
				reConnectService();
			}
		}
	}
}
