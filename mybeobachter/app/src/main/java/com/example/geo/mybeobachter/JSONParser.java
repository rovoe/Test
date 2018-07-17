package com.example.geo.mybeobachter;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {

	/**
	 * Creates the JSONObject JoinRequest.
	 *
	 * @param playerName The desired player name.
	 * @param playerType The type of the player. [1: Player, 2: Observer, 3: KI]
	 * @return a JSONObject representing the JoinRequest.
	 * @throws JSONException If creating the JSONObject fails.
	 */
	public JSONObject createServerJoinRequest(String playerName, int playerType) throws JSONException {
		JSONObject joinRequest = new JSONObject();
		joinRequest.put("ID", 100);

		JSONObject joinRequestData = new JSONObject();
		joinRequestData.put("playerName", playerName);
		joinRequestData.put("playerType", playerType);

		joinRequest.put("data", joinRequestData);
		return joinRequest;
	}

	/**
	 * Creates the JSONObject LeaveRequest.
	 *
	 * @param reason The reason of leaving the server.
	 * @return a JSONObject representing the LeaveRequest.
	 * @throws JSONException If creating the JSONObject fails.
	 */
	public JSONObject createLeaveRequest(String reason) throws JSONException {
		JSONObject leaveRequest = new JSONObject();
		leaveRequest.put("ID", 200);

		JSONObject leaveRequestData = new JSONObject();
		leaveRequestData.put("reason", reason);

		leaveRequest.put("data", leaveRequestData);
		return leaveRequest;
	}

	/**
	 * Creates the JSONObject GameListRequest.
	 *
	 * @return a JSONObject representing the GameListRequest.
	 * @throws JSONException If creating the JSONObject fails.
	 */
	public JSONObject createGameListRequest() throws JSONException {
		JSONObject gameListRequest = new JSONObject();
		gameListRequest.put("ID", 300);

		JSONObject data = new JSONObject();

		gameListRequest.put("data", data);


		return gameListRequest;
	}

	/**
	 * Creates the JSONObject GameJoinRequest.
	 *
	 * @param gameId The id of the game to enter.
	 * @return a JSONObject representing the GameJoinRequest.
	 * @throws JSONException If creating the JSONObject fails.
	 */
	public JSONObject createGameJoinRequest(int gameId) throws JSONException {
		JSONObject gameJoinRequest = new JSONObject();
		gameJoinRequest.put("ID", 302);

		JSONObject gameJoinRequestData = new JSONObject();
		gameJoinRequestData.put("gameID", gameId);

		gameJoinRequest.put("data", gameJoinRequestData);
		return gameJoinRequest;
	}

	/**
	 * Creates the JSONObject GameLeaveRequest.
	 *
	 * @param reason The reason of leaving the game.
	 * @return a JSONObject representing the GameLeaveRequest.
	 * @throws JSONException If creating the JSONObject fails.
	 */
	public JSONObject createGameLeaveRequest(String reason) throws JSONException {
		JSONObject gameLeaveRequest = new JSONObject();
		gameLeaveRequest.put("ID", 410);

		JSONObject gameLeaveRequestData = new JSONObject();
		gameLeaveRequestData.put("reason", reason);

		gameLeaveRequest.put("data", gameLeaveRequestData);
		return gameLeaveRequest;
	}

	/**
	 * Creates the JSONObject Turn.
	 *
	 * @param tileID The if of the tile that will be changed this turn.
	 * @param x The X-coordinate of the tile.
	 * @param y The Y-coordinate of the tile.
	 * @param rotation The rotation of the tile.
	 * @param meeplePosition The position of the meeple.
	 * @param meepleOwner The owner of the meeple.
	 * @return a JSONObject representing the Turn.
	 * @throws JSONException If creating the JSONObject fails.
	 */

	public JSONObject createTurn(int tileID, int x, int y, int rotation, int meeplePosition, int meepleOwner) throws JSONException  {
		JSONObject turn = new JSONObject();
		turn.put("ID", 403);
		JSONObject turnData = new JSONObject();

		JSONObject tile = new JSONObject();
		tile.put("tileID", tileID);
		tile.put("x", x);
		tile.put("y", y);
		tile.put("rotation", rotation);
		tile.put("meeplePosition", meeplePosition);
		tile.put("meepleOwner", meepleOwner);

		turnData.put("tile", tile);

		turn.put("data", turnData);
		return turn;
	}

	/**
	 * Creates the JSONObject Chat.
	 *
	 * @param message The outgoing message.
	 * @return a JSONObject representing the Chat.
	 * @throws JSONException If creating the JSONObject fails.
	 */
	public JSONObject createChat(String message) throws JSONException {
		JSONObject chat = new JSONObject();
		chat.put("ID", 202);

		JSONObject chatData = new JSONObject();
		chatData.put("message", message);

		chat.put("data", chatData);
		return chat;
	}

	/**
	 * Creates the JSONObject HighscoreRequest.
	 *
	 * @return a JSONObject representing the HighscoreRequest.
	 * @throws JSONException If creating the JSONObject fails.
	 */
	public JSONObject createHighscoreRequest()  throws JSONException {
		JSONObject highscoreRequest = new JSONObject();
		highscoreRequest.put("ID", 204);
		highscoreRequest.put("data", "");

		return highscoreRequest;
	}
}