package com.example.chessclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {

    TextView b1,b5,b10,b30,b40,b50,bSecondi, bMinuti,bOre;
    String smo="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        b1=(TextView)findViewById(R.id.btn1);
        b5=(TextView)findViewById(R.id.btn5);
        b10=(TextView)findViewById(R.id.btn10);
        b30=(TextView)findViewById(R.id.btn30);
        b40=(TextView)findViewById(R.id.btn40);
        b50=(TextView)findViewById(R.id.btn50);
        bSecondi =(TextView) findViewById(R.id.btnSecondi);
        bMinuti =(TextView) findViewById(R.id.btnMinuti);
        bOre =(TextView) findViewById(R.id.btnOre);

        VisibleSMO();

        //in base al bottone (secondi/ minuti/ ore) scelto, cambio la scritta di ogni bottone
        bSecondi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                smo="secondi";
                InvisibleSMO(smo);
            }
        });

        bMinuti.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                smo="minuti";
                InvisibleSMO(smo);
            }
        });

        bOre.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                smo="ore";
                InvisibleSMO(smo);
            }
        });

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StartActivity.timer=true;           //controllo di aver premuto un bottone con il tempo
                MainActivity.setTextPartecipante1(b1.getText().toString(),true);
                MainActivity.setTextPartecipante2(b1.getText().toString(),true);
                finish();
            }
        });

        b5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StartActivity.timer=true;
                MainActivity.setTextPartecipante1(b5.getText().toString(),true);
                MainActivity.setTextPartecipante2(b5.getText().toString(),true);
                finish();
            }
        });
        b10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StartActivity.timer=true;
                MainActivity.setTextPartecipante1(b10.getText().toString(),true);
                MainActivity.setTextPartecipante2(b10.getText().toString(),true);
                finish();
            }
        });
        b30.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StartActivity.timer=true;
                MainActivity.setTextPartecipante1(b30.getText().toString(),true);
                MainActivity.setTextPartecipante2(b30.getText().toString(),true);
                finish();
            }
        });
        b40.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StartActivity.timer=true;
                MainActivity.setTextPartecipante1(b40.getText().toString(),true);
                MainActivity.setTextPartecipante2(b40.getText().toString(),true);
                finish();
            }
        });
        b50.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StartActivity.timer=true;
                MainActivity.setTextPartecipante1(b50.getText().toString(),true);
                MainActivity.setTextPartecipante2(b50.getText().toString(),true);
                finish();
            }
        });
    }


    public void InvisibleSMO(String smo)
    {
        if(smo=="secondi")
        {
            b1.setText("00:00:01:00");
            b5.setText("00:00:05:00");
            b10.setText("00:00:10:00");
            b30.setText("00:00:20:00");
            b40.setText("00:00:40:00");
            b50.setText("00:00:50:00");
        }
        else
        {
            if(smo=="minuti")
            {
                b1.setText("00:01:00:00");
                b5.setText("00:05:00:00");
                b10.setText("00:10:00:00");
                b30.setText("00:20:00:00");
                b40.setText("00:40:00:00");
                b50.setText("00:50:00:00");
            }
            else
            {
                if(smo=="ore")
                {
                    b1.setText("01:00:00:00");
                    b5.setText("2:00:00:00");
                    b10.setText("3:00:00:00");
                    b30.setText("4:00:00:00");
                    b40.setText("5:00:00:00");
                    b50.setText("6:00:00:00");
                }
            }
        }
        bSecondi.setEnabled(false);
        bSecondi.setVisibility(View.INVISIBLE);
        bMinuti.setEnabled(false);
        bMinuti.setVisibility(View.INVISIBLE);
        bOre.setEnabled(false);
        bOre.setVisibility(View.INVISIBLE);

        b1.setEnabled(true);
        b1.setVisibility(View.VISIBLE);
        b5.setEnabled(true);
        b5.setVisibility(View.VISIBLE);
        b10.setEnabled(true);
        b10.setVisibility(View.VISIBLE);
        b30.setEnabled(true);
        b30.setVisibility(View.VISIBLE);
        b40.setEnabled(true);
        b40.setVisibility(View.VISIBLE);
        b50.setEnabled(true);
        b50.setVisibility(View.VISIBLE);

    }

    public void VisibleSMO()
    {
        bSecondi.setEnabled(true);
        bSecondi.setVisibility(View.VISIBLE);
        bMinuti.setEnabled(true);
        bMinuti.setVisibility(View.VISIBLE);
        bOre.setEnabled(true);
        bOre.setVisibility(View.VISIBLE);

        b1.setEnabled(false);
        b1.setVisibility(View.INVISIBLE);
        b5.setEnabled(false);
        b5.setVisibility(View.INVISIBLE);
        b10.setEnabled(false);
        b10.setVisibility(View.INVISIBLE);
        b30.setEnabled(false);
        b30.setVisibility(View.INVISIBLE);
        b40.setEnabled(false);
        b40.setVisibility(View.INVISIBLE);
        b50.setEnabled(false);
        b50.setVisibility(View.INVISIBLE);

    }
}