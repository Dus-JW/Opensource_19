package com.example.myapplication;

import java.util.Date;

public class ChargeStationInfo {
    String addr;    //충전소주소
    String csNm;    //충전소 명칭
    double lat;     //위도
    double longi;   //경도
    ChargeMachine[] machines;   //기기 정보
    int machines_size;
    public ChargeStationInfo() {
        this.addr = "";
        this.csNm = "";
        this.lat = 0.0;
        this.longi = 0.0;
        this.machines = null;
        this.machines_size = 0;
    }

    //충전소에 해당하는 충전기가 있으면 true
    public  boolean chargeType(int want_check){
        for(int i = 0; i < machines_size; i++){
            if(want_check == machines[i].getCpTp()){
                return true;
            }
        }

        return false;
    }
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCsNm() {
        return csNm;
    }

    public void setCsNm(String csNm) {
        this.csNm = csNm;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public ChargeMachine[] getMachines() {
        return machines;
    }

    public void setMachines(ChargeMachine[] machines) {
        this.machines = machines;
    }

    public int getMachines_size() {
        return machines_size;
    }

    public void setMachines_size(int machines_size) {
        this.machines_size = machines_size;
    }
}
