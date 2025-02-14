package com.tcn.drivers.constants;

public class TcnProtoResultDef {

	public static final int SHIP_SHIPING             = 1; //出货中
	public static final int SHIP_SUCCESS             = 2; //出货成功
	public static final int SHIP_FAIL                = 3; //出货失败

	public static final int DO_NONE   = -1;
	public static final int DO_START   = 0;
	public static final int DO_END   = 1;

	public static final int STATUS_INVALID		             = -1;
	public static final int STATUS_FREE		             = 0;
	public static final int STATUS_BUSY		             = 1;
	public static final int STATUS_WAIT_TAKE_GOODS		 = 2;           //等待客户取走



	public static final int SUCCESS		            = 1;
	public static final int FAIL		            = 0;

	public static final int OFF_SUCCESS		            = 1;
	public static final int OFF_FAIL		            = 0;
	public static final int OFF_CLOSING		         = -1;

	public static final int DOOR_OPEN     = 0;
	public static final int DOOR_CLOSE    = 1;
}
