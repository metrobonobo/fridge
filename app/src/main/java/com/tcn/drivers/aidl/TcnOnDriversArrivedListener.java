package com.tcn.drivers.aidl;

import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tcn.drivers.constants.TcnProtoDef;
import com.tcn.drivers.constants.TcnProtoResultDef;
import com.tcn.drivers.utils.TcnUtility;


public class TcnOnDriversArrivedListener extends IOnDriversArrivedListener.Stub {
	private static final String TAG = "DriversArrivedListener";
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private long lTimeLogDoorWrite = -1;
	private boolean isCanUse = false;
	private boolean isDoorOpen = false;
	private Handler m_sendHandler = null;

	public TcnOnDriversArrivedListener() {

	}

	public TcnOnDriversArrivedListener(Handler handler) {
		m_sendHandler = handler;
	}

	private boolean isMoreTimeThanSecons(int seconds) {
		boolean bRet = false;
		if (Math.abs(System.currentTimeMillis() - lTimeLogDoorWrite) > (seconds * 1000)) {
			bRet = true;
		}
		return bRet;
	}

	@Override
	public void onQueryInfo(int door, int temp1, int temp2, int version, String versionName, String jsonData) throws RemoteException {
		if (isMoreTimeThanSecons(60)) {
			lTimeLogDoorWrite = System.currentTimeMillis();
			Log.i(TAG, "onQueryInfo() door: " + door + " temp1: " +temp1
					+ " temp2: " +temp2 + " version: " +version + " versionName: " +versionName+ " jsonData: " +jsonData);
		}

		try {
			if (!TextUtils.isEmpty(jsonData)) {
				JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();
				if (jsonObject.has("canUse")) {
					isCanUse = jsonObject.get("canUse").getAsBoolean();
				}
			}

			if (isDoorOpen) {

				if (door == 0) {
					isDoorOpen = false;
					sendMessageData(TcnProtoDef.CMD_READ_DOOR_STATUS, TcnProtoResultDef.DOOR_CLOSE, 1, null);
				}
			} else {
				if (door == 1) {    //开门
					isDoorOpen = true;
					sendMessageData(TcnProtoDef.CMD_READ_DOOR_STATUS, TcnProtoResultDef.DOOR_OPEN, 1, null);        //1:代表change
				}
			}

			StringBuffer mYsBoardTemp = new StringBuffer();
			if ((temp1 > -80) && (temp1 < 100)) {
				mYsBoardTemp.append(String.valueOf(temp1));
			}
			if ((temp2 > -80) && (temp2 < 100)) {
				mYsBoardTemp.append("|");
				mYsBoardTemp.append(String.valueOf(temp2));
			}

			sendMessageData(TcnProtoDef.CMD_READ_TEMP,temp1, temp2, mYsBoardTemp.toString());

//			sendReceiveData(TcnProtoDef.CMD_READ_CURRENT_TEMP, temp1, temp2, (String) msg.obj);

		} catch (Exception e) {

		}

	}

	@Override
	public void onQueryMachineInfo(int machineIndex,String jsonData)  throws RemoteException {
		Log.i(TAG,"onQueryMachineInfo machineIndex: " + machineIndex
				+" jsonData: "+jsonData);
		int driveType = -1;
		int countCabInOneBoard = -1;
		String driveVersionName = null;
		try {
			if (!TextUtils.isEmpty(jsonData)) {
				JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();
				if (jsonObject.has("canUse")) {
					isCanUse = jsonObject.get("canUse").getAsBoolean();
				}


				if (jsonObject.has("driveVersionName")) {
					driveVersionName = jsonObject.get("driveVersionName").getAsString();
				}


				if (jsonObject.has("driveType")) {
					driveType = jsonObject.get("driveType").getAsInt();
				}


				if (jsonObject.has("countCabInOneBoard")) {       //一个主板控制多少个柜子
					countCabInOneBoard = jsonObject.get("countCabInOneBoard").getAsInt();
				}


			}
		} catch (Exception e) {
			Log.e(TAG, "onQueryMachineInfo() Exception e: " + e);
		}
		
		sendMessageData(TcnProtoDef.CMD_INITED_DRIVES, driveType,countCabInOneBoard, driveVersionName);
	}

	/*
	status:

		public static final int STATUS_INVALID = -1;
		public static final int STATUS_FREE = 0;
		public static final int STATUS_BUSY = 1;
		public static final int STATUS_WAIT_TAKE_GOODS = 2;

		public static final int STATUS_HEATING = 4;
		public static final int STATUS_HEATING_START = 5;
		public static final int STATUS_HEATING_END = 6;

	 */
	@Override
	public void onQueryMachineStatus(int machineIndex,int status,int errCode, String errMsg,String jsonData) throws RemoteException {
		Log.i(TAG, "onQueryMachineStatus() machineIndex: " + machineIndex + " status: " +status
				+ " errCode: " +errCode+ " errMsg: " +errMsg+ " jsonData: " +jsonData);

		if (!TextUtils.isEmpty(jsonData)) {
			boolean isLockSlotNo = false;
			int iSlotNo = -1;
			try {
				JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();
				if (jsonObject.has("isLockSlotNo")) {
					isLockSlotNo = jsonObject.get("isLockSlotNo").getAsBoolean();
				}
				if (jsonObject.has("slotNo")) {
					iSlotNo = jsonObject.get("slotNo").getAsInt();
				}

				if (isLockSlotNo && (iSlotNo > 0)) {
					sendMessageData(TcnProtoDef.CMD_SHIP_SLOT_ERRCODE_UPDATE, iSlotNo, errCode, null);
				}

			} catch (Exception e) {
				Log.e(TAG, "onQueryMachineStatus() Exception e: " + e);

			}
		}

		sendMessageData(TcnProtoDef.CMD_QUERY_STATUS_DRIVES,getStatus(status),errCode,errMsg);
//		EventBus.getDefault().post(new MessageFromDrive(TcnDrivesConstant.BOARD_SPRING,m_currentSendMsg.getIndex(), TcnDriveCmdType.CMD_QUERY_SLOTNO_EXISTS,iErrCode,-1,-1,-1,false,
//				null, null,null,null,null,null));
	}

	@Override
	public void onQueryWorkStatus(int machineIndex,int type,String jsonData)  throws RemoteException {
		Log.i(TAG,"onQueryWorkStatus machineIndex: " + machineIndex
				+" type: "+type+" jsonData: "+jsonData);
		String dataHex = null;
		String data = null;
		try {
			JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();
			if (jsonObject.has("dataHex")) {
				dataHex = jsonObject.get("dataHex").getAsString();

				data = new String(TcnUtility.hexStringToBytes(dataHex));
			}
			sendMessageData(TcnProtoDef.CMD_QUERY_WORK_STATUS,type,-1,data);
		} catch (Exception e) {
			Log.e(TAG, "onQueryWorkStatus() Exception e: " + e);

		}
	}

	/*
		public static final int SHIP_STATUS_INVALID          = -1;
		public static final int SHIP_STATUS_SHIPING          = 1;
		public static final int SHIP_STATUS_SUCCESS          = 2;
		public static final int SHIP_STATUS_FAIL             = 3;
	 */
	@Override
	public void onShip(int slotNo, int shipStatus, int errCode,String shipMethod, String amount, String tradeNo, String errMsg, String jsonData) throws RemoteException {
		Log.i(TAG, "onShip() slotNo: " + slotNo + " shipStatus: " +shipStatus+ " errCode: " +errCode
				+ " shipMethod: " +shipMethod+ " amount: " +amount+ " tradeNo: " +tradeNo+ " errMsg: " +errMsg+ " jsonData: " +jsonData);
		handShipData(slotNo,shipStatus,errCode,shipMethod,amount,tradeNo,errMsg,jsonData);
	}

	@Override
	public void onShipTest(int slotNo, int shipStatus, int errCode, String errMsg, String jsonData) throws RemoteException {
		Log.i(TAG, "onShipTest() slotNo: " + slotNo + " shipStatus: " +shipStatus + " errCode: " +errCode
				+ " errMsg: " +errMsg+ " jsonData: " +jsonData);
		handShipTestData(slotNo,shipStatus,errCode,errMsg,jsonData);
	}

	@Override
	public void onSelectSlotNo(int slotNo, String jsonData) throws RemoteException {
		Log.i(TAG, "onSelectSlotNo() slotNo: " + slotNo + " jsonData: " +jsonData);
		handSelectData(slotNo,jsonData);
	}

	@Override
	public void onQueryParameters(int machineIndex,int address,int status, int data1,int data2,String jsonData) throws RemoteException {
		Log.i(TAG, "onQueryParameters() machineIndex: " + machineIndex + " address: " +address
				+ " data1: " +data1 + " data2: " +data2 + " jsonData: " +jsonData);
		//msg.arg1:查询地址    msg.arg2：查询所得的值
		sendMessageData(TcnProtoDef.CMD_QUERY_PARAMETERS, address, data2, null);
	}

	@Override
	public void onSetParameters(int machineIndex,int address,int status, int data1,int data2,int data3,int data4,String jsonData) throws RemoteException {
		Log.i(TAG, "onSetParameters() machineIndex: " + machineIndex + " address: " +address
				+ " data1: " +data1 + " data2: " +data2 + " data3: " +data3+ " data4: " +data4+ " jsonData: " +jsonData);
		//msg.arg1:地址  msg.arg2:故障代码 msg.obj:设置之后返回的值
		String errMsg = null;
		try {
			JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();
			if (jsonObject.has("errMsg")) {
				errMsg = jsonObject.get("errMsg").getAsString();
			}

		} catch (Exception e) {

		}
		sendMessageData(TcnProtoDef.CMD_SET_PARAMETERS_DRIVES,address, data1, errMsg);
	}

	@Override
	public void onQueryParametersOther(int machineIndex, int address, int status, int data1, int data2, String jsonData) throws RemoteException {

	}

	@Override
	public void onSetParametersOther(int machineIndex, int address, int status, int data1, int data2, int data3, int data4, int num, String jsonData) throws RemoteException {

	}

	@Override
	public void onFactoryReset(int machineIndex, int status, String jsonData) throws RemoteException {

	}

	/*
		status:
			public static final int ACTION_STATUS_INVALID          = -1;
			public static final int ACTION_STATUS_DOING            = 1;
			public static final int ACTION_STATUS_SUCCESS          = 2;
			public static final int ACTION_STATUS_FAIL             = 3;
		 */
	@Override
	public void onActionDo(int machineIndex,int status, int actionType, int data1, int data2, int errCode, String errMsg, String jsonData) throws RemoteException {
		Log.i(TAG, "onActionDo() machineIndex: " + machineIndex + " status: " +status
				+ " actionType: " +actionType + " data1: " +data1 + " data2: " +data2+ " jsonData: " +jsonData);
		sendMessageData(TcnProtoDef.CMD_ACTION_DO_DRIVES, getActionStatusStartEnd(status), actionType, errMsg);

		if (1 == status) {    //开始动作
			sendMessageData(TcnProtoDef.CMD_ACTION_DRIVES_STATUS_DOING, actionType, data1, errMsg);
		} else if (2 == status) {   //执行完成
			sendMessageData(TcnProtoDef.CMD_ACTION_DRIVES_STATUS_SUCCESS, actionType, data1, errMsg);
		} else if (3 == status) {   //执行失败
			sendMessageData(TcnProtoDef.CMD_ACTION_DRIVES_STATUS_FAIL, actionType, data1, errMsg);
		} else {

		}

	}

	@Override
	public void onActionDoOther(int machineIndex, int status, int actionType, int data1, int data2, int errCode, String errMsg, String jsonData) throws RemoteException {

	}

	@Override
	public void onOpenCool(int machineIndex, int status, int data, String jsonData) throws RemoteException {

	}

	@Override
	public void onOpenHeat(int machineIndex, int status, int data, String jsonData) throws RemoteException {

	}

	@Override
	public void onCloseCoolAndHeat(int driveIndex, int status, int data, String jsonData) throws RemoteException {

	}

	@Override
	public void onBackHome(int driveIndex, int status, int data, String jsonData) throws RemoteException {

	}

	@Override
	public void onClapboardOpen(int driveIndex, int status, int data, String jsonData) throws RemoteException {

	}

	@Override
	public void onClapboardClose(int driveIndex, int status, int data, String jsonData) throws RemoteException {

	}

	@Override
	public void onDetectShip(int driveIndex, int status, int data, String jsonData) throws RemoteException {

	}

	@Override
	public void onDetectLight(int driveIndex, int status, int data, String jsonData) throws RemoteException {

	}

	@Override
	public void onTakeGoodsDoorOpen(int driveIndex, int status, int data, String jsonData) throws RemoteException {

	}

	@Override
	public void onTakeGoodsDoorClose(int driveIndex, int status, int data, String jsonData) throws RemoteException {

	}

	@Override
	public void onMicovenHeatOpen(int driveIndex, int status, int data, String jsonData) throws RemoteException {

	}

	@Override
	public void onMicovenHeatClose(int driveIndex, int status, int data, String jsonData) throws RemoteException {

	}

	@Override
	public void onLightOn(int driveIndex, int status, int data, String jsonData) throws RemoteException {

	}

	@Override
	public void onLightOff(int driveIndex, int status, int data, String jsonData) throws RemoteException {

	}

	@Override
	public void onDoorOpen(int driveIndex, int status, int data, String jsonData) throws RemoteException {

	}

	@Override
	public void onDoorClose(int driveIndex, int status, int data, String jsonData) throws RemoteException {

	}

	/*
	status 机器状态
		0 : 空闲    1 : 机器忙（动作中）   2:等待取走商品（取货口有货）
	 */
	@Override
	public void onCleanDriveFaults(int machineIndex,int status, int errCode, String errMsg,String jsonData) throws RemoteException {
		Log.i(TAG, "onCleanDriveFaults() machineIndex: " + machineIndex + " jsonData: " +jsonData);


		sendMessageData(TcnProtoDef.CMD_CLEAN_FAULTS_DRIVES, getStatus(status), errCode, errMsg);
	}

	@Override
	public void onUpdate(int machineIndex,int type,int data1, int data2,String jsonData) throws RemoteException {
		Log.i(TAG,"onUpdate machineIndex: " + machineIndex
				+" type: "+type+" data1: "+data1+" data2: "+data2+" jsonData: "+jsonData);

	}

	@Override
	public void onUploadData(int machineIndex,int type, String jsonData) throws RemoteException {
		Log.i(TAG, "onUploadData() machineIndex: " + machineIndex + " type: " +type
				+ " jsonData: " +jsonData);
		try {
			int slotNo = -1;
			int errCode = -1;
			boolean finish = false;
			JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();
			if (jsonObject.has("slotNo")) {
				slotNo = jsonObject.get("slotNo").getAsInt();
			}
			if (jsonObject.has("errCode")) {
				errCode = jsonObject.get("errCode").getAsInt();
			}
			if (jsonObject.has("finish")) {
				finish = jsonObject.get("finish").getAsBoolean();
			}

			if (slotNo > 0) {
				sendMessageData(TcnProtoDef.COMMAND_SLOTNO_INFO, slotNo, errCode, finish);
			}


		} catch (Exception e) {

		}
	}

	public boolean isCanUse() {
		return isCanUse;
	}

	private void sendMessageData(int what, int arg1, int arg2, Object data) {
		if (null == m_sendHandler) {
			return;
		}
		Message message = m_sendHandler.obtainMessage();
		message.what = what;
		message.arg1 = arg1;
		message.arg2 = arg2;
		message.obj = data;
		m_sendHandler.sendMessage(message);
	}

	private int getStatus(int status) {
		int iRet = TcnProtoResultDef.STATUS_INVALID;
		if (0 == status) {
			iRet = TcnProtoResultDef.STATUS_FREE;
		} else if (1 == status) {
			iRet = TcnProtoResultDef.STATUS_BUSY;
		} else if (2 == status) {
			iRet = TcnProtoResultDef.STATUS_WAIT_TAKE_GOODS;
		} else {

		}
		return iRet;
	}

	private void handSelectData(int slotNo, String jsonData) {
		boolean queryUsable = false;
		int errCode = -1;
		String result = null;
		String errMsg = null;
		try {
			JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();
			if (jsonObject.has("errMsg")) {
				errMsg = jsonObject.get("errMsg").getAsString();
			}
			if (jsonObject.has("queryUsable")) {
				queryUsable = jsonObject.get("queryUsable").getAsBoolean();
			}
			if (jsonObject.has("result")) {
				result = jsonObject.get("result").getAsString();
			}
			if (jsonObject.has("errCode")) {
				errCode = jsonObject.get("errCode").getAsInt();
			}
		} catch (Exception e) {

		}
		if (queryUsable) {
			if (SUCCESS.equals(result)) {
				sendMessageData(TcnProtoDef.COMMAND_SLOTNO_QUERY_DRIVES_SUCCESS, slotNo, errCode, errMsg);
			} else {
				sendMessageData(TcnProtoDef.COMMAND_SLOTNO_QUERY_DRIVES_FAIL, slotNo, errCode, errMsg);
			}
		} else {
			if (SUCCESS.equals(result)) {
				sendMessageData(TcnProtoDef.COMMAND_SELECT_SLOTNO, slotNo, errCode, errMsg);
			} else {
				sendMessageData(TcnProtoDef.COMMAND_SELECT_FAIL_DRIVES, slotNo, errCode, errMsg);
			}
		}
	}

	private void handShipData(int slotNo, int shipStatus, int errCode,String shipMethod, String amount, String tradeNo, String errMsg, String jsonData) {
//		MsgToSend msgToSend = new MsgToSend();
//		msgToSend.setM_iSlotNo(slotNo);
//		msgToSend.setM_strPayMethod(shipMethod);
//		msgToSend.setM_strAmount(amount);
//		msgToSend.setM_strTradeNo(tradeNo);
//		msgToSend.setM_iErrCode(errCode);

		int iShipStatus = getShipStatus(shipStatus);

//		if (iShipStatus == TcnProtoResultDef.SHIP_FAIL) {
//			boolean isLockSlotNo = false;
//			try {
//				JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();
//				if (jsonObject.has("isLockSlotNo")) {
//					isLockSlotNo = jsonObject.get("isLockSlotNo").getAsBoolean();
//				}
//			} catch (Exception e) {
//
//			}
//			if (isLockSlotNo) {
//				sendMessageData(TcnProtoDef.CMD_SHIP_SLOT_ERRCODE_UPDATE, slotNo, errCode, null);
//			}
//		}

		sendMessageData(iShipStatus, slotNo, errCode, errMsg);
	}

	private void handShipTestData(int slotNo, int shipStatus, int errCode, String errMsg, String jsonData) {
		int iShipStatus = getShipStatus(shipStatus);
		if (TcnProtoResultDef.SHIP_SHIPING == iShipStatus) {
			sendMessageData(TcnProtoDef.CMD_TEST_SLOT, slotNo, errCode, TcnProtoResultDef.SHIP_SHIPING);
		} else if (TcnProtoResultDef.SHIP_SUCCESS == iShipStatus) {
			if (errCode > 0) {
				// sendReceiveData(TcnProtoDef.CMD_SHIP_SLOT_ERRCODE_UPDATE,slotNo,errCode,null);
			}
			sendMessageData(TcnProtoDef.CMD_TEST_SLOT, slotNo, errCode, TcnProtoResultDef.SHIP_SUCCESS);
		} else if (TcnProtoResultDef.SHIP_FAIL == iShipStatus) {
			if (errCode > 0) {
				// sendReceiveData(TcnProtoDef.CMD_SHIP_SLOT_ERRCODE_UPDATE,slotNo,errCode,null);
			}
			sendMessageData(TcnProtoDef.CMD_TEST_SLOT, slotNo, errCode, TcnProtoResultDef.SHIP_FAIL);
		} else {

		}
	}

//	public int getPayShipMedthod(String payMethod) {
//		int sendWhat = -1;
//		int iPayMethod = -1;
//		if (TcnUtility.isDigital(payMethod)) {
//			iPayMethod = Integer.valueOf(payMethod);
//			if (Integer.valueOf(PayMethod.PAYMETHED_CASH) == iPayMethod) {
//				sendWhat = TcnProtoDef.COMMAND_SHIPMENT_CASHPAY;
//			} else if (Integer.valueOf(PayMethod.PAYMETHED_MDB_CARD) == iPayMethod) {
//				sendWhat = TcnProtoDef.COMMAND_SHIPMENT_BANKCARD_ONE;
//			} else if (Integer.valueOf(PayMethod.PAYMETHED_TCNICCARD) == iPayMethod) {
//				sendWhat = TcnProtoDef.COMMAND_SHIPMENT_TCNCARD_ONLINE;
//			} else if (Integer.valueOf(PayMethod.PAYMETHED_BANKPOSCARD) == iPayMethod) {
//				sendWhat = TcnProtoDef.COMMAND_SHIPMENT_BANKCARD_TWO;
//			} else if (Integer.valueOf(PayMethod.PAYMETHED_WECHAT) == iPayMethod) {
//				sendWhat = TcnProtoDef.COMMAND_SHIPMENT_WECHATPAY;
//			} else if (Integer.valueOf(PayMethod.PAYMETHED_ALI) == iPayMethod) {
//				sendWhat = TcnProtoDef.COMMAND_SHIPMENT_ALIPAY;
//			} else if (Integer.valueOf(PayMethod.PAYMETHED_GIFTS) == iPayMethod) {
//				sendWhat = TcnProtoDef.COMMAND_SHIPMENT_GIFTS;
//			} else if (Integer.valueOf(PayMethod.PAYMETHED_REMOUT) == iPayMethod) {
//				sendWhat = TcnProtoDef.COMMAND_SHIPMENT_REMOTE;
//			} else if (Integer.valueOf(PayMethod.PAYMETHED_VERIFY) == iPayMethod) {
//				sendWhat = TcnProtoDef.COMMAND_SHIPMENT_VERIFY;
//			} else {
//				sendWhat = TcnProtoDef.COMMAND_SHIPMENT_OTHER_PAY;
//			}
//		} else {
//			sendWhat = TcnProtoDef.COMMAND_SHIPMENT_OTHER_PAY;
//		}
//
//		return sendWhat;
//	}

	private int getShipStatus(int shipStatus) {
		int iStatus = -1;
		if (4 == shipStatus) {
			iStatus = TcnProtoResultDef.SHIP_SHIPING;
		} else if (5 == shipStatus) {
			iStatus = TcnProtoResultDef.SHIP_SUCCESS;
		} else if (3 == shipStatus) {
			iStatus = TcnProtoResultDef.SHIP_FAIL;
		} else {

		}
		return iStatus;
	}

	private int getActionStatusStartEnd(int status) {
		int iRetStatus = TcnProtoResultDef.DO_NONE;
		if (1 == status) {
			iRetStatus = TcnProtoResultDef.DO_START;
		} else if (2 == status) {           //success
			iRetStatus = TcnProtoResultDef.DO_END;
		} else if (3 == status) {    //Fail
			iRetStatus = TcnProtoResultDef.DO_END;
		}
		else {

		}

		return iRetStatus;
	}

}
