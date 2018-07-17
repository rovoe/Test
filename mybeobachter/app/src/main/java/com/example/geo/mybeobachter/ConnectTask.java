package com.example.geo.mybeobachter;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * ConnectTask holds the background task to organise communication
 * with the server.
 */
public class ConnectTask extends AsyncTask<String, String, TcpClient>  {

    TcpClient mTcpClient;

    public Runnable getGameRunnable() {
        return gameRunnable;
    }

    public void setGameRunnable(Runnable gameRunnable) {
        this.gameRunnable = gameRunnable;
    }

    Runnable gameRunnable;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    Handler handler;

    /**
     * Getter and setter for the message receiver. A JSON object which
     * holds game data.
     *
     * @param messageReceiver
     *              The JSON
     */
    public void setMessageReceiver(JSONObject messageReceiver) {
        this.messageReceiver = messageReceiver;
    }

    public JSONObject getMessageReceiver() {
        return messageReceiver;
    }

    JSONObject messageReceiver;

    /**
     * Getter and setter to access the thread used in activities to organise
     * waiting for game data.
     *
     * @return
     *          The thread
     */
    public Thread getWaitThread() {
        return waitThread;
    }

    public void setWaitThread(Thread waitThread) {
        this.waitThread = waitThread;
    }

    Thread waitThread;

    /**
     * Getter for the TCP Client from async task which is called from class Globals.
     * @return
     *          TCP Client
     */
    public TcpClient getmTcpClient() {
        return mTcpClient;
    }

    /**
     * Standard implementation for Async Task (1st step).
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * A background task is started with the TCP Client. The method publishProgress(message)
     * from AsyncTask updates the progress(2nd step).
     *
     * @param message
     *              Message string which will converted to JSON later on.
     * @return
     *              Null
     */
    @Override
    protected TcpClient doInBackground(String... message) {

        //create a TCPClient object
        mTcpClient = new TcpClient(new TcpClient.OnMessageReceived() {
            @Override
            //here the messageReceived method is implemented
            public void messageReceived(String message) {
                //this method calls the onProgressUpdate
                publishProgress(message);
            }
        });
        mTcpClient.run();

        return null;
    }

    /**
     * Invoked after doInBackground(...) call. JSON object from the newly received String
     * is being created.
     * @param values
     *              Received String from the server.
     */
    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        //response received from server
        try {
            messageReceiver = new JSONObject(values[0]);

            if(getWaitThread()!=null) {
                synchronized (getWaitThread()) {
                    getWaitThread().notifyAll();
                }
            }

        }
        catch(JSONException e){
            Log.e("JSON", "S: Message Receive Error", e);
        }

        Log.d("ConnectTask", "response " + values[0]);

    }

}