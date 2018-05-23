package com.aouadi.kata.tennis;

/**
 * 
 * @author Wael.Aouadi
 *
 */
public interface Match {

    /**
     * @return The player 1
     */
    Player getFirstPlayer();

    /**
     * @return the player 2
     */
    Player getSecondPlayer();

    /**
     * @return te match status
     */
    Status getStatus();

    /**
     * Gets the ongoing game
     *
     * @return the ongoing game if the game status is {@code Status.IN_PROGRESS}, otherwise returns {@code null}
     */
    Score.Game getCurrentGame();

    /**
     * Gets the ongoing set
     *
     * @return the ongoing set if the game status is {@code Status.IN_PROGRESS}, otherwise returns {@code null}
     */
    Score.Set getCurrentSet();

    /**
     * @param i the set number, i starts with 1;
     * @return the set score having the given number
     */
    Score.Set getSet(int i);

    /**
     * @return the sets size
     */
    int getSetCount();

    /**
     * Prints the match details to the standard output screen
     */
    void printDetails();

}
