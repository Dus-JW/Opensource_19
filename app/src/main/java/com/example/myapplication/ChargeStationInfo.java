package com.example.myapplication;

import java.util.Date;

public class ChargeStationInfo {
    String addr;    //충전소주소
    int chargeTp;   //1 : 완속
                    //2 : 급속
    int cpId;       //충전기ID
    String cpNm;    //충전기명칭
    int cpStat;     //1 : 충전가능
                    //2 : 충전중
                    //3 : 고장/점검
                    //4 : 통신장애
                    //5 : 통신미연결
    int cpTp;       //1 : B타입(5핀)
                    //2 : C타입(5핀)
                    //3 : BC타입(5핀)
                    //4 : BC타입(7핀)
                    //5 : DC차데모
                    //6 : AC3상
                    //7 : DC콤보
                    //8 : DC차데모+DC콤보
                    //9 : DC차데모+AC3상
                    //10 : DC차데모+DC콤보+AC3상
    int csId;       //충전소ID
    String csNm;    //충전소 명칭
    double lat;     //위도
    double longi;   //경도
    Date statUpdateDateTime;    //충전기 상태 갱신 시각

    public ChargeStationInfo() {
        this.addr = "";
        this.chargeTp = 0;
        this.cpId = 0;
        this.cpNm = "";
        this.cpStat = 0;
        this.cpTp = 0;
        this.csId = 0;
        this.csNm = "";
        this.lat = 0.0;
        this.longi = 0.0;
        this.statUpdateDateTime = null;
    }

    public int getChargeTp() {
        return chargeTp;
    }

    public void setChargeTp(int chargeTp) {
        this.chargeTp = chargeTp;
    }

    public int getCpId() {
        return cpId;
    }

    public void setCpId(int cpId) {
        this.cpId = cpId;
    }

    public String getCpNm() {
        return cpNm;
    }

    public void setCpNm(String cpNm) {
        this.cpNm = cpNm;
    }

    public int getCpStat() {
        return cpStat;
    }

    public void setCpStat(int cpStat) {
        this.cpStat = cpStat;
    }

    public int getCpTp() {
        return cpTp;
    }

    public void setCpTp(int cpTp) {
        this.cpTp = cpTp;
    }

    public int getCsId() {
        return csId;
    }

    public void setCsId(int csId) {
        this.csId = csId;
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

    public Date getStatUpdateDateTime() {
        return statUpdateDateTime;
    }

    public void setStatUpdateDateTime(Date statUpdateDateTime) {
        this.statUpdateDateTime = statUpdateDateTime;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
