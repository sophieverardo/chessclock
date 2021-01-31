package com.example.chessclock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class FinishGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_game);

        TextView txt= (TextView)findViewById(R.id.txtRigioco);
        TextView txt2= (TextView)findViewById(R.id.txtRigioco2);
        txt2.setVisibility(View.INVISIBLE);
        TextView Nome= (TextView)findViewById(R.id.txtNome);
        if(getIntent().getExtras()!=null)
        {
            String nome=getIntent().getExtras().getString("nomepartecipante");
            Nome.setText(nome+": has finished the seconds!");
        }

        TextView no=(TextView)findViewById(R.id.txtNo);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt.getVisibility()==View.VISIBLE)
                    finishAffinity();
                else
                {
                    MainActivity.setTextPartecipante1(MainActivity.R1,true);
                    MainActivity.setTextPartecipante2(MainActivity.R2,true);
                    finish();}
            }
        });

        TextView si=(TextView)findViewById(R.id.txtSi);
        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt.getVisibility()==View.VISIBLE)
                {   txt2.setVisibility(View.VISIBLE);
                    txt.setVisibility(View.INVISIBLE);}
                else
                    startActivity(new Intent(FinishGame.this,MainActivity.class));

            }
        });
    }
}