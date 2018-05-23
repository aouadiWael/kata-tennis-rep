package com.aouadi.kata.tennis;

import java.util.stream.Stream;

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
     * @return the ongoing set score if the match is in progress, otherwise {@code null}
     */
    Score.Set getCurrentSet();

    /**
     * @return the number of sets over and also the ongoing one if exists
     */
    int getSetCount();

    /**
     * Finds the set matches the given index
     * @param i the set index starts with 1
     * @return the matched set
     * @throws IndexOutOfBoundsException if the given index is invalid
     */
    Score.Set getSet(int i);

    /**
     * Stream the sets score
     */
    Stream<Score.Set> streamSet();

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
