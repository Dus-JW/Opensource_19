package com.example.myapplication;

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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class SearchByAddress {
    private StringBuilder xmlString;

    public SearchByAddress(String searchString) throws IOException {
        this.xmlString = APISearch(searchString);
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
        System.out.println("Response code: " + conn.getResponseCode());
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


    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        StringBuilder t = new StringBuilder();
        t =APISearch("서울특별시 성북구");
        InputSource is = new InputSource(new StringReader(t.toString()));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(is);

        Element root = document.getDocumentElement();
        NodeList item = root.getElementsByTagName("item");

        for(int i = 0; i < item.getLength(); i++){
            Node node = item.item(i);
            NodeList node_list = node.getChildNodes();
            for(int j = 0; j<node_list.getLength();j++){
                System.out.println(node_list.item(j).getNodeName());
                System.out.println(node_list.item(j).getFirstChild().getNodeValue());
            }
            System.out.println("-----------------------------------------");
        }
    }



}
