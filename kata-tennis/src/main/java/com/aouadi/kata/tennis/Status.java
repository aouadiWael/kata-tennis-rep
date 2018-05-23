package com.aouadi.kata.tennis;

/**
 * @author Wael.Aouadi
 * Match status enum
 */
public enum Status {

    IN_PROGRESS("In progress"), PLAYER1_WINS("Player 1 wins"), PLAYER2_WINS("Player 2 wins");

    private final String label;

    Status(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}