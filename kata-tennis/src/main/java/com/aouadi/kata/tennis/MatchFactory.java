package com.aouadi.kata.tennis;

import java.util.stream.Stream;

import com.aouadi.kata.tennis.impl.ScoreMediator;
import com.aouadi.kata.tennis.impl.SingleMatch;
import com.aouadi.kata.tennis.impl.SinglePlayer;

/**
 * @author Wael.Aouadi
 * 
 * Factory Design Pattern
 */
public interface MatchFactory {

    /**
     * Create a Tennis Patch
     *
     * @param name1 the name of the first player
     * @param name2 the name of the second player
     * @return {@link Match}
     */
    static Match createMatch(String name1, String name2) {

        if (name1 == null || name1.isEmpty() || name2 == null || name2.isEmpty()) {
            throw new TennisException("A player's name cannot be null or empty");
        }
        if (name1.equals(name2)) {
            throw new TennisException("A players name are the identical.");
        }
        // Create the proxy to construct players
        final ScoreMediatorProxy mediatorProxy = new ScoreMediatorProxy();
        final SinglePlayer p1 = new SinglePlayer(name1, mediatorProxy);
        final SinglePlayer p2 = new SinglePlayer(name2, mediatorProxy);
        // Create the real mediator
        final ScoreMediator mediator = new ScoreMediator(p1, p2);
        // Now init the proxy by setting the real mediator
        mediatorProxy.delegate = mediator;
        return new SingleMatch(p1, p2, mediator);
    }

    /**
     * Proxy Design Pattern
     * Handles the methods invoke for MatchMediator instances.
     * It's a solution the the workaround the cyclic dependencies between
     * {@link SinglePlayer} and {@link ScoreMediator} classes.
     */
    class ScoreMediatorProxy implements MatchMediator{

        MatchMediator delegate;

        @Override
        public Score.Game getCurrentGame() {
            return delegate.getCurrentGame();
        }

        @Override
        public Status getStatus() {
            return delegate.getStatus();
        }

        @Override
        public Score.Set getCurrentSet() {
            return delegate.getCurrentSet();
        }

        @Override
        public int getSetCount() {
            return delegate.getSetCount();
        }

        @Override
        public Score.Set getSet(int i) {
            return delegate.getSet(i);
        }

        @Override
        public Stream<Score.Set> streamSet() {
            return delegate.streamSet();
        }

        @Override
        public void winPoint(Player player) {
            delegate.winPoint(player);
        }

        @Override
        public boolean isOver() {
            return delegate.isOver();
        }
    }
}
