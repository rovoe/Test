package com.example.geo.mybeobachter.GameBasics;

import com.example.geo.mybeobachter.GameBasics.*;

import java.util.ArrayList;
import java.util.List;


public class    Config {

    private int maxPlayers;
    private List<Tile> deck = new ArrayList<Tile>();

    public Config(int maxPlayers, List<Tile> deck, Tile startTile, int meeple, int time, int penaltyTurn, int penaltyTime, int pointLoss) {
        this.maxPlayers = maxPlayers;
        this.deck = deck;
        this.startTile = startTile;
        this.meeple = meeple;
        this.time = time;
        this.penaltyTurn = penaltyTurn;
        this.penaltyTime = penaltyTime;
        this.pointLoss = pointLoss;
    }

    private Tile startTile;
    private int meeple;
    private int time;
    private int penaltyTurn;
    private int penaltyTime;
    private int pointLoss;

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public List<Tile> getDeck() {
        return deck;
    }

    public void setDeck(List<Tile> deck) {
        this.deck = deck;
    }

    public Tile getStartTile() {
        return startTile;
    }

    public void setStartTile(Tile startTile) {
        this.startTile = startTile;
    }

    public int getMeeple() {
        return meeple;
    }

    public void setMeeple(int meeple) {
        this.meeple = meeple;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getPenaltyTurn() {
        return penaltyTurn;
    }

    public void setPenaltyTurn(int penaltyTurn) {
        this.penaltyTurn = penaltyTurn;
    }

    public int getPenaltyTime() {
        return penaltyTime;
    }

    public void setPenaltyTime(int penaltyTime) {
        this.penaltyTime = penaltyTime;
    }

    public int getPointLoss() {
        return pointLoss;
    }

    public void setPointLoss(int pointLoss) {
        this.pointLoss = pointLoss;
    }




}
