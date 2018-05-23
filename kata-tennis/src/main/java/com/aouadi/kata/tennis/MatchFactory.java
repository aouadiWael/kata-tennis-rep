package com.aouadi.kata.tennis;

import com.aouadi.kata.tennis.impl.DelegateInvocationHandler;
import com.aouadi.kata.tennis.impl.ScoreMediator;
import com.aouadi.kata.tennis.impl.SingleMatch;
import com.aouadi.kata.tennis.impl.SinglePlayer;

import java.lang.reflect.Proxy;

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
        // Validate the names
        if (name1 == null || name1.isEmpty() || name2 == null || name2.isEmpty())
            throw new TennisRuntimeException("A player's name cannot be null or empty");
        if (name1.equals(name2))
            throw new TennisRuntimeException("A players name are the identical.");

        // Create the proxy of mediator to construct players
        final DelegateInvocationHandler<MatchMediator> handlerInvocation = new DelegateInvocationHandler() ;
        final MatchMediator mediatorProxy = (MatchMediator) Proxy.newProxyInstance(
                MatchMediator.class.getClassLoader(), new Class[]{MatchMediator.class}, handlerInvocation
        );
        // Thanks to Java Proxy, now we can create the players
        final SinglePlayer p1 = new SinglePlayer(name1, mediatorProxy);
        final SinglePlayer p2 = new SinglePlayer(name2, mediatorProxy);
        // Create the real mediator that depends on players
        final MatchMediator mediator = new ScoreMediator(p1, p2);
        // Initialize the proxy by setting the real mediator
        handlerInvocation.setDelegate(mediator);
        // Create Match and return it
        return new SingleMatch(p1, p2, mediator);
    }
}