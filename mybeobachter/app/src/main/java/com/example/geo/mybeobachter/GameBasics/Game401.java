package com.example.geo.mybeobachter.GameBasics;

import java.util.ArrayList;
import java.util.List;

import com.example.geo.mybeobachter.GameBasics.Tile;

public class Game401 {

	private List<Player> players = new ArrayList<Player>();
	private List<Tile> deck = new ArrayList<Tile>();
	private List<Tile> tiles = new ArrayList<Tile>();
	private Tile lastTile = new Tile();

	public Game401() {
	}

	public List<Player> getPlayers() {
		return players;
	}

	public List<Tile> getDeck() {
		return deck;
	}

	public List<Tile> getTiles() {
		return tiles;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public void setDeck(List<Tile> deck) {
		this.deck = deck;
	}

	public void setTiles(List<Tile> tiles) {
		this.tiles = tiles;
	}

	public Tile getLastTile() {
		return lastTile;
	}

	public void setLastTile(Tile lastTile) {
		this.lastTile = lastTile;
	}

}
