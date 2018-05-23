package com.aouadi.kata.tennis.impl;

import com.aouadi.kata.tennis.Player;
import com.aouadi.kata.tennis.Score;

/**
 * @author Wael.Aouadi
 * Strategy Design Pattern
 * Represents a tie-break game (or tiebreaker).
 * The tie-break game continues until one player wins seven points by a margin of two or more points
 */
public class TieBreakerScore implements Score.Game {

    /**
     * Number of points won by each player
     */
    private int[] pointsWon = {0, 0};

    @Override
    public boolean winPoint(Player.Id pid) {
        ++pointsWon[pid.ordinal()];
        return isOver();
    }

    @Override
    public Player.Id getWinner() {
        if (this.isOver()) {
            return (pointsWon[0] > pointsWon[1]) ? Player.Id.FIRST : Player.Id.SECOND;
        }
        return Player.Id.NONE;
    }

    @Override
    public boolean isOver() {
        final int max = Math.max(pointsWon[0], pointsWon[1]);
        if (max < 7) {
            return false;
        }
        final int min = Math.min(pointsWon[0], pointsWon[1]);
        return max - min > 1;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(pointsWon[0]).append('-').append(pointsWon[1]).toString();
    }

    public int[] getPointsWon() {
        return pointsWon;
    }
}