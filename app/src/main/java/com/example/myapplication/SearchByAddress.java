package com.example.myapplication;

import android.os.AsyncTask;
import android.text.PrecomputedText;
import android.util.Log;

import androidx.core.text.PrecomputedTextCompat;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;

//public class SearchByAddress extends AsyncTask<String, Integer, NodeList> {
public class SearchByAddress {
    private StringBuilder xmlString;
    private ChargeStationInfo[] Stations;
    private int Station_size;
    private int temp_size;
    public SearchByAddress() {
        this.xmlString = new StringBuilder();
    }

    public static StringBuilder APISearch(String SearchArea) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.kepco.co.kr/service/EvInfoServiceV2/getEvSearchList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=mc8t%2FIKId9o9HjveUHJrRF%2BAU86YpNgUZq0sQ48LdzYalmgyTOvovrAgdCinV%2Ba32d%2BgzEfwm6alnB8exvhnjw%3D%3D"); /*Service Key*/
        //urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("mc8t%2FIKId9o9HjveUHJrRF%2BAU86YpNgUZq0sQ48LdzYalmgyTOvovrAgdCinV%2Ba32d%2BgzEfwm6alnB8exvhnjw%3D%3D", "UTF-8")); /*공공데이터포털에서 받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("addr","UTF-8") + "=" + URLEncoder.encode(SearchArea, "UTF-8")); /*검색대상 충전소주소*/
        URL url = new URL(urlBuilder.toString());
        String onlyurl = "http://openapi.kepco.co.kr/service/EvInfoServiceV2/getEvSearchList?ServiceKey=mc8t%2FIKId9o9HjveUHJrRF%2BAU86YpNgUZq0sQ48LdzYalmgyTOvovrAgdCinV%2Ba32d%2BgzEfwm6alnB8exvhnjw%3D%3D" +
                "&pageNo=1&numOfRows=10&addr=%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C+%EC%84%B1%EB%B6%81%EA%B5%AC";
        //URL url = new URL(onlyurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        //System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        //System.out.println(sb.toString());

        return sb;
    }

    public void XmlToStationList(StringBuilder in) throws ParserConfigurationException, IOException, SAXException, ParseException {
        InputSource is = new InputSource(new StringReader(in.toString()));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(is);

        Element root = document.getDocumentElement();
        NodeList item = root.getElementsByTagName("item");

        Stations = new ChargeStationInfo[item.getLength()];
        Station_size = item.getLength();
        int current_station_num = 0;
        int station_index = 0;
        for(int i = 0; i < item.getLength(); i++){
            Node node = item.item(i);
            NodeList node_list = node.getChildNodes();
            Log.d("intheapi",node_list.item(0).getFirstChild().getNodeValue());
            //이미 있는 스테이션인지 확인 후 있으면 정보 추가
            boolean midluck = false;
            int j = 0;
            for(j = 0; j < current_station_num ; j++){
                if(Stations[j].getAddr().equals(node_list.item(0).getFirstChild().getNodeValue())){
                    midluck = true;
                    break;
                }
            }
            if(midluck == true){
                Stations[j].machines[Stations[j].machines_size] = new ChargeMachine();
                Stations[j].machines[Stations[j].machines_size].setChargeTp(Integer.parseInt(node_list.item(1).getFirstChild().getNodeValue()));
                Stations[j].machines[Stations[j].machines_size].setCpId(Integer.parseInt(node_list.item(2).getFirstChild().getNodeValue()));
                Stations[j].machines[Stations[j].machines_size].setCpNm(node_list.item(3).getFirstChild().getNodeValue());
                Stations[j].machines[Stations[j].machines_size].setCpStat(Integer.parseInt(node_list.item(4).getFirstChild().getNodeValue()));
                Stations[j].machines[Stations[j].machines_size].setCpTp(Integer.parseInt(node_list.item(5).getFirstChild().getNodeValue()));
                Stations[j].machines[Stations[j].machines_size].setCsId(Integer.parseInt(node_list.item(6).getFirstChild().getNodeValue()));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Stations[j].machines[Stations[j].machines_size].setStatUpdateDateTime(sdf.parse(node_list.item(10).getFirstChild().getNodeValue()));
                Stations[j].setMachines_size(Stations[j].machines_size + 1);
                continue;

            }
            Stations[station_index] = new ChargeStationInfo();
            Stations[station_index].setAddr(node_list.item(0).getFirstChild().getNodeValue());
            Stations[station_index].setMachines(new ChargeMachine[50]);
            Stations[station_index].machines[0] = new ChargeMachine();
            Stations[station_index].machines[0].setChargeTp(Integer.parseInt(node_list.item(1).getFirstChild().getNodeValue()));
            Stations[station_index].machines[0].setCpId(Integer.parseInt(node_list.item(2).getFirstChild().getNodeValue()));
            Stations[station_index].machines[0].setCpNm(node_list.item(3).getFirstChild().getNodeValue());
            Stations[station_index].machines[0].setCpStat(Integer.parseInt(node_list.item(4).getFirstChild().getNodeValue()));
            Stations[station_index].machines[0].setCpTp(Integer.parseInt(node_list.item(5).getFirstChild().getNodeValue()));
            Stations[station_index].machines[0].setCsId(Integer.parseInt(node_list.item(6).getFirstChild().getNodeValue()));
            Stations[station_index].setCsNm(node_list.item(7).getFirstChild().getNodeValue());
            Stations[station_index].setLat(Double.parseDouble(node_list.item(8).getFirstChild().getNodeValue()));
            Stations[station_index].setLongi(Double.parseDouble(node_list.item(9).getFirstChild().getNodeValue()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Stations[station_index].machines[0].setStatUpdateDateTime(sdf.parse(node_list.item(10).getFirstChild().getNodeValue()));
            Stations[station_index].setMachines_size(1);
            current_station_num++;
            station_index++;
        }
        Station_size = current_station_num;
    }

    public ChargeStationInfo[] getStations() {
        return Stations;
    }
    public int getStation_size() {
        return Station_size;
    }
    public int getTemp_size() {
        return temp_size;
    }
    public void setStations(ChargeStationInfo[] stations) {
        Stations = stations;
    }
    public void setStation_size(int station_size) {
        Station_size = station_size;
    }

    public ChargeStationInfo[] getSameStation(String name){
        int i = 0;
        ArrayList same = new ArrayList();
        for(i=0; i < Station_size; i++){
            if(name.equals(Stations[i].getCsNm())){
                same.add(i);
            }
        }
        temp_size = same.size();
        ChargeStationInfo[] temp = new ChargeStationInfo[same.size()];
        for(int j = 0; j < same.size();j++){
            temp[j] = new ChargeStationInfo();
            temp[j] = Stations[(int) same.get(j)];
        }

        return temp;
    }
}
