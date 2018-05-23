package com.aouadi.kata.tennis.impl;

import com.aouadi.kata.tennis.*;

/**
 * @author Wael.Aouadi
 * Represents a a tennis match of two single players
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

    @Override
    public Player getFirstPlayer() {
        return player1;
    }

    @Override
    public Player getSecondPlayer() {
        return player2;
    }

    @Override
    public Status getStatus() {
        return mediator.getStatus();
    }

    @Override
    public Score.Game getCurrentGame() {
        return mediator.getCurrentGame();
    }

    @Override
    public Score.Set scoreSetAt(int n) {
        return mediator.scoreSetAt(n);
    }

    @Override
    public int scoreSetCount() {
        return mediator.scoreSetCount();
    }

    @Override
    public void printTo(Printer printer) {
        mediator.printTo(printer);
    }

}
