package com.example.geo.mybeobachter.GameBasics;

import java.util.ArrayList;
import java.util.List;

public class Games {

    private int gameID;
    private String gameName;
    private int gamestate;
    private Boolean isTournament;
    private Config config;

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    List<Player> players = new ArrayList<Player>();

    public Games(int gameID, String gameName, int gamestate, Boolean isTournament, Config config, List<Player> players) {
        this.gameID = gameID;
        this.gameName = gameName;
        this.gamestate = gamestate;
        this.isTournament = isTournament;
        this.players = players;
        this.config = config;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getGamestate() {
        return gamestate;
    }

    public void setGamestate(int gamestate) {
        this.gamestate = gamestate;
    }

    public Boolean getTournament() {
        return isTournament;
    }

    public void setTournament(Boolean tournament) {
        isTournament = tournament;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

}
