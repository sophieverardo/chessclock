package com.example.chessclock;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyService extends Service {
    private static final String TAG = "VerardoExampleService";
    private Thread t;
    int ore, minuti, secondi, millisecondi;
    // indica se il servizio e’ attivo
    private static boolean isRunning  = false;

    private int i;

    // costruttore
    public MyService() {
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "Service onCreate");
    }

    // collegamento con altre APP non necessario (ma il metodo onBind va implementato cmq)
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "Service onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Service onStartCommand");
        String durata= intent.getExtras().getString("durata");
        String p= intent.getExtras().getString("nomepartecipante");
        String tempo[]= durata.split(":");
        //divido il tempo totale, portandolo in millisecondi
        int temp=Integer.parseInt(tempo[3])+Integer.parseInt(tempo[2])*1000+Integer.parseInt(tempo[1])*60*1000+Integer.parseInt(tempo[0])*60*60*1000;
        int partecipante=intent.getExtras().getInt("partecipante");

        isRunning = true;
        // Qui viene avviato il thread che implementa il servizio in background:

        t= new Thread(new Runnable() {     //mostra un alert dialog allo scadere del tempo
            @Override
            public void run() {

                // Oggetto utilizzato per comunicare con il thread proncipale (UIThread)
                Handler handler = new Handler(Looper.getMainLooper());


                String fullstops = "";
                //Your logic that service will perform will be placed here
                //In this example we are just looping and waits for 1000 milliseconds in each loop.
                for (i = temp; i >0; i--) {
                    if(i==1) {
                        //emetto il suono nel momento in cui il timer finisce e incomincio una nuova activity
                        handler.post(new Runnable() { // the runnable object
                            @Override
                            public void run() {
                                Log.d(TAG,"zero");
                                Intent finish=new Intent(MyService.this, FinishGame.class);
                                finish.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                 finish.putExtra("nomepartecipante",p);
                                 final MediaPlayer sound=MediaPlayer.create(MyService.this,R.raw.sound);
                                 sound.start();
                                startActivity(finish);
                            }
                        });
                    }

                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                    }

                    fullstops = fullstops + ".";

                    if(isRunning){
                        Log.i(TAG, "Service running"+fullstops);
                    }
                    else{

                        break;
                    }
                    // il metodo post serve a inviare un messaggio al UIThread
                    // (in questo caso viene inviato un oggetto eseguibile cioè Runnable)
                    handler.post(new Runnable() { // the runnable object
                        @Override
                        public void run() {
                            ore = i / (1000 * 60 * 60);
                            int a = i-(ore * 1000 * 60 * 60);
                            minuti = a / (1000 * 60);
                            int b= a-(minuti * 1000 * 60 );
                            secondi = b / 1000;
                            millisecondi=b-(secondi*1000);
                            if(partecipante==1)
                                 MainActivity.setTextPartecipante1(ore+":"+minuti+":"+secondi+":"+millisecondi,false);
                            else
                                MainActivity.setTextPartecipante2(ore+":"+minuti+":"+secondi+":"+millisecondi,false);
                        }
                    });


                }

                //Stop service once it finishes its task
                stopSelf();
            }
        });
        t.start();
        return Service.START_STICKY;
    }
    @Override
    public void onDestroy() {
        Log.i(TAG, "Service onDestroy");
        isRunning = false;

    }
}