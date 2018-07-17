package com.example.geo.mybeobachter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;

import android.widget.Toast;


public class MainActivity extends Activity {

    ImageButton loginButton;
    EditText nameEditText;
    JSONParser jsonParser = new JSONParser();
    JSONObject messageReceiver;
    Globals g;
    ConnectTask ct;
    ProgressDialog progressDialog;


    private View.OnClickListener OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            final Thread loginThread = new Thread() {

                public void run() {
                    //Show ProcessDialog for waiting till messageReceiver updated from ConnectTask (Message Received)
                    //Sending ServerJoinRequest Id: 100
                    if (g.getCt().getmTcpClient() != null) {
                        //Parsing JSONObject Id:100
                        try {
                            JSONObject message = jsonParser.createServerJoinRequest(nameEditText.getText().toString(), 1);
                            if (message != null)
                                ct.getmTcpClient().sendMessage(message.toString());
                        } catch (JSONException e) {
                            Log.e("JSON", "S: createServerJoin Request JSONObject ", e);
                        }
                    }

                    //wait for getMessageReceiver is not NUll

                    try {

                        ct.setWaitThread(this);
                        synchronized (this) {
                            while (ct.getMessageReceiver() == null) {
                                this.wait();
                            }

                            Log.d("JSON", "S: " + ct.getMessageReceiver().toString());

                            if (ct.getMessageReceiver().getJSONObject("data").getBoolean("accepted") == false) {
                                //TODO:
                            } else {


                                g.getCt().setMessageReceiver(null);


                                if (g.getCt().getmTcpClient() != null) {
                                    //GameListRequest
                                    try {
                                        JSONObject message = jsonParser.createGameListRequest();
                                        if(message != null)
                                            ct.getmTcpClient().sendMessage(message.toString());
                                    }
                                    catch(JSONException e){
                                        Log.e("JSON", "S: createGameListRequest Object ", e);
                                    }

                                }

                                    ct.setWaitThread(this);
                                    synchronized (this) {
                                        while (ct.getMessageReceiver() == null) {
                                            this.wait();
                                        }

                                    }


                                Intent intent = new Intent(MainActivity.this, LobbyActivity.class);
                                    intent.putExtra("games",ct.getMessageReceiver().toString());
                                //Start Loccy activity
                                startActivity(intent);
                            }
                        }
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        Log.e("JSON", "S: Message Receive Error Boolean", e);
                    }


                }
            };

            loginThread.start();

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Singleton Class for global Variable ConnectTask
        g = Globals.getInstance();
        //Starting ConnectTask with Async TcpClient
        g.setCt(new ConnectTask());
        ct = g.getCt();
        ct.execute();

        //Init variable
        loginButton = (ImageButton) findViewById(R.id.imageButton);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        loginButton.setOnClickListener(OnClickListener);
        progressDialog = new ProgressDialog(MainActivity.this);

    }


}