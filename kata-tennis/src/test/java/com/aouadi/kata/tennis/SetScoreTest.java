package com.aouadi.kata.tennis;

import com.aouadi.kata.tennis.impl.GameScore;
import com.aouadi.kata.tennis.impl.SetScore;
import com.aouadi.kata.tennis.impl.TieBreakerScore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 
 * @author Wael.Aouadi
 *
 */
public class SetScoreTest {

    @Test
    public void initialStateSetScore() {
        //GIVEN
        final SetScore setScore;
        //WHEN
        setScore = new SetScore();
        //THEN
        assertEquals(Player.Id.NONE, setScore.getWinner());
        assertEquals("(0-0)", setScore.toString());
        assertEquals("0-0", setScore.getCurrentGame().toString());
        assertEquals(GameScore.class, setScore.getCurrentGame().getClass());
    }

    @Test
    public void hasAdvantage() {
        //GIVEN deuce
        final SetScore setScore = TennisTestUtil.createSet(Player.Id.FIRST, 3, Player.Id.SECOND, 3);
        //WHEN
        setScore.winPoint(Player.Id.FIRST);
        //THEN
        assertEquals(Player.Id.NONE, setScore.getWinner());
        assertEquals("(0-0)", setScore.toString());
        assertEquals("Player 1 has advantage", setScore.getCurrentGame().toString());
    }

    @Test
    public void winAdvantage() {
        //GIVEN
        final SetScore setScore = TennisTestUtil.createSet(Player.Id.FIRST, 3, Player.Id.SECOND, 4);
        //WHEN
        setScore.winPoint(Player.Id.SECOND);
        //THEN
        assertEquals("(0-1)", setScore.toString());
        assertEquals("0-0", setScore.getCurrentGame().toString());
    }

    @Test
    public void loseAdvantage() {
        //GIVEN
        final SetScore setScore = TennisTestUtil.createSet(Player.Id.FIRST, 3, Player.Id.SECOND, 4);
        //WHEN
        setScore.winPoint(Player.Id.FIRST);
        //THEN
        assertEquals(Player.Id.NONE, setScore.getWinner());
        assertEquals("(0-0)", setScore.toString());
        assertEquals("deuce", setScore.getCurrentGame().toString());
    }

    @Test
    public void deuce() {
        //GIVEN
        final SetScore setScore = TennisTestUtil.createSet(Player.Id.FIRST, 2, Player.Id.SECOND, 3);
        //WHEN
        setScore.winPoint(Player.Id.FIRST);
        //THEN
        assertEquals(Player.Id.NONE, setScore.getWinner());
        assertEquals("(0-0)", setScore.toString());
        assertEquals("deuce", setScore.getCurrentGame().toString());
    }

    @Test
    public void winFirstGameAndStartNewGame() {
        //GIVEN 40-30
        final SetScore setScore = TennisTestUtil.createSet(Player.Id.FIRST, 3, Player.Id.SECOND, 2);
        //WHEN
        setScore.winPoint(Player.Id.FIRST);
        //THEN
        assertEquals(Player.Id.NONE, setScore.getWinner());
        assertFalse(setScore.isOver());
        assertEquals("(1-0)", setScore.toString());
        assertEquals("0-0", setScore.getCurrentGame().toString());
    }

    @Test(expected = TennisRuntimeException.class)
    public void playingAndSetOver() {
        //GIVEN (6-0)
        final SetScore setScore = TennisTestUtil.createSet(Player.Id.FIRST, 4 * 6);
        //WHEN
        setScore.winPoint(Player.Id.FIRST);
        //THEN
        //EXCEPTION
    }

    @Test
    public void winLastGameSetAndGameOver() {
        //GIVEN (5-1) 40-0
        final SetScore setScore = TennisTestUtil.createSet(Player.Id.FIRST, 4 * 5, Player.Id.SECOND, 4);
        TennisTestUtil.initSet(setScore, Player.Id.FIRST, 3);
        //WHEN
        setScore.winPoint(Player.Id.FIRST);
        //THEN (6-1)
        assertEquals(Player.Id.FIRST, setScore.getWinner());
        assertTrue(setScore.isOver());
        assertEquals("(6-1)", setScore.toString());
        assertNull(setScore.getCurrentGame());
    }

    @Test
    public void startTieBreakGame() {
        //GIVEN (5-6)
        final SetScore setScore = TennisTestUtil.createSet(Player.Id.FIRST, 4 * 5, Player.Id.SECOND, 4 * 6);
        //WHEN (6-6)
        TennisTestUtil.initSet(setScore, Player.Id.FIRST, 4);
        //THEN (6-6)
        assertEquals(Player.Id.NONE, setScore.getWinner());
        assertFalse(setScore.isOver());
        assertEquals("(6-6)", setScore.toString());
        assertEquals(TieBreakerScore.class, setScore.getCurrentGame().getClass());
        assertEquals("0-0", setScore.getCurrentGame().toString());
    }

    @Test
    public void tieBreakGame() {
        //GIVEN (6-6)
        final SetScore setScore = TennisTestUtil.createSet(Player.Id.FIRST, 4 * 5, Player.Id.SECOND, 4 * 6);
        TennisTestUtil.initSet(setScore, Player.Id.FIRST, 4);
        //WHEN 3-6
        TennisTestUtil.initSet(setScore, Player.Id.SECOND, 6);
        TennisTestUtil.initSet(setScore, Player.Id.FIRST, 3);
        //THEN
        assertEquals(Player.Id.NONE, setScore.getWinner());
        assertFalse(setScore.isOver());
        assertEquals("(6-6)", setScore.toString());
        assertEquals(TieBreakerScore.class, setScore.getCurrentGame().getClass());
        assertEquals("3-6", setScore.getCurrentGame().toString());
    }

    @Test
    public void winTieBreakGameHardly() {
        //GIVEN (6-6)
        final SetScore setScore = TennisTestUtil.createSet(Player.Id.FIRST, 4 * 5, Player.Id.SECOND, 4 * 6);
        TennisTestUtil.initSet(setScore, Player.Id.FIRST, 4);
        //WHEN 8-6
        TennisTestUtil.initSet(setScore, Player.Id.SECOND, 6);
        TennisTestUtil.initSet(setScore, Player.Id.FIRST, 8);
        //THEN
        assertEquals(Player.Id.FIRST, setScore.getWinner());
        assertTrue(setScore.isOver());
        assertEquals("(7-6)", setScore.toString());
        assertNull(setScore.getCurrentGame());
    }

    @Test
    public void winTieBreakGameEasily() {
        //GIVEN (6-6)
        final SetScore setScore = TennisTestUtil.createSet(Player.Id.FIRST, 4 * 5, Player.Id.SECOND, 4 * 6);
        TennisTestUtil.initSet(setScore, Player.Id.FIRST, 4);
        //WHEN 7-0
        TennisTestUtil.initSet(setScore, Player.Id.FIRST, 7);
        //THEN
        assertEquals(Player.Id.FIRST, setScore.getWinner());
        assertTrue(setScore.isOver());
        assertEquals("(7-6)", setScore.toString());
        assertNull(setScore.getCurrentGame());
    }
}
