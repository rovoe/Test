package com.example.geo.mybeobachter;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class GameActivity extends Activity {


    JSONParser jsonParser = new JSONParser();
    JSONObject messageReceiver;
    Globals g;
    ConnectTask ct;
    Board board;
    Boolean gameOn = false;
    JSONObject selectedGame;
    int tileoffset;
    int tilecount;
    JSONObject config;
    TextView playerNameinfo;
    TextView playerScoreinfo;
    TextView playerMeepleinfo;
    HashMap<Integer, String> players = new HashMap<Integer, String>();
    HashMap<Integer, Integer> playerMeeple = new HashMap<Integer, Integer>();
    JSONArray playerJSON;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //Singleton Class for global Variable ConnectTask
        g = Globals.getInstance();
        //Starting ConnectTask with Async TcpClient
        ct = g.getCt();

        playerNameinfo = (TextView) findViewById(R.id.textViewPlayername);
        playerScoreinfo = (TextView) findViewById(R.id.textViewPlayerscore);
        playerMeepleinfo = (TextView) findViewById(R.id.textViewPlayerMeeple);

        playerNameinfo.setMovementMethod(new ScrollingMovementMethod());
        playerScoreinfo.setMovementMethod(new ScrollingMovementMethod());
        playerMeepleinfo.setMovementMethod(new ScrollingMovementMethod());

        final ZoomLayout zoomlayout = findViewById(R.id.zoomlayout);

        //init players



        JSONArray deck = null;
        try {


            selectedGame = new JSONObject(getIntent().getStringExtra("response400"));

            Log.d("response400: ", selectedGame.toString());


            config = selectedGame.getJSONObject("config");

            deck = config.getJSONArray("deck");

            playerJSON = selectedGame.getJSONArray("players");

            for(int j = 0 ; j < playerJSON.length(); j++){

                players.put(playerJSON.getJSONObject(j).getInt("playerID"),playerJSON.getJSONObject(j).getString("playerName"));
                playerMeeple.put(playerJSON.getJSONObject(j).getInt("playerID"),j);
            }




            tilecount = 0;
            for (int i = 0; i < deck.length(); i++) {
                tilecount = tilecount + deck.getJSONObject(i).getInt("tileCount");
                Log.d("Tilecount", tilecount + "");
            }

            if (tilecount % 2 == 0) {
                int temp = tilecount;
                while (temp % 2 == 0) {
                    temp = temp + 1;
                }
                tileoffset = (tilecount / 2) + 1;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }




        int anzahlkarten = tilecount;
        Log.d("Offset", tileoffset+"");



        board = new Board(GameActivity.this, anzahlkarten, anzahlkarten, playerMeeple);

        // get config information

        try {
            board.addTile(config.getJSONObject("startTile").getInt("tileID"), tileoffset, tileoffset,config.getJSONObject("startTile").getInt("rotation"), -1, 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        zoomlayout.addView(board);


        final Thread gameThread = new Thread() {

            Boolean gameObjectnotFound = true;
            JSONObject gameWinner = null;
            JSONObject gameObject;
            JSONObject gameObjectTemp = null;

            public void run() {

                try {

                    outerloop:
                    while (true) {

                        innterloop:
                        while (true) {

                            ct.setMessageReceiver(null);
                            ct.setWaitThread(this);

                            synchronized (this) {
                                while (ct.getMessageReceiver() == null) {
                                    this.wait();
                                }

                                if (ct.getMessageReceiver().getInt("ID") == 401) {
                                    gameObject = ct.messageReceiver;
                                    break innterloop;

                                }

                                if (ct.getMessageReceiver().getInt("ID") == 405) {
                                    gameWinner = ct.getMessageReceiver();
                                    break outerloop;

                                }

                            }

                        }


                        runOnUiThread(new Runnable() {
                            public void run() {
                                if (gameObject != gameObjectTemp) {
                                    gameObjectTemp = gameObject;
                                    createBoard(gameObject);
                                    getGameinfo(gameObject);
                                    board.invalidate();
                                }
                            }

                        });


                    }


                    runOnUiThread(new Runnable() {
                        public void run() {

                            try {

                                JSONObject data = gameWinner.getJSONObject("data");
                                JSONArray winner = data.getJSONArray("winner");

                                Toast.makeText(getApplicationContext(), "The winner is:" + players.get(winner.getJSONObject(0).getInt("playerID")), Toast.LENGTH_SHORT).show();

                            }
                            catch(Exception e){

                            }
                        }


                    });

                } catch (InterruptedException e) {

                } catch (JSONException e) {

                }

            }

        };

        gameThread.start();


    }

    private  void createBoard(JSONObject object) {
        try {
            JSONObject data = object.getJSONObject("data");
            JSONObject game = data.getJSONObject("game");
            JSONArray tiles = game.getJSONArray("tiles");

            for (int i = 0; i < tiles.length(); i++) {

                board.addTile(tiles.getJSONObject(i).getInt("tileID"),
                        tileoffset + tiles.getJSONObject(i).getInt("x"),
                        tileoffset - tiles.getJSONObject(i).getInt("y"),
                        tiles.getJSONObject(i).getInt("rotation"),
                        tiles.getJSONObject(i).getInt("meeplePosition"),
                        tiles.getJSONObject(i).getInt("meepleOwner"));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getGameinfo(JSONObject object) {
        try {
            JSONObject data = object.getJSONObject("data");
            JSONObject game = data.getJSONObject("game");
            JSONArray players = game.getJSONArray("players");
            playerNameinfo.setText("");
            playerScoreinfo.setText("");
            playerMeepleinfo.setText("");

            for(int i = 0; i<players.length();i++){
                players.getJSONObject(i).get("playerName");
                playerNameinfo.append("\n"+players.getJSONObject(i).get("playerName"));
                playerScoreinfo.append("\n"+players.getJSONObject(i).getInt("playerScore"));
                playerMeepleinfo.append("\n"+players.getJSONObject(i).getInt("playerMeeple"));

            }




        } catch (JSONException e) {
            e.printStackTrace();
        }

    }




}

