package com.example.geo.mybeobachter;

public class Globals{
    private static Globals instance;

    // Global variable
    private ConnectTask ct;

    // Restrict the constructor from being instantiated
    private Globals(){}

    public void setCt(ConnectTask ct){
        this.ct=ct;
    }
    public ConnectTask getCt(){
        return this.ct;
    }

    public static synchronized Globals getInstance(){
        if(instance==null){
            instance=new Globals();
        }
        return instance;
    }
}