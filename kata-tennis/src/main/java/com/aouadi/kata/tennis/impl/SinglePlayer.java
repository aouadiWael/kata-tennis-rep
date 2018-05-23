package com.aouadi.kata.tennis.impl;

import com.aouadi.kata.tennis.MatchMediator;
import com.aouadi.kata.tennis.Player;

import java.util.Objects;

/**
 * 
 * @author Wael.Aouadi
 *
 */
public class SinglePlayer implements Player{

    private final MatchMediator mediator;
    private final String name;

    public SinglePlayer(String name, MatchMediator mediator){
        Objects.requireNonNull(name);
        this.name = name;
        this.mediator = mediator;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void winPoint(){
        mediator.winPoint(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }


}
