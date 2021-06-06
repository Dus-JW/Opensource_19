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
    TextView CarName = findViewById(R.id.MyCarNameInput);
    TextView CarCompany = findViewById(R.id.MyCarCompanyInput);
    TextView CarRange = findViewById(R.id.MyCarRangeInput);
    Spinner plugSpinner = findViewById(R.id.MyCarPlugInput);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        refreshCarInfoSetting();

        //내 차 이름
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
        ArrayAdapter<String> plugAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,plugs);
        plugAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        plugSpinner.setAdapter(plugAdapter);
        plugSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                carInfo.cpTp = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        refreshCarInfoSetting();
    }

    protected void refreshCarInfoSetting()
    {
        CarName.setText(carInfo.name);
        CarCompany.setText(carInfo.Company);
        CarRange.setText(carInfo.range);
        plugSpinner.setSelection(carInfo.cpTp);
    }
}