package com.aouadi.kata.tennis;

/**
 * @author Wael.Aouadi
 * Mediator design pattern
 * Manages the score of a tennis match
 */
public interface MatchMediator {

    /**
     * @return he ongoing game score if the match is in progress, otherwise {@code null}
     */
    Score.Game getCurrentGame();

    /**
     * @return the match status
     */
    Status getStatus();

    /**
     * @param n the set number, it starts with {@code 1};
     * @return the set score at the given number
     */
    Score.Set scoreSetAt(int n);

    /**
     * @return the sets score size
     */
    int scoreSetCount();

    /**
     * Prints the match details to the the given {@link Match.Printer}
     */
    void printTo(Match.Printer printer);

    /**
     * Do the work of the given player wins a point
     * @param player the player
     */
    void winPoint(Player player);

    /**
     * @return is the match is over
     */
    boolean isOver();

}
