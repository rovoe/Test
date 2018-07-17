package com.example.geo.mybeobachter;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class BoardTest {

    Context testContext;
    Board testBoard;
    HashMap<Integer, Integer> testPlayerMeeple;

    @Before
    public void setUp(){
        testContext = InstrumentationRegistry.getTargetContext();
        testBoard = new Board(testContext, 51,50, testPlayerMeeple);
    }

    // Getter and setter for testing purposes only, are never used in the program itself.
    @Test
    public void test_getBoard_height() {
        assertEquals(true, testBoard.getBoard_height()==51);
    }

    @Test
    public void test_setBoard_height() {
        testBoard.setBoard_height(51);
        assertTrue(testBoard.getBoard_height()==51);
        assertFalse(testBoard.getBoard_height()==50);
    }

    @Test
    public void test_getBoard_width() {
        assertTrue(testBoard.getBoard_width()==50);
        assertFalse(testBoard.getBoard_height()==40);
    }

    @Test
    public void test_setBoard_width() {
        testBoard.setBoard_width(51);
        assertTrue(testBoard.getBoard_width()==51);
        assertFalse(testBoard.getBoard_width()==50);
    }

    @Test
    public void test_getTiles() {
        int[][][] actualTiles = testBoard.getTiles();
        assertEquals(-1, actualTiles[49][48][0]);
        assertEquals(0, actualTiles[49][48][1]);
        Log.i("Value at actualTiles[49][48][0]", String.valueOf(actualTiles[49][48][1]));
    }

    @Test
    public void test_addTiles() throws IndexOutOfBoundsException {
        int[][][] actualTiles = testBoard.getTiles();
        testBoard.addTile(03, 49,49, 0, 1, 2);
        assertEquals(3, actualTiles[49][49][0]);
        assertEquals(0, actualTiles[49][49][1]);
        assertEquals(1, actualTiles[49][49][2]);
        assertEquals(2, actualTiles[49][49][3]);
    }

}