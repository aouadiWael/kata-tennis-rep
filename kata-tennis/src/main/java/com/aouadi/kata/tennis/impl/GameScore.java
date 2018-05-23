package com.aouadi.kata.tennis.impl;

import com.aouadi.kata.tennis.Player;
import com.aouadi.kata.tennis.Score;

/**
 * @author Wael.Aouadi
 * Strategy Design Pattern
 * Represents the standard game without tie-break
 */
public class GameScore implements Score.Game {

    private StateComposite state;

    public GameScore() {
        this.state = StateComposite.ZERO_ALL;
    }

    public boolean winPoint(Player.Id pid) {
        switch (pid) {
            case FIRST:
                state = state.getNext1();
                return state.equals(StateComposite.WINNER_1);
            case SECOND:
                state = state.getNext2();
                return state.equals(StateComposite.WINNER_2);
        }
        throw new IllegalStateException("Invalid Player Id: " + pid);
    }

    @Override
    public Player.Id getWinner() {
        switch (state) {
            case WINNER_1:
                return Player.Id.FIRST;
            case WINNER_2:
                return Player.Id.SECOND;
            default:
                return Player.Id.NONE;
        }
    }

    @Override
    public boolean isOver() {
        return StateComposite.WINNER_1.equals(state) || StateComposite.WINNER_2.equals(state);
    }

    @Override
    public String toString() {
        return state.toString();
    }

    /**
     * Composite design Pattern
     * Represents the limited states graph of a tennis game score.
     */
    @SuppressWarnings("unused")
    enum StateComposite {

        WINNER_1(State.WINNER_1, State.WINNER_1, State.WINNER_1),
        WINNER_2(State.WINNER_2, State.WINNER_2, State.WINNER_2),

        ADVANTAGE_1(State.ADVANTAGE_1, State.WINNER_1, State.FORTY_ALL),
        ADVANTAGE_2(State.ADVANTAGE_2, State.FORTY_ALL, State.WINNER_2),

        ZERO_ALL(State.ZERO_ALL, State.FIFTEEN_ZERO, State.ZERO_FIFTEEN),
        ZERO_FIFTEEN(State.ZERO_FIFTEEN, State.FIFTEEN_ALL, State.ZERO_THIRTY),
        ZERO_THIRTY(State.ZERO_THIRTY, State.FIFTEEN_THIRTY, State.ZERO_FORTY),
        ZERO_FORTY(State.ZERO_FORTY, State.FIFTEEN_FORTY, State.WINNER_2),

        FIFTEEN_ZERO(State.FIFTEEN_ZERO, State.THIRTY_ZERO, State.FIFTEEN_ALL),
        FIFTEEN_ALL(State.FIFTEEN_ALL, State.THIRTY_FIFTEEN, State.FIFTEEN_THIRTY),
        FIFTEEN_THIRTY(State.FIFTEEN_THIRTY, State.THIRTY_ALL, State.FIFTEEN_FORTY),
        FIFTEEN_FORTY(State.FIFTEEN_FORTY, State.THIRTY_FORTY, State.WINNER_2),

        THIRTY_ZERO(State.THIRTY_ZERO, State.FORTY_ZERO, State.THIRTY_FIFTEEN),
        THIRTY_FIFTEEN(State.THIRTY_FIFTEEN, State.FORTY_FIFTEEN, State.THIRTY_ALL),
        THIRTY_ALL(State.THIRTY_ALL, State.FORTY_THIRTY, State.THIRTY_FORTY),
        THIRTY_FORTY(State.THIRTY_FORTY, State.FORTY_ALL, State.WINNER_2),

        FORTY_ALL(State.FORTY_ALL, State.ADVANTAGE_1, State.ADVANTAGE_2),
        FORTY_ZERO(State.FORTY_ZERO, State.WINNER_1, State.FORTY_FIFTEEN),
        FORTY_FIFTEEN(State.FORTY_FIFTEEN, State.WINNER_1, State.FORTY_THIRTY),
        FORTY_THIRTY(State.FORTY_THIRTY, State.WINNER_1, State.FORTY_ALL);

        /**
         * The state graph with transitions
         * <p>{@code current} represents the current state of the graph's node</p>
         * <p>Depending on who wins the point the (transition) then the next state is computed statically </p>
         * <pre>
         *     - When the player 1 wins a point the next state is {@code next1}
         *     - When the player 2 wins a point the next state is {@code next2}
         * </pre>
         */
        private State current, next1, next2;

        StateComposite(State current, State next1, State next2) {
            this.current = current;
            this.next1 = next1;
            this.next2 = next2;
        }

        public StateComposite getNext1() {
            return StateComposite.valueOf(next1.name());
        }

        public StateComposite getNext2() {
            return StateComposite.valueOf(next2.name());
        }

        @Override
        public String toString() {
            return current.toString();
        }
    }

    /**
     * Represents the internal states of a tennis game score.
     */
    enum State {

        WINNER_1("Player 1 wins"),
        WINNER_2("Player 2 wins"),

        ADVANTAGE_1("Player 1 has advantage"),
        ADVANTAGE_2("Player 2 has advantage"),

        ZERO_ALL("0-0"),
        FIFTEEN_ALL("15-15"),
        THIRTY_ALL("30-30"),
        FORTY_ALL("deuce"),

        ZERO_FIFTEEN("0-15"),
        ZERO_THIRTY("0-30"),
        ZERO_FORTY("0-40"),

        FIFTEEN_ZERO("15-0"),
        FIFTEEN_THIRTY("15-30"),
        FIFTEEN_FORTY("15-40"),

        THIRTY_ZERO("30-0"),
        THIRTY_FIFTEEN("30-15"),
        THIRTY_FORTY("30-40"),

        FORTY_ZERO("40-0"),
        FORTY_FIFTEEN("40-15"),
        FORTY_THIRTY("40-30");

        private final String status;

        State(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return status;
        }
    }
}
