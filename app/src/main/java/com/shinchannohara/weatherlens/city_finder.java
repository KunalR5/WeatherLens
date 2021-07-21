package com.shinchannohara.weatherlens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class city_finder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_finder);


        ImageView back = (ImageView)findViewById(R.id.backbutton);
        final EditText searchcity = (EditText)findViewById(R.id.searchcity);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchcity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                String newcity = searchcity.getText().toString();
                startActivity(new Intent(city_finder.this,MainActivity.class).putExtra("City",newcity));

                return false;
            }
        });
    }
}
