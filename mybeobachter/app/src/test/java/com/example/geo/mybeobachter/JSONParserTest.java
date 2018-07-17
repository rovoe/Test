package com.example.geo.mybeobachter;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JSONParserTest {

    JSONParser testParser;

    @Before
    public void setUp() throws Exception {
        testParser = new JSONParser();
    }

    @Test
    public void createServerJoinRequest() throws JSONException {
        JSONObject testJSON;
        testJSON = testParser.createServerJoinRequest("createServerJoinRequest",1);
        assertEquals(100, testJSON.get("ID"));
        assertEquals("createServerJoinRequest", testJSON.getJSONObject("data").getString("playerName"));
        assertEquals(1, testJSON.getJSONObject("data").get("playerType"));
    }

    @Test
    public void createLeaveRequest() throws  JSONException {
        JSONObject testJSON;
        testJSON = testParser.createLeaveRequest("toxic behaviour");
        assertEquals(200, testJSON.get("ID"));
        assertEquals("toxic behaviour", testJSON.getJSONObject("data").getString("reason"));
    }

    @Test
    public void createGameListRequest() throws  JSONException {
        JSONObject testJSON;
        testJSON = testParser.createGameListRequest();
        assertEquals(300, testJSON.get("ID"));
    }

    @Test
    public void createGameJoinRequest() throws  JSONException {
        JSONObject testJSON;
        testJSON = testParser.createGameJoinRequest(123);
        assertEquals(302, testJSON.get("ID"));
        assertEquals(123, testJSON.getJSONObject("data").get("gameID"));
    }

    @Test
    public void createGameLeaveRequest() throws  JSONException {
        JSONObject testJSON;
        testJSON = testParser.createGameLeaveRequest("l2p");
        assertEquals(410, testJSON.get("ID"));
        assertEquals("l2p", testJSON.getJSONObject("data").getString("reason"));
    }

    @Test
    public void createTurn() throws  JSONException {
        JSONObject testJSON;
        testJSON = testParser.createTurn(02, 4,5, 3, 7,1);
        assertEquals(403, testJSON.get("ID"));
        assertEquals(02, testJSON.getJSONObject("data").getJSONObject("tile").get("tileID"));
        assertEquals(4, testJSON.getJSONObject("data").getJSONObject("tile").get("x"));
        assertEquals(5, testJSON.getJSONObject("data").getJSONObject("tile").get("y"));
        assertEquals(3, testJSON.getJSONObject("data").getJSONObject("tile").get("rotation"));
        assertEquals(7, testJSON.getJSONObject("data").getJSONObject("tile").get("meeplePosition"));
        assertEquals(1, testJSON.getJSONObject("data").getJSONObject("tile").get("meepleOwner"));
    }

    @Test
    public void createChat() throws  JSONException {
        JSONObject testJSON;
        testJSON = testParser.createChat("Never gonna give you up");
        assertEquals(202, testJSON.get("ID"));
        assertEquals("Never gonna give you up", testJSON.getJSONObject("data").getString("message"));
    }

    @Test
    public void createHighscoreRequest() throws  JSONException {
        JSONObject testJSON;
        testJSON = testParser.createHighscoreRequest();
        assertEquals(204, testJSON.get("ID"));
    }

    @Test
    public void exceptionCreateServerJoinRequest() {

    }

    @Test
    public void exceptionCreateLeaveRequest() {
    }

    @Test
    public void exceptionCreateGameListRequest() {
    }

    @Test
    public void exceptionCreateGameJoinRequest() {
    }

    @Test
    public void exceptionCreateGameLeaveRequest() {
    }

    @Test
    public void exceptionCreateTurn() {
    }

    @Test
    public void exceptionCreateChat() {
    }

    @Test
    public void exceptionCreateHighscoreRequest() {
    }
}

