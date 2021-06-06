package com.example.myapplication;

public class MyCarInfo {        //싱글톤으로 객체 1개 유지
    //static field
    private static MyCarInfo myCarInfo = new MyCarInfo();
    //차량 이름(일반 String)
    String name;
    //차량 회사(가능하면 몇가지 중 선택하기)
    String Company;
    //주행 가능 거리
    int range;
    //충전 플러그 선택(몇가지 중 선택)int
    int cpTp;
    /*  1 : B타입(5핀)
        2 : C타입(5핀)
        3 : BC타입(5핀)
        4 : BC타입(7핀)
        5 : DC차데모
        6 : AC3상
        7 : DC콤보
        8 : DC차데모+DC콤보
        9 : DC차데모+AC3상
        10 : DC차데모+DC콤보+AC3상*/

    private MyCarInfo()
    {
        name = null;
        Company = null;
        range = 0;
        cpTp = 0;
    }

    static MyCarInfo getInstance()
    {
        return myCarInfo;
    }
}
