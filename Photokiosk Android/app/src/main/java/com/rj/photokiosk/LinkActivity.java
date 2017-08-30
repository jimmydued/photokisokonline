package com.rj.photokiosk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class LinkActivity extends AppCompatActivity {

    TextView name,link;

    private AppCompatButton appCompatButtonLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);

        name = (TextView) findViewById(R.id.name);

        appCompatButtonLogout = (AppCompatButton) findViewById(R.id.appCompatButtonLogout);


        //String strName = getIntent().getExtras().getString("name");
        SharedPreferences getpref = getApplicationContext().getSharedPreferences("MyPref", 0);
        final SharedPreferences.Editor editor = getpref.edit();
        String strName = getpref.getString("name", null);

        String[] strArray = strName.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String s : strArray) {
            String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
            builder.append(cap + " ");
        }
        name.setText("Logged in user : "+builder.toString());

        link = (TextView) findViewById(R.id.link);

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.photokioskonline.com"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        appCompatButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear();
                editor.commit();
                finish();
            }
        });

    }
}
