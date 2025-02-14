// IMyAidlInterface.aidl
package com.tcn.drivers.aidl;

interface IOnDriversArrivedListener {
    void onQueryInfo(int door,int temp1,int temp2,int version,String versionName,String jsonData);
    void onQueryMachineInfo(int machineIndex,String jsonData);
    void onQueryMachineStatus(int machineIndex,int status,int errCode, String errMsg,String jsonData);
    void onQueryWorkStatus(int machineIndex,int type,String jsonData);
    void onShip(int slotNo, int shipStatus,int errCode,String shipMethod, String amount, String tradeNo, String errMsg,String jsonData);
    void onShipTest(int slotNo, int shipStatus,int errCode, String errMsg,String jsonData);
    void onSelectSlotNo(int slotNo,String jsonData);
    void onQueryParameters(int machineIndex,int address,int status, int data1,int data2,String jsonData);
    void onSetParameters(int machineIndex,int address,int status, int data1,int data2,int data3,int data4,String jsonData);
    void onQueryParametersOther(int machineIndex,int address,int status, int data1,int data2,String jsonData);
    void onSetParametersOther(int machineIndex,int address,int status, int data1,int data2,int data3,int data4,int num,String jsonData);
    void onFactoryReset(int machineIndex,int status,String jsonData);
    void onActionDo(int machineIndex,int status, int actionType, int data1, int data2, int errCode, String errMsg, String jsonData);
    void onActionDoOther(int machineIndex,int status, int actionType, int data1, int data2, int errCode, String errMsg, String jsonData);
    void onOpenCool(int machineIndex,int status,int data,String jsonData);
    void onOpenHeat(int machineIndex,int status,int data, String jsonData);
    void onCloseCoolAndHeat(int driveIndex,int status,int data, String jsonData);
    void onBackHome(int driveIndex,int status,int data, String jsonData);
    void onClapboardOpen(int driveIndex,int status,int data, String jsonData);
    void onClapboardClose(int driveIndex,int status,int data, String jsonData);
    void onDetectShip(int driveIndex,int status,int data, String jsonData);
    void onDetectLight(int driveIndex,int status,int data, String jsonData);
    void onTakeGoodsDoorOpen(int driveIndex,int status,int data, String jsonData);
    void onTakeGoodsDoorClose(int driveIndex,int status,int data, String jsonData);
    void onMicovenHeatOpen(int driveIndex,int status,int data, String jsonData);
    void onMicovenHeatClose(int driveIndex,int status,int data, String jsonData);
    void onLightOn(int driveIndex,int status,int data, String jsonData);
    void onLightOff(int driveIndex,int status,int data, String jsonData);
    void onDoorOpen(int driveIndex,int status,int data, String jsonData);
    void onDoorClose(int driveIndex,int status,int data, String jsonData);
    void onCleanDriveFaults(int machineIndex,int status, int errCode, String errMsg,String jsonData);
    void onUpdate(int machineIndex,int type,int data1, int data2,String jsonData);
    void onUploadData(int machineIndex,int type,String jsonData);
}
