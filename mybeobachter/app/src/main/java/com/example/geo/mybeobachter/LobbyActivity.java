package com.example.geo.mybeobachter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LobbyActivity extends Activity {

    TcpClient mTcpClient;
    ExpandableListView listView;
    ConnectTask ct;
    Globals g;
    JSONParser jsonParser = new JSONParser();
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ImageButton exitButton;
    ImageButton joinButton;
    JSONArray games;
    JSONObject selectedGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);


        listView =  (ExpandableListView) findViewById(R.id.listView);
        g = Globals.getInstance();
        ct = g.getCt();
        final List<String> game_list = new ArrayList<String>();
        exitButton = (ImageButton) findViewById(R.id.exitButton);
        joinButton = (ImageButton) findViewById(R.id.joinButton);
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();


                    // Create a List from String Array elements

                    try {
                         games = new JSONObject(getIntent().getStringExtra("games")).getJSONObject("data").getJSONArray("games");

                        for (int i = 0; i < games.length(); i++) {

                            listDataHeader.add(games.getJSONObject(i).getString("gameName"));
                            Log.d("Test neu", games.getJSONObject(i).getString("gameName"));
                            List<String> gameinfo = new ArrayList<String>();

                            gameinfo.add("Game ID: " + games.getJSONObject(i).getInt("gameID"));
                            gameinfo.add("Game state: " + games.getJSONObject(i).getInt("gameState"));
                            gameinfo.add("Tournament: " + games.getJSONObject(i).getBoolean("isTournament"));
                            if(games.getJSONObject(i).getJSONArray("players").length()>0) {
                                gameinfo.add("Players: ");
                                for (int j = 0; j < games.getJSONObject(i).getJSONArray("players").length(); j++) {
                                    JSONObject player = games.getJSONObject(i).getJSONArray("players").getJSONObject(j);
                                    gameinfo.add(player.getInt("playerID") + " " + player.getString("playerName") + " " + player.getInt("playerType")
                                    );
                                }
                            }
                            gameinfo.add("Config: ");
                            JSONObject config = games.getJSONObject(i).getJSONObject("config");
                            gameinfo.add("Max player: " + config.getInt("maxPlayer"));
                            gameinfo.add("Meeple: " + config.getInt("meeple"));
                            gameinfo.add("Time: " + config.getInt("time"));
                            gameinfo.add("Penalty Turn: " + config.getInt("penaltyTurn"));
                            gameinfo.add("Penalty Time: " + config.getInt("penaltyTime"));
                            gameinfo.add("Point loss: " + config.getInt("pointLoss"));

                            listDataChild.put(listDataHeader.get(i), gameinfo);

                        }


                    }catch (JSONException e) {
                e.printStackTrace();
            }


            JSONObject gamelist = ct.getMessageReceiver();



         View.OnClickListener joinButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Thread joinThread = new Thread() {

                    public void run() {


                        //Show ProcessDialog for waiting till messageReceiver updated from ConnectTask (Message Received)
                        //Sending ServerJoinRequest Id: 100
                        if (g.getCt().getmTcpClient() != null) {
                            //Parsing JSONObject Id:100
                            try {

                                //TODO: Parser benutzen
                                JSONObject message = new JSONObject();
                                message.put("ID",302);
                                JSONObject data = new JSONObject();

                                int position = 0;
                                for(int i = 0;i <99;i++){
                                    if(listView.isGroupExpanded(i)){
                                        position = i;
                                    }

                                }


                                data.put("gameID",games.getJSONObject(position).getInt("gameID"));
                                message.put("data",data);

                                //get config from chosen Game

                                selectedGame = games.getJSONObject(position);

                                if (message != null)
                                    ct.getmTcpClient().sendMessage(message.toString());
                            } catch (JSONException e) {
                                Log.e("JSON", "S: createGameJoinRequest Exception ", e);
                            }

                        }

                        //wait for getMessageReceiver is not NUll
                        ct.setMessageReceiver(null);

                        try {
                            ct.setWaitThread(this);
                            synchronized (this) {
                                while (ct.getMessageReceiver() == null) {
                                    this.wait();
                                }

                            }


                            if (ct.getMessageReceiver().getJSONObject("data").getBoolean("accepted") == false) {
                                //TODO:

                            } else {



                                /*
                                    ct.setMessageReceiver(null);
                                    ct.setWaitThread(this);
                                    synchronized (this) {
                                        while (ct.getMessageReceiver() == null) {

                                            //TODO: Progress Dialog für Loading und  Game befindet sich im Spiel oder wartet auf Game start
                                            if(ct.getMessageReceiver().getInt("ID") == 400 && ct.getMessageReceiver().getInt("ID") == 401){
                                                break;
                                            }
                                            else{
                                                ct.setMessageReceiver(null);
                                                ct.setWaitThread(this);
                                                this.wait();
                                            }
                                            this.wait();

                                        }
                                          JSONObject response400 = ct.getMessageReceiver();
                                    }*/


                                Intent intent = new Intent(LobbyActivity.this, GameActivity.class);
                                intent.putExtra("response400", selectedGame.toString());
                                //Start Loccy activity
                                startActivity(intent);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            Log.e("JSON", "S: Message Receive Error Boolean", e);
                        }


                    }
                };

                joinThread.start();

            }
        };


        joinButton.setOnClickListener(joinButtonListener);



        // Create an ArrayAdapter from List
        ExpandableListAdapter  listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        // DataBind ListView with items from ArrayAdapter
        listView.setAdapter(listAdapter);



       //Show only one child of expandable list at a time
        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;
            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousGroup)
                    listView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });

    }
}


