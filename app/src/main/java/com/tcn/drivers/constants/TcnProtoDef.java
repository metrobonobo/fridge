package com.tcn.drivers.constants;

/**
 * Created by Administrator on 2017/6/7.
 */
public class TcnProtoDef {
    //串口
    public static final int SERIAL_PORT_RECEIVE_DATA = 100;
    public static final int SERIAL_PORT_RECEIVE_DATA_OTHER = 101;
    public static final int SERIAL_PORT_CONFIG_ERROR = 102;
    public static final int SERIAL_PORT_SECURITY_ERROR = 103;
    public static final int SERIAL_PORT_UNKNOWN_ERROR = 104;


    public static final int COMMAND_TOSS_COINS = 110; //投硬币
    public static final int COMMAND_TOSS_PAPER_MONEY = 111; //投纸币
    public static final int COMMAND_CHANGE_IN_COINS = 112; //找硬币
    public static final int COMMAND_CHANGE_IN_PAPER_MONEY = 113; //找纸币
    public static final int COMMAND_BALANCE = 114; //上报余额

    //出货
    public static final int COMMAND_SHIPMENT_CASHPAY = 120; //现金购买
    public static final int COMMAND_SHIPMENT_WECHATPAY = 121; //微信支付出货
    public static final int COMMAND_SHIPMENT_ALIPAY = 122; //支付宝出货
    public static final int COMMAND_SHIPMENT_GIFTS = 123; //赠送出货
    public static final int COMMAND_SHIPMENT_REMOTE = 124; //远程App出货   此次支付金额|货道号|状态
    public static final int COMMAND_SHIPMENT_VERIFY = 125; //提货码出货  此次支付金额|货道号|状态
    public static final int COMMAND_SHIPMENT_BANKCARD_ONE = 126; //刷银行卡1支付出货 MDB卡支付
    public static final int COMMAND_SHIPMENT_BANKCARD_TWO = 127; //刷银行卡2支付出货
    public static final int COMMAND_SHIPMENT_TCNCARD_OFFLINE = 128; //刷中吉IC卡离线支付出货
    public static final int COMMAND_SHIPMENT_TCNCARD_ONLINE = 129; //刷中吉IC卡在线支付出货
    public static final int COMMAND_SHIPMENT_OTHER_PAY = 130; //其它支付出货  此次支付金额|货道号|状态|支付方式


    public static final int COMMAND_TOTAL_SLOT_NUMBER = 140;  //总货道数   最大货道号|总有效货道数
    public static final int COMMAND_TEMPERATURE_INFO = 141;  //温度   柜号|温度|柜号|温度......|柜号|温度
    public static final int COMMAND_DOOR_SWITCH = 142;  //门状态  C/O
    public static final int COMMAND_KEY_NUMBER = 143;  //按键编号  按键号|已经选择的货道号(如果为0表示还没选择货道号)


    public static final int COMMAND_SLOTNO_INFO = 144;  //货道信息  此货道类型|货道号|单价|容量|现存|故障|掉货检测开关|此货道销售总数量|此货道销售总金额|商品编码

    public static final int COMMAND_SLOTNO_INFO_SINGLE = 145;

    public static final int CMD_LOOP = 146;

    public static final int CMD_READ_TEMP = 147;


    public static final int COMMAND_SLOTNO_INFO_MLZ = 148;

    public static final int COMMAND_SELECT_SLOTNO = 150; //选择货道
    public static final int COMMAND_INVALID_SLOTNO = 151;
    public static final int COMMAND_FAULT_SLOTNO = 152;
    public static final int COMMAND_SELECT_FAIL = 153;
    public static final int COMMAND_SELECT_KEY = 154;

    public static final int COMMAND_SNAKE_SELECT_SLOTNO = 155;
    public static final int COMMAND_SNAKE_SELECT_FAIL = 156;
    public static final int COMMAND_SNAKE_SELECT_SLOT_ERR = 157;
    public static final int COMMAND_SNAKE_SELECT_SLOT_SOLD_OUT = 158;
    public static final int COMMAND_SNAKE_SELECT_SELECT_BUSY = 159;

    public static final int COMMAND_SELECT_SLOT_FAIL = 160;

    public static final int COMMAND_SELECT_FAIL_DRIVES = 165;

    public static final int CMD_QUERY_AGE_STATUS_TO_SELECT = 170;

    public static final int COMMAND_BUSY = 180;
    public static final int REQ_CMD_TEST_SLOT = 181;
    public static final int CMD_TEST_SLOT = 182; //测试货道
    public static final int CMD_TEST_SLOT_NO_CHECK = 183; // 不带光检的测试出货

    public static final int COMMAND_SLOTNO_QUERY = 185;

    public static final int COMMAND_SLOTNO_QUERY_DRIVES_SUCCESS = 187;
    public static final int COMMAND_SLOTNO_QUERY_DRIVES_FAIL = 188;

    public static final int REQ_QUERY_SLOT_STATUS = 190;
    public static final int QUERY_SLOT_STATUS = 191;

    public static final int REQ_SELF_CHECK = 194;
    public static final int SELF_CHECK = 195;
    public static final int REQ_CMD_RESET = 196;
    public static final int CMD_RESET = 197;
    public static final int REQ_SET_SLOTNO_SPRING = 198;
    public static final int SET_SLOTNO_SPRING = 199;
    public static final int REQ_SET_SLOTNO_BELTS = 200;
    public static final int SET_SLOTNO_BELTS = 201;
    public static final int REQ_SET_SLOTNO_ALL_SPRING = 202;
    public static final int SET_SLOTNO_ALL_SPRING = 203;
    public static final int REQ_SET_SLOTNO_ALL_BELT = 204;
    public static final int SET_SLOTNO_ALL_BELT = 205;
    public static final int REQ_SET_SLOTNO_SINGLE = 206;
    public static final int SET_SLOTNO_SINGLE = 207;
    public static final int REQ_SET_SLOTNO_DOUBLE = 208;
    public static final int SET_SLOTNO_DOUBLE = 209;
    public static final int REQ_SET_SLOTNO_ALL_SINGLE = 210;
    public static final int SET_SLOTNO_ALL_SINGLE = 211;
    public static final int REQ_SET_TEST_MODE = 212;
    public static final int SET_TEST_MODE = 213;

    public static final int REQ_SET_TEMP_CONTROL_OR_NOT = 215;
    public static final int SET_TEMP_CONTROL_OR_NOT = 216;
    public static final int REQ_CMD_SET_COOL = 217;
    public static final int CMD_SET_COOL = 218;
    public static final int REQ_CMD_SET_HEAT = 219;
    public static final int CMD_SET_HEAT = 220;
    public static final int REQ_CMD_SET_TEMP = 221;
    public static final int CMD_SET_TEMP = 222;
    public static final int REQ_CMD_SET_GLASS_HEAT_OPEN = 223;
    public static final int CMD_SET_GLASS_HEAT_OPEN = 224;
    public static final int REQ_CMD_SET_GLASS_HEAT_CLOSE = 225;
    public static final int CMD_SET_GLASS_HEAT_CLOSE = 226;
    public static final int REQ_CMD_READ_CURRENT_TEMP = 227;
    public static final int CMD_READ_CURRENT_TEMP = 228;
    public static final int REQ_CMD_SET_LIGHT_OPEN = 229;
    public static final int CMD_SET_LIGHT_OPEN = 230;
    public static final int REQ_CMD_SET_LIGHT_CLOSE = 231;
    public static final int CMD_SET_LIGHT_CLOSE = 232;
    public static final int REQ_CMD_SET_BUZZER_OPEN = 233;
    public static final int CMD_SET_BUZZER_OPEN = 234;
    public static final int REQ_CMD_SET_BUZZER_CLOSE = 235;
    public static final int CMD_SET_BUZZER_CLOSE = 236;
    public static final int REQ_CMD_SET_COOL_HEAT_CLOSE = 237;
    public static final int CMD_SET_COOL_HEAT_CLOSE = 238;
    public static final int REQ_CMD_READ_DOOR_STATUS = 239;
    public static final int CMD_READ_DOOR_STATUS = 240;

    public static final int REQ_CMD_READ_CURRENT_HUMIDITY = 241;
    public static final int CMD_READ_CURRENT_HUMIDITY = 242;

    public static final int REQ_CMD_OPEN_DOOR = 243;
    public static final int CMD_OPEN_DOOR = 244;
    public static final int REQ_CMD_CLOSE_DOOR = 245;
    public static final int CMD_CLOSE_DOOR = 246;

    public static final int CMD_SET_ACTION_PARAMS = 248;
    public static final int CMD_QUERY_ACTION_PARAMS = 249;


    /*************************升降机 start************************************/
    public static final int REQ_CMD_QUERY_STATUS = 250;
    public static final int CMD_QUERY_STATUS = 251;
    public static final int REQ_CMD_TAKE_GOODS_DOOR = 254;
    public static final int CMD_TAKE_GOODS_DOOR = 255;
    public static final int REQ_CMD_LIFTER_UP = 256;
    public static final int CMD_LIFTER_UP = 257;
    public static final int REQ_CMD_LIFTER_BACK_HOME = 258;
    public static final int CMD_LIFTER_BACK_HOME = 259;
    public static final int REQ_CMD_CLAPBOARD_SWITCH = 260;
    public static final int CMD_CLAPBOARD_SWITCH_LIFT = 261;
    public static final int REQ_CMD_OPEN_COOL = 262;
    public static final int CMD_OPEN_COOL = 263;
    public static final int REQ_CMD_OPEN_HEAT = 264;
    public static final int CMD_OPEN_HEAT = 265;
    public static final int REQ_CMD_CLOSE_COOL_HEAT = 266;
    public static final int CMD_CLOSE_COOL_HEAT = 267;
    public static final int REQ_CMD_CLEAN_FAULTS = 268;
    public static final int CMD_CLEAN_FAULTS = 269;
    public static final int REQ_CMD_QUERY_PARAMETERS = 270;
    public static final int CMD_QUERY_PARAMETERS = 271;
    public static final int REQ_CMD_QUERY_DRIVER_CMD = 272;
    public static final int CMD_QUERY_DRIVER_CMD = 273;
    public static final int REQ_CMD_SET_SWITCH_OUTPUT_STATUS = 274;
    public static final int CMD_SET_SWITCH_OUTPUT_STATUS = 275;
    public static final int REQ_CMD_SET_ID = 276;
    public static final int CMD_SET_ID = 277;
    public static final int REQ_CMD_SET_LIGHT_OUT_STEP = 278;
    public static final int CMD_SET_LIGHT_OUT_STEP = 279;
    public static final int REQ_CMD_SET_PARAMETERS = 280;
    public static final int CMD_SET_PARAMETERS = 281;
    public static final int REQ_CMD_FACTORY_RESET = 282;
    public static final int CMD_FACTORY_RESET = 283;
    public static final int REQ_CMD_DETECT_LIGHT = 284;
    public static final int CMD_DETECT_LIGHT = 285;
    public static final int REQ_CMD_DETECT_SHIP = 286;
    public static final int CMD_DETECT_SHIP = 287;
    public static final int REQ_CMD_DETECT_SWITCH_INPUT = 288;
    public static final int CMD_DETECT_SWITCH_INPUT = 289;

    public static final int CMD_TAKE_GOODS_FIRST = 290;

    public static final int CMD_SHIP_FAIL_TAKE_GOODS_FIRST = 291;
    public static final int CMD_SHIP_SLOT_ERRCODE_UPDATE = 292;

    public static final int REQ_CMD_MICOVEN_HEAT_OPEN = 295;
    public static final int CMD_MICOVEN_HEAT_OPEN = 296;
    public static final int REQ_CMD_MICOVEN_HEAT_CLOSE = 297;
    public static final int CMD_MICOVEN_HEAT_CLOSE = 298;

    public static final int REQ_CMD_LIFTER_MOVE = 300;
    public static final int CMD_LIFTER_MOVE_START = 301;
    public static final int CMD_LIFTER_MOVE_END = 302;

    public static final int CMD_QUERY_DRIVE_VERSION_INFO = 303;

    public static final int REQ_CMD_QUERY_ADDRESS = 350;
    public static final int CMD_QUERY_ADDRESS = 351;
    public static final int REQ_CMD_SCAN_LIGHT_SET = 352;
    public static final int CMD_SCAN_LIGHT_SET = 353;
    public static final int REQ_CMD_CABINETNO_SET_VAILD = 354;
    public static final int CMD_CABINETNO_SET_VAILD = 355;
    public static final int REQ_CMD_CABINETNO_SET_INVAILD = 356;   // 柜号设置
    public static final int CMD_CABINETNO_SET_INVAILD = 357;   // 柜号设置
    public static final int REQ_CMD_CABINETNO_OFF = 358;
    public static final int CMD_CABINETNO_OFF = 359;
    public static final int CMD_CABINETNO_ON = 360;

    public static final int REQ_CMD_QUERY_WATER_TEMP = 370;
    public static final int CMD_QUERY_WATER_TEMP = 371;
    public static final int REQ_CMD_QUERY_SHIP_CUP = 372;
    public static final int CMD_QUERY_SHIP_CUP = 373;
    public static final int REQ_CMD_QUERY_CLEAN = 374;
    public static final int CMD_QUERY_CLEAN = 375;
    public static final int REQ_CMD_QUERY_COFF_STATUS = 376;
    public static final int CMD_QUERY_COFF_STATUS = 377;

    public static final int REQ_CMD_QUERY_SNAKE_STATUS = 390;
    public static final int CMD_QUERY_SNAKE_STATUS = 391;

    public static final int REQ_CMD_LED_OFF = 393;
    public static final int CMD_LED_OFF = 394;

    public static final int REQ_CMD_LED_ON = 395;
    public static final int CMD_LED_ON = 396;

    public static final int REQ_CMD_HEAT_COOL_TEMP_MODE = 397;

    public static final int CMD_COOL_TEMP_MODE = 398;

    public static final int CMD_HEAT_TEMP_MODE = 399;

    public static final int CMD_COOL_LEFT_TEMP_MODE = 400;

    public static final int CMD_COOL_RIGHT_TEMP_MODE = 402;

    public static final int CMD_HEAT_LEFT_TEMP_MODE = 404;

    public static final int CMD_HEAT_RIGHT_TEMP_MODE = 406;

    public static final int CMD_COOL_LEFT_HEAT_RIGHT_TEMP_MODE = 408;

    public static final int CMD_HEAT_LEFT_COOL_RIGHT_TEMP_MODE = 410;
    public static final int REQ_CMD_COOL_HEAT_DETAIL_PARAME = 411;
    public static final int CMD_COOL_HEAT_DETAIL_PARAME = 412;

    public static final int CMD_HEAT_COOL_NONE_MODE = 413;

    public static final int REQ_CMD_QUERY_GOODS = 416;
    public static final int CMD_QUERY_GOODS = 417;

    public static final int CMD_QUERY_GOODS_SELF = 418;

    public static final int REQ_CMD_SET_NOT_SELL_LASTONE = 419;
    public static final int CMD_SET_NOT_SELL_LASTONE = 420;

    public static final int REQ_CMD_SET_SELL_LASTONE = 421;
    public static final int CMD_SET_SELL_LASTONE = 422;

    public static final int REQ_CMD_SET_BOARD_ADDR = 423;
    public static final int CMD_SET_BOARD_ADDR = 424;

    public static final int REQ_CMD_READ_SNAKE_CURRENT_TEMP = 425;


    public static final int REQ_CMD_KEY_SET_STATUS = 430;
    public static final int CMD_KEY_SET_STATUS = 431;

    public static final int REQ_CMD_KEY_VALUE_PRE = 432;
    public static final int CMD_KEY_VALUE_PRE = 433;
    public static final int REQ_CMD_KEY_SET_VALUE = 434;
    public static final int CMD_KEY_SET_VALUE = 435;
    public static final int REQ_CMD_KEY_SET_PATTERN_MODE = 436;
    public static final int CMD_KEY_SET_PATTERN_MODE = 437;
    public static final int REQ_CMD_KEY_EXIT_PATTERN_MODE = 438;
    public static final int CMD_KEY_EXIT_PATTERN_MODE = 439;
    public static final int REQ_CMD_KEY_EXIT_FLICKER_STATUS = 440;
    public static final int CMD_KEY_EXIT_FLICKER_STATUS = 441;
    public static final int REQ_CMD_KEY_SET_SOLDOUT_KEY_PATTERN = 442;
    public static final int CMD_KEY_SET_SOLDOUT_KEY_PATTERN = 443;
    public static final int REQ_CMD_KEY_SET_SOLDOUT_KEY = 444;
    public static final int CMD_KEY_SET_SOLDOUT_KEY = 445;
    public static final int REQ_CMD_KEY_SET_ALL_SAME_COLOR = 446;
    public static final int CMD_KEY_SET_ALL_SAME_COLOR = 447;
    public static final int REQ_CMD_KEY_KEY_FLICKER = 448;
    public static final int CMD_KEY_KEY_FLICKER = 449;
    public static final int CMD_KEY_VALUE_SET_WAIT = 450;

    public static final int CMD_REQ_NO_FILE = 490;
    public static final int CMD_REQ_UPDATA = 491;
    public static final int CMD_UPDATA_DATA = 492;
    public static final int CMD_UPDATA_END = 493;
    public static final int CMD_CHECK_SERIPORT = 495;

    public static final int CMD_NO_DATA_RECIVE = 500;

    public static final int CMD_CLAPBOARD_SWITCH_WRSD = 550;
    public static final int CMD_CLAPBOARD_SWITCH_LIFT_ZJQH = 551;
    public static final int CMD_CLAPBOARD_SWITCH_HEFAN = 552;
    public static final int CMD_CLAPBOARD_SWITCH_HEFAN_DOUB = 553;
    public static final int CMD_CLAPBOARD_SWITCH_HAOBAO = 554;
    public static final int CMD_CLAPBOARD_SWITCH_SHAOB = 555;
    public static final int CMD_CLAPBOARD_SWITCH_SX = 556;

    public static final int CMD_SET_PARAMETERS_WRSD = 580;
    public static final int CMD_SET_PARAMETERS_DJS = 581;
    public static final int CMD_SET_PARAMETERS_LIFT_ZJQH = 582;
    public static final int CMD_SET_PARAMETERS_HEFAN = 583;
    public static final int CMD_SET_PARAMETERS_HEFAN_DOUB = 584;
    public static final int CMD_SET_PARAMETERS_HAOBAO = 585;
    public static final int CMD_SET_PARAMETERS_SHAOB = 586;
    public static final int CMD_SET_PARAMETERS_SX = 587;
    public static final int CMD_SET_PARAMETERS_HEFAN_ZP = 588;
    public static final int CMD_SET_PARAMETERS_MLZ = 589;
    public static final int CMD_SET_PARAMETERS_WRDGS = 590;
    public static final int CMD_SET_PARAMETERS_FDZK = 591;
    public static final int CMD_SET_PARAMETERS_ICE = 592;
    public static final int CMD_SET_PARAMETERS_COFF = 593;
    public static final int CMD_SET_PARAMETERS_CFMKX = 595;


    public static final int CMD_QUERY_STATUS_WRSD = 600;
    public static final int CMD_QUERY_STATUS_DJS = 601;
    public static final int CMD_QUERY_STATUS_LIFT_ZJQH = 602;
    public static final int CMD_QUERY_STATUS_HEFAN = 603;
    public static final int CMD_QUERY_STATUS_HEFAN_DOUB = 604;
    public static final int CMD_QUERY_STATUS_HAOBAO = 605;
    public static final int CMD_QUERY_STATUS_SHAOB = 606;
    public static final int CMD_QUERY_STATUS_SX = 607;
    public static final int CMD_QUERY_STATUS_MLZ = 608;
    public static final int CMD_QUERY_STATUS_WRDGS = 609;
    public static final int CMD_QUERY_STATUS_FDZK = 610;
    public static final int CMD_QUERY_STATUS_YSBOARD = 611;
    public static final int CMD_QUERY_STATUS_STAND = 612;
    public static final int CMD_QUERY_STATUS_KIMA = 613;

    public static final int CMD_TAKE_GOODS_DOOR_WRSD = 620;
    public static final int CMD_TAKE_GOODS_DOOR_DJS = 621;
    public static final int CMD_TAKE_GOODS_DOOR_LIFT_ZJQH = 622;
    public static final int CMD_TAKE_GOODS_DOOR_HEFAN = 623;
    public static final int CMD_TAKE_GOODS_DOOR_HEFAN_DOUB = 624;
    public static final int CMD_TAKE_GOODS_DOOR_HAOBAO = 625;
    public static final int CMD_TAKE_GOODS_DOOR_SHAOB = 626;
    public static final int CMD_TAKE_GOODS_DOOR_SX = 627;
    public static final int CMD_TAKE_GOODS_DOOR_MLZ = 628;
    public static final int CMD_TAKE_GOODS_DOOR_WRDGS = 629;
    public static final int CMD_TAKE_GOODS_DOOR_FDZK = 630;

    public static final int CMD_LIFTER_UP_DJS = 650;
    public static final int CMD_LIFTER_UP_ZJQH = 651;
    public static final int CMD_LIFTER_UP_HEFAN = 652;
    public static final int CMD_LIFTER_UP_HEFAN_DOUB = 653;
    public static final int CMD_LIFTER_UP_HAOBAO = 654;
    public static final int CMD_LIFTER_UP_SHAOB = 655;
    public static final int CMD_LIFTER_UP_SX = 656;
    public static final int CMD_LIFTER_UP_MLZ = 657;
    //    public static final int CMD_LIFTER_UP_WRDGS                    = 658;
    public static final int CMD_LIFTER_UP_FDZK = 659;
    public static final int CMD_LIFTER_UP_MBL = 660;
    public static final int CMD_ACTION_DO = 670;
    public static final int CMD_ACTION_DO_DRIVES = 671;

    public static final int CMD_ACTION_DRIVES_STATUS_DOING = 672;
    public static final int CMD_ACTION_DRIVES_STATUS_SUCCESS = 673;
    public static final int CMD_ACTION_DRIVES_STATUS_FAIL = 674;

    public static final int CMD_ERR_TIPS = 680;

    public static final int CMD_DOOR_HAVE_GOODS = 685;

    public static final int CMD_LIFT_UP_WHEN_NO_BUY = 690;

    public static final int CMD_SET_PARAMETERS_DRIVES = 695;

    public static final int CMD_CLEAN_FAULTS_DRIVES = 697;

    public static final int REQ_CMD_SET_PARAMETERS_AAR = 698;// 同时设置多个参数

    /*************************升降机 end************************************/

    public static final int CMD_RESETING = 700;   //复位中
    public static final int CMD_MAKING_POPCORN = 701;    //制作爆米花中
    public static final int CMD_MAKE_POPCORN_END = 702;    //制作爆米花完成
    public static final int CMD_TAKE_OUT = 703;    //取走爆米花


    /*************************板栗机 start************************************/
    public static final int CMD_QUERY_STATUS_MBL = 750;
    public static final int CMD_SET_PARAMETERS_MBL = 751;
    public static final int CMD_MBL_BAKING_LOOP = 753;
    public static final int REQ_CMD_DO_CONTROL = 754;
    /*************************板栗机 end************************************/

    /*************************冰淇淋机 start************************************/

    public static final int REQ_CMD_ICE_SET_WORK_MODE = 800;    //设置工作模式
    public static final int CMD_ICE_SET_WORK_MODE = 801;    //设置工作模式

    public static final int REQ_CMD_PARAM_ICE_MAKE_SET = 802;    //制冰机参数设置
    public static final int CMD_PARAM_ICE_MAKE_SET = 803;    //制冰机参数设置

    public static final int REQ_CMD_PARAM_ICE_MAKE_QUERY = 804;    //制冰机参数查询
    public static final int CMD_PARAM_ICE_MAKE_QUERY = 805;    //制冰机参数查询

    public static final int REQ_CMD_PARAM_QUERY = 806;    //查询参数
    public static final int CMD_PARAM_QUERY = 807;    //查询参数

    public static final int REQ_CMD_PARAM_SET = 808;    //设置参数
    public static final int CMD_PARAM_SET = 809;    //设置参数


    public static final int REQ_CMD_POSITION_MOVE = 810;    //移动位置
    public static final int CMD_POSITION_MOVE = 811;    //移动位置


    public static final int REQ_CMD_QUERY_STATUS_AND_JUDGE = 815;    //状态查询与判断
    public static final int CMD_QUERY_STATUS_AND_JUDGE = 816;    //状态查询与判断

    public static final int REQ_CMD_SELF_INSPECTION = 819;//机器自检
    public static final int CMD_SELF_INSPECTION = 820;//机器自检
    public static final int REQ_CMD_TEST_DISCHARGE = 821;//测试出料testDischarge
    public static final int CMD_TEST_DISCHARGE = 822;//测试出货

    public static final int CMD_QUERY_STATUS_ICEC = 832;
    public static final int CMD_QUERY_STATUS_GOODS_TAKE = 833;
    public static final int CMD_SELF_INSPECTION_STATUS = 835;// 自检状态查询
    public static final int REQ_CMD_PARAM_AUERY_ALL = 838;// 查询一类项目参数
    public static final int REQ_QUERY_ICE_CMD = 839;// 查询故障日志
    public static final int REQ_CMD_LOGOUT = 840;// 返回故障日志
    public static final int REQ_CMD_QUERY_DRIVERVERSION = 899;

    /*************************冰淇淋机 end************************************/


    /*************************转盘盒饭机 start************************************/

    public static final int REQ_CMD_QUERY_STATUS_FD_ZP = 900;
    public static final int CMD_QUERY_STATUS_FD_ZP = 901;
    public static final int CMD_TAKE_GOODS_DOOR_HEFAN_ZP = 902;
    public static final int CMD_QUERY_STATUS_SHIP_START_HEATING = 905;

    /*************************转盘盒饭机 end************************************/

    public static final int REQ_CMD_QUERY_STATUS_CFM_KX = 910;
    public static final int CMD_QUERY_STATUS_CFM_KX = 911;
    public static final int CMD_TAKE_GOODS_DOOR_CFM_KX = 912;


    public static final int CMD_QUERY_STATUS_COCO = 920;
    public static final int CMD_TAKE_GOODS_DOOR_COCO = 922;
    public static final int CMD_RCV_KEY_PRESS = 928;

    public static final int CMD_QUERY_STATUS_MIQ = 930;

    public static final int CMD_COMMODITY_RECOVERY = 935;

    public static final int CMD_UPLOAD_PRICE = 940;
    public static final int CMD_UPLOAD_STATUS_STOCK = 941;

    public static final int CMD_UPLOAD_STOCK_MBL = 942;


    /*************************咖啡机 start************************************/

    public static final int REQ_CMD_QUERY_STATUS_CFN = 1001;
    public static final int CMD_QUERY_STATUS_CFN = 1002;
    public static final int REQ_CMD_OPEN_CABINET_DOOR = 1003;       //开箱体柜门
    public static final int CMD_OPEN_CABINET_DOOR = 1004;       //开箱体柜门
    public static final int REQ_CMD_CLOSE_CABINET_DOOR = 1005;       //关箱体柜门
    public static final int CMD_CLOSE_CABINET_DOOR = 1006;       //关箱体柜门
    public static final int REQ_CMD_SUPPLY_WATER = 1007;       //中转水箱加水
    public static final int CMD_SUPPLY_WATER = 1008;       //中转水箱加水
    public static final int REQ_CMD_HIGH_PRE_EFFLUENT = 1009;       //高压出水 High pressure effluent
    public static final int CMD_HIGH_PRE_EFFLUENT = 1010;       //高压出水 High pressure effluent
    public static final int REQ_CMD_LOW_PRE_EFFLUENT = 1011;       //低压出水
    public static final int CMD_LOW_PRE_EFFLUENT = 1012;       //低压出水
    public static final int REQ_CMD_TEST_BEAN_GRINDING = 1013;       //磨豆测试  Bean grinding test
    public static final int CMD_TEST_BEAN_GRINDING = 1014;       //磨豆测试  Bean grinding test
    public static final int REQ_CMD_TEST_BOILER_HEATING = 1015;       //测试锅炉加热  Test Boiler Heating
    public static final int CMD_TEST_BOILER_HEATING = 1016;       //测试锅炉加热  Test Boiler Heating
    public static final int REQ_CMD_GLAND = 1017;       //压盖  Gland
    public static final int CMD_GLAND = 1018;       //压盖  Gland
    public static final int REQ_CMD_OPEN_TAKEGOODS_DOOR = 1019;       //打开小门  打开取货门
    public static final int CMD_OPEN_TAKEGOODS_DOOR = 1020;       //打开小门  打开取货门
    public static final int REQ_CMD_CLOSE_TAKEGOODS_DOOR = 1021;       //关闭小门  关闭取货门
    public static final int CMD_CLOSE_TAKEGOODS_DOOR = 1022;       //关闭小门  关闭取货门
    public static final int REQ_CMD_OPEN_MANIPULATOR = 1023;       //机械手打开  Manipulator open
    public static final int CMD_OPEN_MANIPULATOR = 1024;       //机械手打开  Manipulator open
    public static final int REQ_CMD_CLOSE_MANIPULATOR = 1025;       //机械手关闭  Manipulator close
    public static final int CMD_CLOSE_MANIPULATOR = 1026;       //机械手关闭  Manipulator close
    public static final int REQ_CMD_REVERSAL_HOPPER = 1027;       //翻转料斗  Reversal hopper
    public static final int CMD_REVERSAL_HOPPER = 1028;       //翻转料斗  Reversal hopper
    public static final int REQ_CMD_POSITION_OF_EXTRACTOR_PUL = 1029;       //萃取器接粉位置  Pulverized position of extractor
    public static final int CMD_POSITION_OF_EXTRACTOR_PUL = 1030;       //萃取器接粉位置  Pulverized position of extractor
    public static final int REQ_CMD_POSITION_OF_EXTRACTOR_BRE = 1031;       //萃取器冲泡位置  Brewing position of extractor
    public static final int CMD_POSITION_OF_EXTRACTOR_BRE = 1032;       //萃取器冲泡位置  Brewing position of extractor
    public static final int REQ_CMD_DOOR_ASCENDING = 1033;       //上升门  Ascending door
    public static final int CMD_DOOR_ASCENDING = 1034;       //上升门  Ascending door
    public static final int REQ_CMD_DOOR_DESCENT = 1035;       //下降门  Descent door
    public static final int CMD_DOOR_DESCENT = 1036;       //下降门  Descent door
    public static final int REQ_CMD_ROTARY_AGITATOR = 1037;       //旋转搅拌器  Rotary agitator
    public static final int CMD_ROTARY_AGITATOR = 1038;       //旋转搅拌器  Rotary agitator
    public static final int REQ_CMD_CARTRIDGE_DISCHARGE = 1039;       //料盒出料  Cartridge discharge
    public static final int CMD_CARTRIDGE_DISCHARGE = 1040;       //料盒出料  Cartridge discharge
    public static final int REQ_CMD_MOVE_TO_MAT_BOX = 1041;       //移动到物料盒位  Move to Material Box
    public static final int CMD_MOVE_TO_MAT_BOX = 1042;       //移动到物料盒位  Move to Material Box
    public static final int REQ_CMD_MOVE_TO_DROPPER = 1043;       //移动到落杯器  Move to Dropper
    public static final int CMD_MOVE_TO_DROPPER = 1044;       //移动到落杯器  Move to Dropper
    public static final int REQ_CMD_DROP_SINGLE = 1045;       //单次落杯  Single drop
    public static final int CMD_DROP_SINGLE = 1046;       //单次落杯  Single drop
    public static final int REQ_CMD_MOVE_TO_OUTLET = 1047;       //移动到出水口 Move to the outlet
    public static final int CMD_MOVE_TO_OUTLET = 1048;       //移动到出水口 Move to the outlet
    public static final int REQ_CMD_MOVE_TO_TAKE_CUP = 1049;       //移动到用户取杯口 Move to the user's cup mouth
    public static final int CMD_MOVE_TO_TAKE_CUP = 1050;       //移动到用户取杯口 Move to the user's cup mouth
    public static final int REQ_CMD_MOVE_TO_MIXER = 1051;       //移动到搅拌处 Move to the mixer
    public static final int CMD_MOVE_TO_MIXER = 1052;       //移动到搅拌处 Move to the mixer
    public static final int REQ_CMD_MOVE_TO_CLEAN = 1053;       //移动到清洗处 Move to the cleaning area
    public static final int CMD_MOVE_TO_CLEAN = 1054;       //移动到清洗处 Move to the cleaning area
    public static final int REQ_CMD_MOVE_TO_ZAXIS_TOP = 1055;       //移动Z轴顶部 Moving Z-axis top
    public static final int CMD_MOVE_TO_ZAXIS_TOP = 1056;       //移动Z轴顶部 Moving Z-axis top
    public static final int REQ_CMD_QUERY_REAL_TIME_DATA = 1058;
    public static final int CMD_QUERY_REAL_TIME_DATA = 1059;

    public static final int REQ_CMD_MOVE_RESULT = 1060;//运动控制板复位
    public static final int CMD_MOVE_RESULT = 1061;//运动控制板复位
    public static final int REQ_SET_OR_QUERY_PARAMS = 1062;

    public static final int CMD_OUT_COFFEE_SINGLE = 1063;//单出咖啡
    public static final int REQ_CMD_OUT_COFFEE_SINGLE = 1064;//单出咖啡


    public static final int CMD_SET_ACTION_PARAMETERS = 1065;         //设置动作参数
    public static final int CMD_QUERY_ACTION_PARAMETERS = 1066;          //查询动作参数
    public static final int REQ_CMD_MOVE_OTHER = 1067;// 其它动作命令
    public static final int CMD_MOVE_OTHER = 1068;// 其它动作命令

    public static final int COMMAND_REQ_PAY = 1070;
    public static final int COMMAND_CANCEL_PAY = 1071;
    public static final int COMMAND_FAULT_INFORMATION = 1072;
    public static final int COMMAND_PROHIBIT_OPERATION = 1073;
    public static final int COMMAND_CONFIG_INFO = 1074;
    public static final int COMMAND_CONFIG_OK = 1075;
    public static final int COMMAND_SLOTNO_INFO_VALUE = 1076;
    public static final int COMMAND_FRAME_NUMBER = 1077;

    public static final int CMD_INITED_DRIVES = 1079;
    public static final int CMD_INITED = 1080;
    public static final int CMD_QUERY_BOARD_INFO_UPDATE = 1081;
    public static final int CMD_QUERY_WORK_STATUS = 1085;
    public static final int CMD_INITED_STANDJS = 1088;

    public static final int CMD_DRIVE_TIPS = 1090;

    public static final int REQ_SET_PARAMS = 1095;  // 参数设置
    public static final int REQ_QUERY_PARAMS = 1096; // 参数查询

    public static final int COMMAND_MACHINEID = 1100;

    public static final int CMD_LIFTER_UP_STAND = 1200;

    /*************************咖啡机 end************************************/

    public static final int CMD_CLEAN_DROP_STATUS = 1205;
    public static final int CMD_READ_DROP_STATUS = 1206;
    public static final int CMD_SET_DROP_SENSOR = 1207;
    public static final int CMD_READ_DROP_SENSOR = 1208;
    public static final int CMD_READ_REAL_DATA = 1209;

    public static final int CMD_MACHINE_IS_BUSY = 1210;

    /*******制冷压缩机 start********************/
    public static final int REQ_CMD_SET_DEFROST_TIME = 1220;
    public static final int CMD_SET_DEFROST_TIME = 1221;
    public static final int REQ_CMD_SET_COMPRESSOR_WORKING_TIME = 1222;
    public static final int CMD_SET_COMPRESSOR_WORKING_TIME = 1223;
    /*************制冷压缩机 end***************/

    public static final int CMD_CABINET_SHIP_OPEN_QUERY = 1250;
    public static final int CMD_CABINET_SHIP_CLOSE_QUERY = 1256;
    public static final int CMD_CABINET_STATUS_DOOR = 1258;

    public static final int CMD_SWITCH_OUT_INPUT_DOOR_OPEN = 1260;
    public static final int CMD_SWITCH_OUT_INPUT_DOOR_CLOSE = 1261;

    /*******五寸屏  start********************/
    public static final int CMD_RECIVE_COIN_MONEY = 1300;//硬币
    public static final int CMD_RECIVE_PAPER_MONEY = 1301;// 纸币
    public static final int CMD_CASH_CHANGE_BLANCE = 1302; //
    public static final int CMD_RECIVE_HINT_BALANCE = 1318; //纸硬币器可找零总余额

    public static final int CMD_PAYOUT_AMOUNT = 1303;
    public static final int CMD_CASH_EXCEED_MAX_BLANCE = 1304;//纸硬币器投币金额大于累计投币限制金额
    public static final int CMD_QUERY_KEY_NUMBER = 1310;
    public static final int CMD_QUERY_KEY_ENTER = 1312;
    public static final int CMD_QUERY_KEY_BACK = 1313;
    public static final int CMD_QUERY_KEY_CANCEL = 1314;
    public static final int CMD_QUERY_KEY_NUMBER_ALL = 1315;
    public static final int CMD_QUERY_KEY_PICKUP_GOODS = 1316;
    public static final int CMD_QUERY_KEY_REFUND = 1317;

    public static final int CMD_QUERY_SWIPE_STATUS = 1320;//Swipe status
    public static final int CMD_CARD_SWIPED = 1321;//Swipe status
    public static final int CMD_CARD_CONSUMED_SUCCESS = 1322;//Swipe status
    public static final int CMD_CARD_CONSUMED_FAIL = 1323;//Swipe status
    public static final int CMD_START_CARD_PAY = 1325;

    public static final int CMD_SEND_TO_UI_STRING = 1340;// 发送字符串到界面


    /*******五寸屏 end********************/

    public static final int CMD_ERR_TIPS_TO_SERVER = 1350;


    public static final int CMD_SET_CAN_SHIP_NEXT = 1400;
    public static final int CMD_SET_SHIP_FINISH = 1401;


    public static final int CMD_QUERY_STATUS_DRIVES = 1500;


    public static final int CMD_GET_DISTANCE = 12500;

    public static final int CMD_ALARM = 12501;
    public static final int CMD_ALARM_OFF = 12502;
    public static final int CMD_ALARM_PARAM_QUERY = 12503;
    public static final int CMD_ALARM_PARAM_SET = 12504;

    public static final int CMD_GET_SENSOR = 12505;// gpio感应器


    //美团页面
    public static final int CMD_MEITUAN_TAKE_GOODS = 12601;//取货
    public static final int CMD_MEITUAN_UNLOAD_TO_TEMP_STORE = 12602;//卸货到暂存仓
    public static final int CMD_MEITUAN_UNLOAD_TO_AVG = 12603;//卸货到AVG
    public static final int CMD_MEITUAN_CURRENT_LOG = 12604;//日志
    public static final int CMD_MEITUAN_CURRENT_SERVER_STATE = 12605;//日志


    // 货道驱动板新增参数查询
    public static final int CMD_QUERY_PARAMETERCARGODRIVE = 12800;// 参数查询
    public static final int REQ_CMD_QUERY_PARAMETER = 12801;// 参数查询
    public static final int CMD_SET_JITTER = 12804;// 设置抖动Jitter
    public static final int CMD_SET_JITTER_AFTER_DELIVERY = 12805;//

    public static final int CMD_SET_JITTER_NUM = 12806;// 设置抖动次数
    public static final int RESULT_CMD_SET_JITTER_NUM = 12807;// 返回抖动次数设置结果

    public static final int CMD_SET_JITTER_WAITE_TIME = 12808;// 设置等待时间
    public static final int RESULT_CMD_SET_JITTER_WAITE_TIME = 12809;// 返回设置等待时间

    public static final int CMD_TEST_SHIP_NO_CHECNK = 12810;// 不带光检的测试出货

    public static final int CMD_READ_TEMP2 = 12811;
}
