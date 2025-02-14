package com.tcn.drivers.control;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.tcn.drivers.aidl.IBinderPool;
import com.tcn.drivers.aidl.IDriversInterface;
import com.tcn.drivers.impl.IDriversInterfaceImpl;

import java.util.concurrent.CountDownLatch;

import static android.content.Context.BIND_AUTO_CREATE;

public class BinderPool {
	private static final String TAG = "BinderPool";

	public static final int BINDER_CODE_DRIVE_COMMON = 1;
	public static final int BINDER_CODE_TOOL=2;

	private Context m_context = null;
	private ITcnConnection m_ITcnConnection = null;
	private IBinderPool mBinderPool;

	// 一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待(异步转同步)
	private CountDownLatch mConnectBinderPoolCountDownLatch;

	// 单例
	private static volatile BinderPool sInstance;

	public static BinderPool getInsance() {
		if (sInstance == null) {
			synchronized (BinderPool.class) {
				if (sInstance == null) {
					sInstance = new BinderPool();
				}
			}
		}
		return sInstance;
	}

	public BinderPool() {

	}

	public void init(Context context,ITcnConnection connection) {
//		m_context = context.getApplicationContext();
		m_context = context;
		m_ITcnConnection = connection;
		connectBinderPoolService();
	}

	public void setTcnConnection(ITcnConnection connection) {
		m_ITcnConnection = connection;
	}

	public IDriversInterface getIDriversInterface() {
		IDriversInterface mIDriversInterface = null;
		IBinder driveBinder = queryBinder(BinderPool.BINDER_CODE_DRIVE_COMMON);
		if (driveBinder != null) {
			mIDriversInterface = IDriversInterfaceImpl.asInterface(driveBinder);
		}

		return mIDriversInterface;
	}

	// 绑定服务
	public synchronized void connectBinderPoolService() {

		try {

			Intent mIntent = new Intent();
			mIntent.setComponent(new ComponentName("com.tcn.drivers", "com.tcn.drivers.aidl.TcnDrivesService"));

			Log.i(TAG, "connectBinderPoolService() start ");
//		mConnectBinderPoolCountDownLatch = new CountDownLatch(1);
			boolean bRet = m_context.bindService(mIntent, mBinderPoolConnection, BIND_AUTO_CREATE);

			if(!bRet) {
				Intent mIntentHide = new Intent();
				mIntentHide.setAction("com.tcn.drivers.DeviceInterface");//你定义的service的action
				mIntentHide.setPackage("com.tcn.drivers");//这里你需要设置你应用的包名
				bRet = m_context.bindService(mIntentHide, mBinderPoolConnection, BIND_AUTO_CREATE);
			}
			Log.i(TAG, "connectBinderPoolService() end  bRet: "+bRet);
		} catch (Exception e) {
			Log.i( TAG, "connectBinderPoolService() Exception e: "+e);
		}

//		try {
//			mConnectBinderPoolCountDownLatch.await();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//			TcnLog.getInstance().LoggerError("Conmboard", TAG, "connectBinderPoolService()", " e: "+e);
//		}
	}

	private ServiceConnection mBinderPoolConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i(TAG, "onServiceConnected() name: "+name+" service: "+service);

			// 连接上服务后，获取服务里远程提供的Binder mBinderPool对象，它是前面的IBinderPool接口

			try {
				mBinderPool = IBinderPool.Stub.asInterface(service);

				// linkToDeath可以给Binder设置一个死亡代理
//				mBinderPool.asBinder().linkToDeath(mBinderPoolDeathRecipient, 0);
				if (m_ITcnConnection != null) {
					m_ITcnConnection.onServiceConnected(name,service);
				}
			}
//			catch (RemoteException e) {
//				e.printStackTrace();
//				TcnLog.getInstance().LoggerError("Conmboard", TAG, "onServiceConnected()", "RemoteException e: "+e);
//			}
			catch (Exception e) {
				Log.e(TAG, "onServiceConnected() Exception e: "+e);
			}
//			mConnectBinderPoolCountDownLatch.countDown();
		}
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i(TAG, "onServiceDisconnected() name: "+name);
			if (m_ITcnConnection != null) {
				m_ITcnConnection.onServiceDisconnected(name);
			}
		}
	};

	private IBinder.DeathRecipient mBinderPoolDeathRecipient = new IBinder.DeathRecipient() {
		@Override
		public void binderDied() {
			mBinderPool.asBinder().unlinkToDeath(mBinderPoolDeathRecipient, 0);
			mBinderPool = null;
		}
	};

	public IBinder queryBinder(int binderCode) {
		IBinder binder = null;
		try {
			if (mBinderPool != null) {
				binder = mBinderPool.queryBinder(binderCode);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
			Log.e(TAG, "queryBinder()  binderCode: "+binderCode+" RemoteException e: "+e);
		}
		return binder;
	}


	/*private void doWork() {
		BinderPool binderPool = BinderPool.getInsance(MainActivity.this);

		IBinder securityBinder = binderPool.queryBinder(BinderPoolService.BINDER_SECURITY_CENTER);
		mSecurityCenter = SecurityCenterImpl.asInterface(securityBinder);
		try {
			String msg = "hello ffo";
			String password = mSecurityCenter.encrypt(msg);
			String originalPassword  = mSecurityCenter.decrypt(password);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		IBinder computeBinder = binderPool.queryBinder(BinderPoolService.BINDER_COMPUTE);
		mCompute = ComputeImpl.asInterface(computeBinder);
		try {
			int reuslt = mCompute.add(6, 8);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}*/
}
