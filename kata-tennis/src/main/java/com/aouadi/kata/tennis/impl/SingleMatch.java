package com.aouadi.kata.tennis.impl;

import com.aouadi.kata.tennis.*;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Wael.Aouadi
 * 
 * Represents a tennis match of two single players
 */
public class SingleMatch implements Match {

    private final Player player1;
    private final Player player2;
    private final MatchMediator mediator;

    public SingleMatch(Player player1, Player player2, MatchMediator mediator) {
        this.mediator = mediator;
        this.player1 = player1;
        this.player2 = player2;
    }

    public Player getFirstPlayer() {
        return player1;
    }

    public Player getSecondPlayer() {
        return player2;
    }

    public Status getStatus() {
        return mediator.getStatus();
    }

    public Score.Game getCurrentGame() {
        return mediator.getCurrentGame();
    }

    public Score.Set getCurrentSet() {
        return mediator.getCurrentSet();
    }

    public Score.Set getSet(int n) {
        try {
            return mediator.getSet(n);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("Invalid set number [1 - Match.getSetCount()]: " + n);
        }
    }

    public int getSetCount() {
        return mediator.getSetCount();
    }

    public void printDetails() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Player 1 : ").append(player1.getName()).append('\n')
                .append("Player 2 : ").append(player2.getName()).append('\n');

        builder.append("Score : ")
                .append(mediator.streamSet().map(Score.Set::toString).collect(Collectors.joining("")));
        // Match is over
        Optional.ofNullable(mediator.getCurrentGame()).ifPresent(
                g -> builder.append('\n').append("Current game status : ").append(g.toString())
        );

        builder.append('\n').append("Match Status : ").append(getStatus().getLabel());

        System.out.print(builder.toString());
    }

}
