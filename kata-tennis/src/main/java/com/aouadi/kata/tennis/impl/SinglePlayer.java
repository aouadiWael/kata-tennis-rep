package com.aouadi.kata.tennis.impl;

import com.aouadi.kata.tennis.MatchMediator;
import com.aouadi.kata.tennis.Player;

import java.util.Objects;

/**
 * 
 * @author Wael.Aouadi
 *
 */
public class SinglePlayer implements Player {

    private final MatchMediator mediator;
    private final String name;

    public SinglePlayer(String name, MatchMediator mediator) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(name);
        this.name = name;
        this.mediator = mediator;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void winPoint() {
        mediator.winPoint(this);
    }

    @Override
    public String toString() {
        return name;
    }


}
