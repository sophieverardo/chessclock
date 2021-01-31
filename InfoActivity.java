package com.example.chessclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        ImageButton back= (ImageButton)findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try{
                    finish();
                }catch(Error e)
                {
                    Log.d("timer","Errore torna indietro");}
            }
        });
    }
}