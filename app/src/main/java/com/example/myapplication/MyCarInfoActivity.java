package com.example.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MyCarInfoActivity extends AppCompatActivity{

    String[] plugs;
    MyCarInfo carInfo = MyCarInfo.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        //내 차 이름
        TextView CarName = findViewById(R.id.MyCarNameInput);
        CarName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString() != "")
                {
                    carInfo.name = s.toString();
                }
            }
        });

        //내 차량 제조사
        TextView CarCompany = findViewById(R.id.MyCarCompanyInput);
        CarCompany.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString() != "")
                {
                    carInfo.Company = s.toString();
                }
            }
        });

        //내 차량 주행 가능 거리
        TextView CarRange = findViewById(R.id.MyCarRangeInput);
        CarRange.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString() != "0")
                {
                    carInfo.range = Integer.parseInt(s.toString());
                }
            }
        });


        //플러그 스피너 설정
        plugs = getResources().getStringArray(R.array.Charge_Plug);
        Spinner plugSpinner = findViewById(R.id.MyCarPlugInput);
        ArrayAdapter<String> plugAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,plugs);
        plugAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        plugSpinner.setAdapter(plugAdapter);
        plugSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                carInfo.cpTp = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}