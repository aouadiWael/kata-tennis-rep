package com.aouadi.kata.tennis;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * 
 * @author Wael.Aouadi
 *
 */
public class MatchTest {

    private Match match;
    private Player player1;
    private Player player2;

    @Before
    public void initMatch() {
        match = MatchFactory.createMatch("WAO", "AAB");
        player1 = match.getFirstPlayer();
        player2 = match.getSecondPlayer();
    }

    @Test
    public void easyMatch() {
        //GIVEN
        //WHEN
        TennisTestUtil.winGame(player1, 6);
        TennisTestUtil.winGame(player1, 6);
        TennisTestUtil.winGame(player1, 6);

        //THEN
        assertEquals(Status.PLAYER1_WINS, match.getStatus());
    }

    @Test(expected = TennisException.class)
    public void matchOverException() {
        //GIVEN :: Match Over
        TennisTestUtil.winGame(player1, 6);
        TennisTestUtil.winGame(player1, 6);
        TennisTestUtil.winGame(player1, 6);

        //WHEN
        player2.winPoint();

        //THEN
        //TennisException
    }

    @Test
    public void example1() {
        //GIVEN Score : (6 -1) (5-7) (1-0)
        TennisTestUtil.winGame(player1, 6, player2, 1);
        TennisTestUtil.winGame(player1, 5, player2, 7);
        TennisTestUtil.winGame(player1, 1, player2, 0);

        //WHEN
        player2.winPoint();
        player1.winPoint();
        player2.winPoint();
        //THEN
        assertEquals(Status.IN_PROGRESS, match.getStatus());
        assertEquals(3, match.getSetCount());

        Assert.assertEquals(Player.Id.FIRST, match.getSet(1).getWinner());
        Assert.assertEquals(Player.Id.SECOND, match.getSet(2).getWinner());
        Assert.assertEquals(Player.Id.NONE, match.getSet(3).getWinner());

        assertEquals("(6-1)", match.getSet(1).toString());
        assertEquals("(5-7)", match.getSet(2).toString());
        assertEquals("(1-0)", match.getSet(3).toString());

        assertNotNull(match.getCurrentGame());
        assertEquals("15-30", match.getCurrentGame().toString());
        Assert.assertEquals(Player.Id.NONE, match.getCurrentGame().getWinner());
    }

    @Test
    public void deuceGame() {
        //GIVEN (40:40)
        TennisTestUtil.winBall(player1, 3, player2, 3);

        //WHEN
        for (int i = 0; i < 10; i++) {
            player2.winPoint();
            player1.winPoint();
        }
        //THEN
        assertEquals(Status.IN_PROGRESS, match.getStatus());

        assertEquals(1, match.getSetCount());
        assertEquals("(0-0)", match.getSet(1).toString());

        assertNotNull(match.getCurrentGame());
        assertEquals("deuce", match.getCurrentGame().toString());
        Assert.assertEquals(Player.Id.NONE, match.getCurrentGame().getWinner());
    }

    @Test
    public void advantagePingPongGame() {
        //GIVEN (40:30)
        TennisTestUtil.winBall(player1, 3, player2, 2);
        //WHEN
        for (int i = 0; i < 10; i++) {
            player2.winPoint();
            player1.winPoint();
        }
        //THEN
        assertEquals(Status.IN_PROGRESS, match.getStatus());

        assertEquals(1, match.getSetCount());
        assertEquals("(0-0)", match.getSet(1).toString());

        assertNotNull(match.getCurrentGame());
        assertEquals("Player 1 has advantage", match.getCurrentGame().toString());
        Assert.assertEquals(Player.Id.NONE, match.getCurrentGame().getWinner());
    }

    @Test
    public void advantageLastGameInSet() {
        //GIVEN :: SinglePlayer 1 has advantage
        TennisTestUtil.winGame(player1, 5, player2, 4);
        TennisTestUtil.winBall(player1, 3, player2, 3);
        player1.winPoint();

        //WHEN
        player1.winPoint();
        //THEN
        assertEquals(Status.IN_PROGRESS, match.getStatus());

        assertEquals(2, match.getSetCount());
        assertEquals("(6-4)", match.getSet(1).toString());

        assertNotNull(match.getCurrentGame());
        assertEquals("0-0", match.getCurrentGame().toString());
    }

    @Test
    public void matchOver() {
        //WHEN
        TennisTestUtil.winGame(player1, 4, player2, 6);
        TennisTestUtil.winGame(player1, 3, player2, 6);
        TennisTestUtil.winGame(player1, 6, player2, 2);
        TennisTestUtil.winGame(player1, 6, player2, 1);
        TennisTestUtil.winGame(player1, 0, player2, 6);
        //THEN
        assertTrue(match.getStatus().equals(Status.PLAYER2_WINS));
    }

    @Test
    public void printDetailsInProgress() {
        //GIVEN
        TennisTestUtil.winGame(player1, 4, player2, 6);
        TennisTestUtil.winGame(player1, 3, player2, 6);
        TennisTestUtil.winGame(player1, 3, player2, 5);
        TennisTestUtil.winBall(player1, 3, player2, 1);
        final String expected = "Player 1 : WAO\n" +
                "Player 2 : AAB\n" +
                "Score : (4-6)(3-6)(3-5)\n" +
                "Current game status : 40-15\n" +
                "Match Status : In progress";
        //THEN
        //Start capturing
        final PrintStream standard = System.out;
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        match.printDetails();
        // Stop capturing
        System.setOut(standard);

        //THEN
        assertTrue(match.getStatus().equals(Status.IN_PROGRESS));
        assertEquals(expected, buffer.toString());
    }

    @Test
    public void printDetailsWithWinner() {
        //GIVEN
        TennisTestUtil.winGame(player1, 4, player2, 6);
        TennisTestUtil.winGame(player1, 3, player2, 6);
        TennisTestUtil.winGame(player1, 1, player2, 6);
        final String expected = "Player 1 : WAO\n" +
                "Player 2 : AAB\n" +
                "Score : (4-6)(3-6)(1-6)\n" +
                "Match Status : Player 2 wins";
        //THEN
        //Start capturing
        final PrintStream standard = System.out;
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        match.printDetails();
        // Stop capturing
        System.setOut(standard);

        //THEN
        assertTrue(match.getStatus().equals(Status.PLAYER2_WINS));
        assertEquals(expected, buffer.toString());
    }
}
