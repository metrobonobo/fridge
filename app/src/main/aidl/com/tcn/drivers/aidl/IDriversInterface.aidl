// IMyAidlInterface.aidl
package com.tcn.drivers.aidl;


// Declare any non-default types here with import statements
import com.tcn.drivers.aidl.IOnDriversArrivedListener;

interface IDriversInterface {
    //获取信息
    void reqAllInfo();

    //温度  门控开关 版本号
    void reqQueryInfo();

    void reqQueryMachineInfo(int machineIndex);

    void reqQueryMachineStatus(int machineIndex);

    void reqQueryWorkStatus(int machineIndex,int type, String jsonData);

    void registerListener(IOnDriversArrivedListener listener);

    void unregisterListener(IOnDriversArrivedListener listener);

    void ship(int slotNo,int heartTimeSeconds, String shipMethod, String amount, String tradeNo,String jsonData);

    void shipTest(int startSlotNo, int endSlotNo, int heartTimeSeconds,String jsonData);

    void reqSelectSlotNo(int slotNo,String jsonData);

    void reqQueryParameters(int machineIndex,int address, int data1,int data2,String jsonData);

    void reqSetParameters(int machineIndex,int address, int data1,int data2,int data3,int data4,int num,String jsonData);

    void reqQueryParametersOther(int driveIndex,int address, int chn, String jsonData);

    void reqSetParameterssOther(int driveIndex,int addr, int parameter1,int parameter2,int parameter3,int parameter4,int num, String jsonData);

    void reqFactoryReset(int driveIndex,int data,String jsonData);

    void reqActionDo(int machineIndex,int actionType,int data1, int data2,int data3,int data4, String jsonData);

    void reqActionDoOther(int driveIndex,int actionType,int data1, int data2,int data3, int data4, String jsonData);

    void reqOpenCool(int driveIndex,int data1, int data2,int data3, int data4, String jsonData);

    void reqOpenHeat(int driveIndex,int data1, int data2,int data3, int data4, String jsonData);

    void reqCloseCoolAndHeat(int driveIndex,int data1, int data2,int data3, int data4, String jsonData);

    void reqBackHome(int driveIndex,int data1, int data2,int data3, int data4, String jsonData);

    void reqClapboardOpen(int driveIndex,int data, String jsonData);

    void reqClapboardClose(int driveIndex,int data, String jsonData);

    void reqDetectShip(int driveIndex,int data1, int data2,int data3, int data4, String jsonData);

    void reqDetectLight(int driveIndex,int data1, int data2,int data3, int data4, String jsonData);

    void reqTakeGoodsDoorOpen(int driveIndex,int data1, int data2,int data3, int data4, String jsonData);

    void reqTakeGoodsDoorClose(int driveIndex,int data1, int data2,int data3, int data4, String jsonData);

    void reqMicovenHeatOpen(int driveIndex,int data1, int data2,int data3, int data4, String jsonData);

    void reqMicovenHeatClose(int driveIndex,int data1, int data2,int data3, int data4, String jsonData);

    void reqLightOn(int driveIndex,int data1, int data2,int data3, int data4, String jsonData);

    void reqLightOff(int driveIndex,int data1, int data2,int data3, int data4, String jsonData);

    void reqDoorOpen(int driveIndex,int data1, int data2,int data3, int data4, String jsonData);

    void reqDoorClose(int driveIndex,int data1, int data2,int data3, int data4, String jsonData);

    void reqCleanDriveFaults(int machineIndex,String jsonData);

    void reqUploadInfo(int machineIndex,int type);

    void reqUpdate(int machineIndex,int type,int data1, int data2,String jsonData);

    int getShipStatus(String tradeNo);

    int getInfoStatus(int type);

    String getInfoStatusJson(int type);

    void setConfigJsonData(String jsonData);

    String getConfigJsonData();

}
