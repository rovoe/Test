package com.example.geo.mybeobachter.GameBasics;

public class Tile {

	private int tileID;
	private int tileCount;
	// added by JL
	private int x;
	private int y;
	private int rotation;
	private int meeplePosition;
	private int meepleOwner;
	private int playerID;

	// added von Server

	private int[] kartenaufbau = new int[12];
	private boolean wappen = false;

	public Tile(int tileID, int tileCount) {
		this.tileID = tileID;
		this.tileCount = tileCount;
	}

	// Constructor added by JL
	public Tile() {

	}

	/* Constructor und kartenaufbau/wappen added aus der Server Klasse Tile, 
	 * braucht wahrscheinlich noch Ueberarbeitung
	*/
	public Tile(int tileID, int rotation, int meeplepos, int meepleOwner) {

		this.tileID = tileID;
		this.rotation = rotation;
		this.meeplePosition = meeplepos;
		this.meepleOwner = meepleOwner;

		// Wiese 0; Weg 1; Burg 2; Kolster 3;
		switch (this.tileID) {
		case 0:
			int[] karte_0 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3 };
			this.kartenaufbau = karte_0;

		case 1:
			int[] karte_1 = { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 3 };
			this.kartenaufbau = karte_1;

		case 2:
			int[] karte_2 = { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
			this.kartenaufbau = karte_2;
			this.wappen = true;

		case 3:
			int[] karte_3 = { 2, 2, 2, 2, 2, 2, 0, 0, 0, 2, 2, 2, 2 };
			this.kartenaufbau = karte_3;

		case 4:
			int[] karte_4 = { 2, 2, 2, 2, 2, 2, 0, 0, 0, 2, 2, 2, 2 };
			this.kartenaufbau = karte_4;
			this.wappen = true;

		case 5:
			int[] karte_5 = { 2, 2, 2, 2, 2, 2, 0, 1, 0, 2, 2, 2, 2 };
			this.kartenaufbau = karte_5;

		case 6:
			int[] karte_6 = { 2, 2, 2, 2, 2, 2, 0, 1, 0, 2, 2, 2, 2 };
			this.kartenaufbau = karte_6;
			this.wappen = true;

		case 7:
			int[] karte_7 = { 2, 2, 2, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0 };
			this.kartenaufbau = karte_7;

		case 8:
			int[] karte_8 = { 2, 2, 2, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0 };
			this.kartenaufbau = karte_8;
			this.wappen = true;

		case 9:
			int[] karte_9 = { 2, 2, 2, 0, 1, 0, 0, 1, 0, 2, 2, 2, 1 };
			this.kartenaufbau = karte_9;

		case 10:
			int[] karte_10 = { 2, 2, 2, 0, 1, 0, 0, 1, 0, 2, 2, 2, 1 };
			this.kartenaufbau = karte_10;
			this.wappen = true;

		case 11:
			int[] karte_11 = { 0, 0, 0, 2, 2, 2, 0, 0, 0, 2, 2, 2, 2 };
			this.kartenaufbau = karte_11;

		case 12:
			int[] karte_12 = { 0, 0, 0, 2, 2, 2, 0, 0, 0, 2, 2, 2, 2 };
			this.kartenaufbau = karte_12;
			this.wappen = true;

		case 13:
			int[] karte_13 = { 2, 2, 2, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0 };
			this.kartenaufbau = karte_13;

		case 14:
			int[] karte_14 = { 2, 2, 2, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0 };
			this.kartenaufbau = karte_14;

		case 15:
			int[] karte_15 = { 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			this.kartenaufbau = karte_15;

		case 16:
			int[] karte_16 = { 2, 2, 2, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1 };
			this.kartenaufbau = karte_16;

		case 17:
			int[] karte_17 = { 2, 2, 2, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1 };
			this.kartenaufbau = karte_17;

		case 18:
			int[] karte_18 = { 2, 2, 2, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1 };
			this.kartenaufbau = karte_18;

		case 19:
			int[] karte_19 = { 2, 2, 2, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1 };
			this.kartenaufbau = karte_19;

		case 20:
			int[] karte_20 = { 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 };
			this.kartenaufbau = karte_20;

		case 21:
			int[] karte_21 = { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1 };
			this.kartenaufbau = karte_21;

		case 22:
			int[] karte_22 = { 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1 };
			this.kartenaufbau = karte_22;

		case 23:
			int[] karte_23 = { 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1 };
			this.kartenaufbau = karte_23;

			/*
			 * EventuellBestrafung fuer falsch gesetzte Meeple hier Lieber beim
			 * Server, wo falsch angelegte Karten auch bestraft werden
			 * 
			 */

			/*
			 * Eventuell Rotation berechnen mit (rotationsint* 3) mod 12
			 */

		}

	}

	public void setTileID(int tileID) {
		this.tileID = tileID;
	}

	public void setTileCount(int tileCount) {
		this.tileCount = tileCount;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public void setMeeplePosition(int meeplePosition) {
		this.meeplePosition = meeplePosition;
	}

	public void setMeepleOwner(int meepleOwner) {
		this.meepleOwner = meepleOwner;
	}

	public int getTileID() {
		return tileID;
	}

	public int getTileCount() {
		return tileCount;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRotation() {
		return rotation;
	}

	public int getMeeplePosition() {
		return meeplePosition;
	}

	public int getMeepleOwner() {
		return meepleOwner;
	}

	public int[] getKartenaufbau() {
		return kartenaufbau;
	}

	public void setKartenaufbau(int[] kartenaufbau) {
		this.kartenaufbau = kartenaufbau;
	}

	public boolean getWappen() {
		return wappen;
	}

	public void setWappen(boolean wappen) {
		this.wappen = wappen;
	}

	public int getPlayerID(){
		return this.playerID;
	}
	
	public void setPlayerID(int playerID){
		this.playerID = playerID;
	}
	
}
