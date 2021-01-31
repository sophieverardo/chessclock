package com.example.chessclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    public static Boolean timer=false;  //cnontrollo se dalla TimerActivity l'utente ha scelto un tempo iniziale
    String Tag="Start";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        timer=false;
        ImageButton Info=(ImageButton) findViewById(R.id.btnInfo);
        TextView txtpt1 = (TextView)findViewById(R.id.txtP1);
        TextView txtpt2 = (TextView)findViewById(R.id.txtP2);
        TextView txtT = (TextView)findViewById(R.id.tctTime);
        TextView txtGame = (TextView)findViewById(R.id.txtGame);
        TextView txtStart = (TextView)findViewById(R.id.txtInfoTop);
        Visible(txtT,txtGame,txtpt1,txtpt2,txtStart,Info);

        txtGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try{
                    if(timer==true)
                        nonVisible(txtT,txtGame,txtpt1,txtpt2,txtStart,Info);
                    else
                        Toast.makeText(StartActivity.this,"you still need to choose a time",Toast.LENGTH_LONG).show();
                }catch(Error e)
                {
                    Log.d(Tag,"Errore selezione bottone per scelta timer");}
            }
        });


        txtT.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try{
                    Intent i = new Intent(StartActivity.this, TimerActivity.class);
                    startActivity(i);
                }catch(Error e)
                {
                    Log.d(Tag,"Errore selezione bottone per scelta timer");}
            }
        });


        txtStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try{
                    if(!txtpt1.getText().toString().isEmpty() && !txtpt2.getText().toString().isEmpty())
                    {
                        MainActivity.setNomiPartecipanti(txtpt1.getText().toString(),txtpt2.getText().toString());  //setto i nomi delle txt della MainActivity
                        finish();
                    }
                    else
                        Toast.makeText(StartActivity.this,"write the name of the two partecipants",Toast.LENGTH_LONG).show();
                }catch(Error e)
                {
                    Log.d(Tag,"Errore selezione bottone per scelta timer");}
            }
        });


        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent Info= new Intent(StartActivity.this,InfoActivity.class);
                    startActivity(Info);

                }catch(Exception e)
                {Log.d(Tag,"Errore Info");}
            }
        });

    }
    private void Visible(TextView txtT,TextView txtGame, TextView txtpt1, TextView txtpt2, TextView txtStart, ImageButton info)
    {
        txtT.setEnabled(true);
        txtT.setVisibility(View.VISIBLE);
        txtGame.setEnabled(true);
        txtGame.setVisibility(View.VISIBLE);
        txtpt1.setEnabled(false);
        txtpt1.setVisibility(View.INVISIBLE);
        txtpt2.setEnabled(false);
        txtpt2.setVisibility(View.INVISIBLE);
        txtStart.setEnabled(false);
        txtStart.setVisibility(View.INVISIBLE);
        info.setEnabled(true);
        info.setVisibility(View.VISIBLE);
    }

    private void nonVisible(TextView txtT,TextView txtGame, TextView txtpt1, TextView txtpt2, TextView txtStart,ImageButton info)
    {
        txtT.setEnabled(false);
        txtT.setVisibility(View.INVISIBLE);
        txtGame.setEnabled(false);
        txtGame.setVisibility(View.INVISIBLE);
        txtpt1.setEnabled(true);
        txtpt1.setVisibility(View.VISIBLE);
        txtpt2.setEnabled(true);
        txtpt2.setVisibility(View.VISIBLE);
        txtStart.setEnabled(true);
        txtStart.setVisibility(View.VISIBLE);
        info.setEnabled(true);
        info.setVisibility(View.INVISIBLE);
    }
}