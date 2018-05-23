package com.aouadi.kata.tennis;

/**
 * @author Wael.Aouadi
 * Score Interface represents the Basic Contract of any Score
 */
public interface Score {

    /**
     * The player having the given {@code pid} wins a point
     *
     * @return if the game is finished, other false
     */
    boolean winPoint(Player.Id pid);

    /**
     * @return The formatted score, basically used in print score details
     */
    CharSequence toCharSequence();

    /**
     * @return The Winner Id if the Score is ongoing, otherwise {@code Player.Id.NONE}
     */
    Player.Id getWinner();

    /**
     * @return {@code false} if the Score is ongoing, otherwise {@code true}
     */
    boolean isOver();

    /**
     * Game Score Contract
     */
    interface Game extends Score {
    }

    /**
     * Set Score Contract
     */
    interface Set extends Score {

        /**
         * @return {@code null} if the set is over, otherwise the ongoing Game
         */
        Game getCurrentGame();
    }

}
