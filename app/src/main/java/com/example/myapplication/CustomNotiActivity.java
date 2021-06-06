package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class CustomNotiActivity extends Activity {
    TextView btn1;
    TextView title;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_noti);


        Intent intent = getIntent();

        String title_string = intent.getExtras().getString("station_name");
        String charges = intent.getExtras().getString("charger_info");
        String url = intent.getExtras().getString("kakao");

        btn1 = (TextView)findViewById(R.id.btn1);
        title = (TextView)findViewById(R.id.atdTitleTv);
        info = (TextView)findViewById(R.id.atdContentTv);

        title.setText(title_string);
        info.setText(charges);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(it);

                finish();
            }
        });
    }
}
