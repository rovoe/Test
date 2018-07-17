package com.example.geo.mybeobachter.GameBasics;

public class Player {

	int playerId;
	String playerName;
	int playerType;
	// added by JL
	int playerScore;
	int playerMeeple;

	public Player(int playerId, String playerName, int playerType) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.playerType = playerType;
	}

	public int getPlayerId() {
		return playerId;

	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getPlayerType() {
		return playerType;
	}

	public void setPlayerType(int playerType) {
		this.playerType = playerType;
	}

	public int getPlayerScore() {
		return playerScore;
	}

	public int getPlayerMeeple() {
		return playerMeeple;
	}

	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}

	public void setPlayerMeeple(int playerMeeple) {
		this.playerMeeple = playerMeeple;
	}

}
