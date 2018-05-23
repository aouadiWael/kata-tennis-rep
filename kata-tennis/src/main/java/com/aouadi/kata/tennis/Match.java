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
     * @param n the set number, it starts with {@code 1};
     * @return the set score at the given number
     */
    Score.Set scoreSetAt(int n);

    /**
     * @return the sets score size
     */
    int scoreSetCount();

    /**
     * Prints the match details to the the given {@link Printer}
     */
    void printTo(Printer printer);

    @FunctionalInterface
    interface Printer {
        void print(String details);
    }
}
