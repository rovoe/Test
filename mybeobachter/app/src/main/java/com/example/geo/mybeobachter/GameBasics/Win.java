package com.example.geo.mybeobachter.GameBasics;

import java.util.ArrayList;
import java.util.List;

public class Win {

	private List<Integer> listPlayerID = new ArrayList<Integer>();
	private Game401 game;
	
	Win(){
	}
	
	public List<Integer> getListPlayerID() {
		return listPlayerID;
	}
	
	public Game401 getGame() {
		return game;
	}
	
	public void setListPlayerID(List<Integer> listPlayerID) {
		this.listPlayerID = listPlayerID;
	}
	
	public void setGame(Game401 game) {
		this.game = game;
	}
	
}
