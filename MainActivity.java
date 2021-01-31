package com.example.chessclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static TextView txtp1, txtp2, Nomep1, Nomep2;       //sono i txt con i nomi dei partecipanti e il tempo di ciascuno di loro
    public static String R1="",R2="";                           //serve per memorizzare con quale tempo l'utente ha deciso di giocare
    private boolean stop=true;                                  //serve per vedere che la persona abbia fermato il timer
    private static String Tag="Main";

    public static void setNomiPartecipanti(String s1,String s2)
    {
        Nomep1.setText(s1);
        Nomep2.setText(s2);
    }
    public static void setTextPartecipante1(String s, boolean revalue)
    {   if(revalue ==true)
            R1=s;
        txtp1.setText(s);
    }
    public static void setTextPartecipante2(String s, boolean revalue)
    {   if(revalue ==true)
            R2=s;
        txtp2.setText(s);}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Nomep1=(TextView)findViewById(R.id.txtNomeP1);
        Nomep2=(TextView)findViewById(R.id.txtNomeP2);
        txtp2=(TextView)findViewById(R.id.txtpa2);
        txtp2.setBackgroundColor(Color.parseColor("#09102D"));//blue
        txtp1=(TextView)findViewById(R.id.txtpa1);
        txtp1.setBackgroundColor(Color.parseColor("#D73528")); //red

        Intent i = new Intent(MainActivity.this, StartActivity.class);
        startActivity(i);                                           //servizio intent che porta alla StartActivity

        txtp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    stop=false;
                    ColorDrawable colorbutton= (ColorDrawable) txtp1.getBackground();       //per settare il colore delle due txt
                    int color=colorbutton.getColor();
                    if (color==Color.parseColor("#D73528"))
                    {
                        txtp1.setBackgroundColor(Color.parseColor("#09102D"));//blu
                        txtp2.setBackgroundColor(Color.parseColor("#D73528"));//rosso
                        onClickStopService(v);
                        onClickStartService(v,txtp2.getText().toString(),2,Nomep2.getText().toString());

                    }
                    else
                        Log.d(Tag,"Partecipante1: errore/sbagliato bottone");

                }catch(Error e)
                {Log.d(Tag,"errore partecipante 1 txt");}
                }
        });

        txtp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    stop=false;
                    ColorDrawable colorbutton= (ColorDrawable) txtp1.getBackground();
                    int color=colorbutton.getColor();
                    if (color==Color.parseColor("#09102D"))
                    {
                        txtp2.setBackgroundColor(Color.parseColor("#09102D"));//blu
                        txtp1.setBackgroundColor(Color.parseColor("#D73528"));//rosso
                        onClickStopService(v);
                        onClickStartService(v,txtp1.getText().toString(),1,Nomep1.getText().toString());
                    }
                    else
                        Log.d(Tag,"Partecipante2: errore/sbagliato bottone");

                }catch(Error e)
                {Log.d(Tag,"Errore partecipante 2 txt");}
             }
        });

        ImageButton Start =(ImageButton)findViewById(R.id.btnStart);
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    stop=false;
                    ColorDrawable colorbutton= (ColorDrawable) txtp1.getBackground();
                    int color=colorbutton.getColor();
                    if(color==Color.parseColor("#D73528"))
                        onClickStartService(v,txtp1.getText().toString(),1,Nomep1.getText().toString());
                    else
                        onClickStartService(v,txtp2.getText().toString(),2,Nomep2.getText().toString());
                }catch(Exception e)
                { Log.d(Tag,"Errore nel bottone start");}
            }
        });

        ImageButton Stop =(ImageButton)findViewById(R.id.btnStop);
        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    stop=true;
                onClickStopService(v);
                }catch(Exception e)
                { Log.d(Tag,"Errore nel bottone stop");}
            }
        });

        ImageButton Restore=(ImageButton) findViewById(R.id.btnRestore);
        Restore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try{
                    if(stop==true || txtp1.getText().toString().equals("0:0:0:0") || txtp2.getText().toString().equals("0:0:0:0"))
                    {
                        setTextPartecipante1(R1,true);
                        setTextPartecipante2(R2,true);
                    }
                   else
                       Log.d(Tag,"non hai fermato il timer");

                }catch(Exception e)
                {Log.d(Tag,"Errore Info");}
            }
        });

        ImageButton Info=(ImageButton) findViewById(R.id.btnInfo);
        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(stop==true){
                    Intent Info= new Intent(MainActivity.this,InfoActivity.class);
                    startActivity(Info);}

                }catch(Exception e)
                {Log.d(Tag,"Errore Info");}
            }
        });

        ImageButton Timer=(ImageButton) findViewById(R.id.btnTime);
        Timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(stop==true)
                    {
                        Intent Time= new Intent(MainActivity.this, TimerActivity.class);
                        startActivity(Time);
                    }
                   else
                        Log.d(Tag,"non hai fermato il timer");
                }catch(Exception e)
                {Log.d(Tag,"Errore Info");}
            }
        });

    }

    public void onClickStartService(View widget, String tempo, int partecipante, String p)
    {
        Intent service=new Intent(MainActivity.this, MyService.class);
        service.putExtra("durata",tempo);
        service.putExtra("partecipante",partecipante);          //mi serve per verificare per quale partecipante sto eseguendo il thread
        service.putExtra("nomepartecipante",p);                 //serve per inviarlo all'activity finale
        startService(service);
    }

    // arresto del servizio
    public void onClickStopService(View widget)
    {
        Intent service=new Intent(MainActivity.this, MyService.class);
        stopService(service);
    }
}