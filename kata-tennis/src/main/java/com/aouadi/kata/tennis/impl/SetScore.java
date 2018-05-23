package com.aouadi.kata.tennis.impl;

import com.aouadi.kata.tennis.Player;
import com.aouadi.kata.tennis.Score;
import com.aouadi.kata.tennis.TennisRuntimeException;

/**
 * 
 * @author Wael.Aouadi
 *
 */
public class SetScore implements Score.Set {

    /**
     * Number of games won by each player
     */
    private int[] gamesWon = {0, 0};

    /**
     * Current game score
     */
    private Score.Game currentGame;

    public SetScore() {
        startGame();
    }

    public boolean winPoint(Player.Id pid) {
        if (currentGame == null) {
            throw new TennisRuntimeException("The set is over.");
        }
        if (currentGame.winPoint(pid)) {
            // Current game is over
            startGame();
        }
        return this.isOver();
    }

    public Player.Id getWinner() {
        if (this.isSetOver()) {
            return (gamesWon[0] > gamesWon[1]) ? Player.Id.FIRST : Player.Id.SECOND;
        }
        return Player.Id.NONE;
    }

    public Score.Game getCurrentGame() {
        return currentGame;
    }

    /**
     * @return {@code false} if the set is ongoing, otherwise {@code true}
     */
    public boolean isOver() {
        return currentGame == null;
    }

    @Override
    public CharSequence toCharSequence() {
        return new StringBuilder().append('(').append(gamesWon[0]).append('-').append(gamesWon[1]).append(')');
    }

    @Override
    public String toString() {
        return toCharSequence().toString();
    }

    private void startGame() {
        if (currentGame != null && currentGame.getWinner() != Player.Id.NONE) {
            ++gamesWon[currentGame.getWinner().ordinal()];
        }
        if (!this.isSetOver()) {
            //Depending on the set score, we apply the selected strategy
            if (isCombination(6, 6)) {
                currentGame = new TieBreakerScore();
            } else {
                currentGame = new GameScore();
            }
        } else {
            // Set is over
            currentGame = null;
        }
    }

    private boolean isSetOver() {
        if (isCombination(6, 7)) {
            // Case Tie-Break
            if (currentGame instanceof TieBreakerScore) {
                return currentGame.isOver();
            }
            // Case (currentGame == null)
            return true;
        } else {
            final int max = Math.max(gamesWon[0], gamesWon[1]);
            if (max < 6) {
                return false;
            }
            final int min = Math.min(gamesWon[0], gamesWon[1]);
            return max - min > 1;
        }
    }

    private boolean isCombination(int i, int j) {
        return gamesWon[0] == i && gamesWon[1] == j || gamesWon[0] == j && gamesWon[1] == i;
    }

}
