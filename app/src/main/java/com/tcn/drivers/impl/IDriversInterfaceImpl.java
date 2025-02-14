package com.tcn.drivers.impl;

import android.os.RemoteException;
import android.util.Log;

import com.tcn.drivers.aidl.IDriversInterface;
import com.tcn.drivers.aidl.IOnDriversArrivedListener;


public class IDriversInterfaceImpl extends IDriversInterface.Stub {
	private static final String TAG = "IDriversInterfaceImpl";


	@Override
	public void reqAllInfo() throws RemoteException {
		Log.i(TAG, "reqAllInfo()");

	}

	@Override
	public void reqQueryInfo() throws RemoteException {
		Log.i(TAG, "reqQueryInfo() currentThread: "+Thread.currentThread());

	}

	@Override
	public void reqQueryMachineInfo(int machineIndex) throws RemoteException {
		Log.i(TAG, "reqQueryMachineInfo()");

	}

	@Override
	public void reqQueryMachineStatus(int machineIndex) throws RemoteException {
		Log.i(TAG, "reqQueryMachineStatus() machineIndex: "+machineIndex);

	}

	@Override
	public void reqQueryWorkStatus(int machineIndex,int type, String jsonData) throws RemoteException {
		Log.i(TAG, "reqQueryWorkStatus() ");

	}

	@Override
	public void registerListener(IOnDriversArrivedListener listener) throws RemoteException {

	}

	@Override
	public void unregisterListener(IOnDriversArrivedListener listener) throws RemoteException {

	}

	@Override
	public void ship(int slotNo, int heartTimeSeconds,String shipMethod, String amount, String tradeNo, String jsonData) throws RemoteException {
		Log.i(TAG, "ship() slotNo: " + slotNo + " shipMethod: " +shipMethod
				+ " amount: " +amount+ " tradeNo: " +tradeNo+ " jsonData: " +jsonData);

	}

	@Override
	public void shipTest(int startSlotNo, int endSlotNo, int heartTimeSeconds, String jsonData) throws RemoteException {
		Log.i(TAG, "shipTest() startSlotNo: " + startSlotNo + " endSlotNo: " +endSlotNo
				+ " jsonData: " +jsonData);

	}

	@Override
	public void reqSelectSlotNo(int slotNo, String jsonData) throws RemoteException {
		Log.i(TAG, "reqSelectSlotNo() slotNo: " + slotNo + " jsonData: " +jsonData);

	}

	@Override
	public void reqQueryParameters(int machineIndex,int address, int data1, int data2, String jsonData) throws RemoteException {

	}

	@Override
	public void reqSetParameters(int machineIndex,int address, int data1,int data2,int data3,int data4,int num,String jsonData) throws RemoteException {
		Log.i(TAG, "reqSetParameters() machineIndex: " + machineIndex + " address: " +address
				+ " data1: " +data1+ " data2: " +data2+ " data3: " +data3+ " data4: " +data4+" num: "+num+ " jsonData: " +jsonData);

	}

	@Override
	public void reqQueryParametersOther(int driveIndex, int address, int chn, String jsonData) throws RemoteException {

	}

	@Override
	public void reqSetParameterssOther(int driveIndex, int addr, int parameter1, int parameter2, int parameter3, int parameter4, int num, String jsonData) throws RemoteException {

	}

	@Override
	public void reqFactoryReset(int driveIndex, int data, String jsonData) throws RemoteException {

	}

	@Override
	public void reqActionDo(int machineIndex,int actionType, int data1, int data2, int data3, int data4, String jsonData) throws RemoteException {
		Log.i(TAG, "reqActionDo() machineIndex: " + machineIndex + " actionType: " +actionType
				+ " data1: " +data1+ " data2: " +data2+ " data3: " +data3+ " data4: " +data4+ " jsonData: " +jsonData);

	}

	@Override
	public void reqActionDoOther(int driveIndex, int actionType, int data1, int data2, int data3, int data4, String jsonData) throws RemoteException {

	}

	@Override
	public void reqOpenCool(int driveIndex, int data1, int data2, int data3, int data4, String jsonData) throws RemoteException {

	}

	@Override
	public void reqOpenHeat(int driveIndex, int data1, int data2, int data3, int data4, String jsonData) throws RemoteException {

	}

	@Override
	public void reqCloseCoolAndHeat(int driveIndex, int data1, int data2, int data3, int data4, String jsonData) throws RemoteException {

	}

	@Override
	public void reqBackHome(int driveIndex, int data1, int data2, int data3, int data4, String jsonData) throws RemoteException {

	}

	@Override
	public void reqClapboardOpen(int driveIndex, int data, String jsonData) throws RemoteException {

	}

	@Override
	public void reqClapboardClose(int driveIndex, int data, String jsonData) throws RemoteException {

	}

	@Override
	public void reqDetectShip(int driveIndex, int data1, int data2, int data3, int data4, String jsonData) throws RemoteException {

	}

	@Override
	public void reqDetectLight(int driveIndex, int data1, int data2, int data3, int data4, String jsonData) throws RemoteException {

	}

	@Override
	public void reqTakeGoodsDoorOpen(int driveIndex, int data1, int data2, int data3, int data4, String jsonData) throws RemoteException {

	}

	@Override
	public void reqTakeGoodsDoorClose(int driveIndex, int data1, int data2, int data3, int data4, String jsonData) throws RemoteException {

	}

	@Override
	public void reqMicovenHeatOpen(int driveIndex, int data1, int data2, int data3, int data4, String jsonData) throws RemoteException {

	}

	@Override
	public void reqMicovenHeatClose(int driveIndex, int data1, int data2, int data3, int data4, String jsonData) throws RemoteException {

	}

	@Override
	public void reqLightOn(int driveIndex, int data1, int data2, int data3, int data4, String jsonData) throws RemoteException {

	}

	@Override
	public void reqLightOff(int driveIndex, int data1, int data2, int data3, int data4, String jsonData) throws RemoteException {

	}

	@Override
	public void reqDoorOpen(int driveIndex, int data1, int data2, int data3, int data4, String jsonData) throws RemoteException {

	}

	@Override
	public void reqDoorClose(int driveIndex, int data1, int data2, int data3, int data4, String jsonData) throws RemoteException {

	}

	@Override
	public void reqCleanDriveFaults(int machineIndex,String jsonData) throws RemoteException {

	}

	@Override
	public void reqUploadInfo(int machineIndex,int type) throws RemoteException {
		Log.i(TAG, "reqUploadInfo() machineIndex: " + machineIndex + " type: " +type);

	}

	@Override
	public void reqUpdate(int machineIndex,int type, int data1, int data2, String jsonData) throws RemoteException {
		Log.i(TAG, "reqUpdate() machineIndex: " + machineIndex + " type: " +type
				+ " data1: " +data1+ " data2: " +data2+ " jsonData: " +jsonData);

	}

	@Override
	public int getShipStatus(String tradeNo) throws RemoteException {
		return 0;
	}

	@Override
	public int getInfoStatus(int type) throws RemoteException {
		return 0;
	}

	@Override
	public String getInfoStatusJson(int type) throws RemoteException {
		return null;
	}

	@Override
	public void setConfigJsonData(String jsonData) throws RemoteException {

	}

	@Override
	public String getConfigJsonData() throws RemoteException {
		return null;
	}
}
