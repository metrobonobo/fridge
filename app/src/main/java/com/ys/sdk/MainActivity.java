package com.ys.sdk;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.tcn.drivers.constants.TcnProtoDef;
import com.tcn.drivers.constants.TcnProtoResultDef;
import com.tcn.drivers.control.TcnDrivesAidlControl;

public class MainActivity extends AppCompatActivity {
	private TCNCommunicationHandler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		handler =  new TCNCommunicationHandler();

		//初始化AIDL与驱动apk通讯
		TcnDrivesAidlControl.getInstance().init(handler, this);
		TcnDrivesAidlControl.getInstance().isCanUse();
	}

	private void actionDO(){
		//动作命令列表
		TcnDrivesAidlControl.getInstance().isCanUse(); //判断是否与驱动apk 通讯成功

		TcnDrivesAidlControl.getInstance().reqQueryMachineInfo(-1);//查询驱动板信息命令

		TcnDrivesAidlControl.getInstance().reqQueryMachineStatus(-1); //查询驱动板故障


		//查询机器运行状态(machineIndex -1：主柜， 1：副柜1， type：查询参数id)
		// 查看LIFT_QUERY_WORK_STATUS_STAND列表
		TcnDrivesAidlControl.getInstance().reqQueryWorkStatus(-1, 50);
//		50 一次性查询重力柜所有层数，及温度参数


		TcnDrivesAidlControl.getInstance().reqCleanDriveFaults(-1); //清除机器故障


		// 出货指令（slotNo货道号  heartTimeSeconds 加热时长, shipMethod支付方式,  amount价格 , tradeNo订单号, String jsonData）
		TcnDrivesAidlControl.getInstance().ship(1,2,"支付方式","价格","订单号", null);



		//查询机器参数命令(machineIndex -1：主柜， 1：副柜1， address：查询参数地址id）
		// 查看列表 LIFT_QUERY_PARAM_STAND
		TcnDrivesAidlControl.getInstance().reqQueryParameters(-1,80, -1, -1, null);


		//设置机器参数命令(machineIndex -1：主柜， 1：副柜1， address：查询参数地址id， data1: 设置的值）
		// 查看列表 LIFT_FLOOR_DATA_STAND
		TcnDrivesAidlControl.getInstance().reqSetParameters(-1,80, 1, -1, -1, -1, null);



		//动作执行命令 (machineIndex -1：主柜， 1：副柜1， actionType：查询参数地址id,  mJsonObject.toString()执行动作的值)
		//查看列表
		JsonObject mJsonObject = new JsonObject();
		mJsonObject.addProperty("actionValueHex", "090000000");
		TcnDrivesAidlControl.getInstance().reqActionDo(-1, 80, -1, -1, -1, -1, mJsonObject.toString());


		/*
		 * type:0 更新驱动板程序
		 * BoardId： data1
		 * SlaveBoardId：data2　　从1开始，第一块从驱动板
		 */
		TcnDrivesAidlControl.getInstance().reqUpdate(-1, 0, 0, 1, null); //更新驱动板命令
	}


	//驱动apk 返回消息
	private class TCNCommunicationHandler extends Handler {
		@Override
		public void handleMessage(@NonNull Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
				case TcnProtoDef.CMD_READ_DOOR_STATUS: //机器门控开关状态
					 if (msg.arg1 == 1){
					 	//开门
					 } else if (msg.arg1 == 0){
					 	//关门
					 }
					break;

				case TcnProtoDef.CMD_READ_TEMP: //查询温度返回
					// msg.arg1 温度1
					// msg.arg2 温度2
					// msg.obj.toString(); //温度1|温度2
					break;

				case TcnProtoDef.CMD_INITED_DRIVES: //查询驱动板信息
					// msg.arg1 驱动类型
					// msg.arg2 个主板控制多少个柜子
					// msg.obj.toString(); 版本号
					break;

				case TcnProtoResultDef.SHIP_SUCCESS: //开门成功
					// msg.arg1 货道号
					// msg.arg2 错误代码
					// msg.obj.toString(); 错误信息
					break;

				case TcnProtoResultDef.SHIP_FAIL: //开门失败
					// msg.arg1 货道号
					// msg.arg2 错误代码
					// msg.obj.toString(); 错误信息
					break;


				case TcnProtoDef.CMD_QUERY_STATUS_DRIVES:  //查询驱动板状态
					// msg.arg1 = 0(机器空闲); msg.arg1 = 1(机器忙); msg.arg2 = 2(等待客户取货)
					//msg.arg2 机器返回的错误代码
					//msg.obj.toString(); 错误信息
					break;

				case TcnProtoDef.CMD_QUERY_WORK_STATUS: //查询机器运行状态
					// msg.arg1 查询命令的地址id  50
					// msg.obj.toString() 机器返回的所有层重量及其他参数类似与参数查询220
					//
					break;

				case TcnProtoDef.CMD_QUERY_PARAMETERS: //查询参数返回结果
					// msg.arg1:查询地址
					// msg.arg2：查询所得的值
					break;

				case TcnProtoDef.CMD_SET_PARAMETERS_DRIVES: //设置参数返回结果
					// msg.arg1:地址
					// msg.arg2:故障代码
					// (String) msg.obj:设置之后返回的值
					break;

				case TcnProtoDef.CMD_ACTION_DO_DRIVES: //机器动作执行状态
					// msg.arg1 = 0(动作开始); msg.arg1 = 1(动作结束);
					//msg.obj.toString(); 动作返回信息
					break;

				case TcnProtoDef.CMD_CLEAN_FAULTS_DRIVES: //清理故障状态
					// msg.arg1 = 0(机器空闲); msg.arg1 = 1(机器忙); msg.arg1 = 2(等待取货);
					// msg.arg2:故障代码
					// (String) msg.obj:故障信息
					break;

			}
		}
	}

	public static String[] LIFT_QUERY_PARAM_STAND = null; //查询参数（设置参数）
	public static String[] LIFT_FLOOR_DATA_STAND = null;  //执行动作命令列表
	public static String[] LIFT_QUERY_WORK_STATUS_STAND = null; //机器各个版本号列表查询命令

	private void showList(){
		LIFT_QUERY_PARAM_STAND = new String[] {
				getString(R.string.background_SX_cx_param_1),getString(R.string.background_SX_cx_param_2),getString(R.string.background_SX_cx_param_3),
				getString(R.string.background_SX_cx_param_4),getString(R.string.background_SX_cx_param_5),getString(R.string.background_SX_cx_param_6),
				getString(R.string.background_SX_cx_param_7),getString(R.string.background_SX_cx_param_8),getString(R.string.background_SX_cx_param_9),
				getString(R.string.background_SX_cx_param_10),getString(R.string.background_SX_cx_param_11),getString(R.string.background_SX_cx_param_12),
				getString(R.string.background_SX_cx_param_13),getString(R.string.background_SX_cx_param_14),getString(R.string.background_SX_cx_param_15),
				getString(R.string.background_SX_cx_param_16),getString(R.string.background_SX_cx_param_17),getString(R.string.background_SX_cx_param_18),
				getString(R.string.background_SX_cx_param_19),getString(R.string.background_SX_cx_param_20),getString(R.string.background_SX_cx_param_21),
				getString(R.string.background_SX_cx_param_22),getString(R.string.background_SX_cx_param_23),getString(R.string.background_SX_cx_param_24),
				getString(R.string.background_SX_cx_param_25),getString(R.string.background_SX_cx_param_26),getString(R.string.background_SX_cx_param_27),
				getString(R.string.background_SX_cx_param_28),getString(R.string.background_SX_cx_param_29),getString(R.string.background_SX_cx_param_30),
				getString(R.string.background_SX_cx_param_31),getString(R.string.background_SX_cx_param_32),getString(R.string.background_SX_cx_param_33),
				getString(R.string.background_SX_cx_param_34),getString(R.string.background_SX_cx_param_35),getString(R.string.background_SX_cx_param_36),
				getString(R.string.background_SX_cx_param_37),getString(R.string.background_SX_cx_param_38),getString(R.string.background_SX_cx_param_39),
				getString(R.string.background_SX_cx_param_40),getString(R.string.background_SX_cx_param_41),getString(R.string.background_SX_cx_param_42),
				getString(R.string.background_SX_cx_param_43),getString(R.string.background_SX_cx_param_44),getString(R.string.background_SX_cx_param_45),
				getString(R.string.background_SX_cx_param_46),
				getString(R.string.background_SX_fg_cx_param_1), getString(R.string.background_SX_fg_cx_param_2), getString(R.string.background_SX_fg_cx_param_3),
				getString(R.string.background_SX_fg_cx_param_4), getString(R.string.background_SX_fg_cx_param_5), getString(R.string.background_SX_fg_cx_param_6),
				getString(R.string.background_SX_fg_cx_param_7), getString(R.string.background_SX_fg_cx_param_8), getString(R.string.background_SX_fg_cx_param_9),
				getString(R.string.background_SX_fg_cx_param_10), getString(R.string.background_SX_fg_cx_param_11), getString(R.string.background_SX_fg_cx_param_12),
				getString(R.string.background_SX_fg_cx_param_13), getString(R.string.background_SX_fg_cx_param_14), getString(R.string.background_SX_fg_cx_param_15),
				getString(R.string.background_SX_fg_cx_param_16), getString(R.string.background_SX_fg_cx_param_17), getString(R.string.background_SX_fg_cx_param_18),
				getString(R.string.background_SX_fg_cx_param_19), getString(R.string.background_SX_fg_cx_param_20), getString(R.string.background_SX_fg_cx_param_21),
				getString(R.string.background_SX_fg_cx_param_22), getString(R.string.background_SX_fg_cx_param_23), getString(R.string.background_SX_fg_cx_param_24),
				getString(R.string.background_SX_fg_cx_param_25), getString(R.string.background_SX_fg_cx_param_26), getString(R.string.background_SX_fg_cx_param_27),
				getString(R.string.background_SX_fg_cx_param_28), getString(R.string.background_SX_fg_cx_param_29), getString(R.string.background_SX_fg_cx_param_30),
				getString(R.string.background_SX_fg_cx_param_31), getString(R.string.background_SX_fg_cx_param_32), getString(R.string.background_SX_fg_cx_param_33),
				getString(R.string.background_SX_fg_cx_param_34), getString(R.string.background_SX_fg_cx_param_35), getString(R.string.background_SX_fg_cx_param_36),
				getString(R.string.background_SX_fg_cx_param_37), getString(R.string.background_SX_fg_cx_param_38), getString(R.string.background_SX_fg_cx_param_39),
				getString(R.string.background_SX_fg_cx_param_40), getString(R.string.background_SX_fg_cx_param_41), getString(R.string.background_SX_fg_cx_param_42),
				getString(R.string.background_SX_fg_cx_param_43), getString(R.string.background_SX_fg_cx_param_44), getString(R.string.background_SX_fg_cx_param_45),
				getString(R.string.background_SX_fg_cx_param_46)
		};

		LIFT_FLOOR_DATA_STAND = new String[] {
				getString(R.string.background_SX_zx_param_1),getString(R.string.background_SX_zx_param_2),getString(R.string.background_SX_zx_param_3),
				getString(R.string.background_SX_zx_param_4),getString(R.string.background_SX_zx_param_5),getString(R.string.background_SX_zx_param_6),
				getString(R.string.background_SX_zx_param_7),getString(R.string.background_SX_zx_param_8),getString(R.string.background_SX_zx_param_9),
				getString(R.string.background_SX_zx_param_10),getString(R.string.background_SX_zx_param_11),getString(R.string.background_SX_zx_param_12),
				getString(R.string.background_SX_zx_param_13),getString(R.string.background_SX_zx_param_14),getString(R.string.background_SX_zx_param_15),
				getString(R.string.background_SX_zx_param_16),getString(R.string.background_SX_zx_param_17),getString(R.string.background_SX_zx_param_18),
				getString(R.string.background_SX_zx_param_19),getString(R.string.background_SX_zx_param_20),getString(R.string.background_SX_zx_param_21),
				getString(R.string.background_SX_zx_param_22),getString(R.string.background_SX_zx_param_23),getString(R.string.background_SX_zx_param_24),
				getString(R.string.background_SX_zx_param_25),getString(R.string.background_SX_zx_param_26),getString(R.string.background_SX_zx_param_27),
				getString(R.string.background_SX_zx_param_28),getString(R.string.background_SX_zx_param_29),getString(R.string.background_SX_zx_param_30),
				getString(R.string.background_SX_zx_param_31),getString(R.string.background_SX_zx_param_32),getString(R.string.background_SX_zx_param_33),
				getString(R.string.background_SX_zx_param_34),getString(R.string.background_SX_zx_param_35),getString(R.string.background_SX_zx_param_36),
				getString(R.string.background_SX_zx_param_37),getString(R.string.background_SX_zx_param_38),getString(R.string.background_SX_zx_param_39),
				getString(R.string.background_SX_zx_param_40),getString(R.string.background_SX_zx_param_41),getString(R.string.background_SX_zx_param_42),
				getString(R.string.background_SX_zx_param_43),getString(R.string.background_SX_zx_param_44),getString(R.string.background_SX_zx_param_45),
				getString(R.string.background_SX_zx_param_46),getString(R.string.background_SX_zx_param_47),getString(R.string.background_SX_zx_param_48),
				getString(R.string.background_SX_zx_param_49),getString(R.string.background_SX_zx_param_50),getString(R.string.background_SX_zx_param_51),
				getString(R.string.background_SX_zx_param_52),
				getString(R.string.background_SX_fg_zx_param_1), getString(R.string.background_SX_fg_zx_param_2), getString(R.string.background_SX_fg_zx_param_3),
				getString(R.string.background_SX_fg_zx_param_4), getString(R.string.background_SX_fg_zx_param_5), getString(R.string.background_SX_fg_zx_param_6),
				getString(R.string.background_SX_fg_zx_param_7), getString(R.string.background_SX_fg_zx_param_8), getString(R.string.background_SX_fg_zx_param_9),
				getString(R.string.background_SX_fg_zx_param_10), getString(R.string.background_SX_fg_zx_param_11), getString(R.string.background_SX_fg_zx_param_12),
				getString(R.string.background_SX_fg_zx_param_13), getString(R.string.background_SX_fg_zx_param_14), getString(R.string.background_SX_fg_zx_param_15),
				getString(R.string.background_SX_fg_zx_param_16), getString(R.string.background_SX_fg_zx_param_17), getString(R.string.background_SX_fg_zx_param_18),
				getString(R.string.background_SX_fg_zx_param_19), getString(R.string.background_SX_fg_zx_param_20), getString(R.string.background_SX_fg_zx_param_21),
				getString(R.string.background_SX_fg_zx_param_22), getString(R.string.background_SX_fg_zx_param_23), getString(R.string.background_SX_fg_zx_param_24),
				getString(R.string.background_SX_fg_zx_param_25), getString(R.string.background_SX_fg_zx_param_26), getString(R.string.background_SX_fg_zx_param_27),
				getString(R.string.background_SX_fg_zx_param_28), getString(R.string.background_SX_fg_zx_param_29), getString(R.string.background_SX_fg_zx_param_30),
				getString(R.string.background_SX_fg_zx_param_31), getString(R.string.background_SX_fg_zx_param_32), getString(R.string.background_SX_fg_zx_param_33),
				getString(R.string.background_SX_fg_zx_param_34), getString(R.string.background_SX_fg_zx_param_35), getString(R.string.background_SX_fg_zx_param_36),
				getString(R.string.background_SX_fg_zx_param_37), getString(R.string.background_SX_fg_zx_param_38), getString(R.string.background_SX_fg_zx_param_39),
				getString(R.string.background_SX_fg_zx_param_40), getString(R.string.background_SX_fg_zx_param_41), getString(R.string.background_SX_fg_zx_param_42),
				getString(R.string.background_SX_fg_zx_param_43), getString(R.string.background_SX_fg_zx_param_44), getString(R.string.background_SX_fg_zx_param_45),
				getString(R.string.background_SX_fg_zx_param_46), getString(R.string.background_SX_fg_zx_param_47), getString(R.string.background_SX_fg_zx_param_48),
				getString(R.string.background_SX_fg_zx_param_49), getString(R.string.background_SX_fg_zx_param_50), getString(R.string.background_SX_fg_zx_param_51),
				getString(R.string.background_SX_fg_zx_param_52), getString(R.string.background_SX_fg_zx_param_53), getString(R.string.background_SX_fg_zx_param_54),
				getString(R.string.background_SX_fg_zx_param_55), getString(R.string.background_SX_fg_zx_param_56)
		};

		LIFT_QUERY_WORK_STATUS_STAND = new String[] {getString(R.string.background_SX_bb_param_1), getString(R.string.background_SX_bb_param_2), getString(R.string.background_SX_bb_param_3),
				getString(R.string.background_SX_bb_param_4), getString(R.string.background_SX_bb_param_5)
		};

	}
}