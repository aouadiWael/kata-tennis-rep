package com.aouadi.kata.tennis.impl;

import com.aouadi.kata.tennis.*;


import java.util.*;
import java.util.stream.Stream;

/**
 * 
 * @author Wael.Aouadi
 *
 */
public class ScoreMediator implements MatchMediator {

    private final Player player1;
    private final Player player2;
    private final int[] setsWon = {0, 0};

    private List<Score.Set> sets;
    private Status status;
    private SetScore currentSet;


    public ScoreMediator(Player player1, Player player2) {
        sets = new ArrayList<>(5);
        this.player1 = player1;
        this.player2 = player2;
        Objects.requireNonNull(player1);
        Objects.requireNonNull(player2);
        status = Status.IN_PROGRESS;
        startSet();
    }

    @Override
    public Score.Game getCurrentGame() {
        //return currentSet != null ? currentSet.getCurrentGame() : null;
        return Optional.ofNullable(currentSet).map(SetScore::getCurrentGame).orElse(null);
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public Score.Set getCurrentSet() {
        return currentSet;
    }

    @Override
    public int getSetCount() {
        return sets.size();
    }

    @Override
    public Score.Set getSet(int i) {
        return sets.get(i -1);
    }

    @Override
    public Stream<Score.Set> streamSet() {
        return sets.stream();
    }

    @Override
    public void winPoint(Player player) {
        if(!Status.IN_PROGRESS.equals(status)){
            throw new TennisException("The match is over.");
        }
        boolean setOver;
        if (player == player1) {
            setOver = currentSet.winPoint(Player.Id.FIRST);
        } else if (player == player2) {
            setOver = currentSet.winPoint(Player.Id.SECOND);
        } else {
            throw new IllegalArgumentException("Invalid player");
        }
        if (setOver) {
            startSet();
        }
    }

    @Override
    public boolean isOver() {
        return setsWon[0] == 3 || setsWon[1] == 3;
    }

    private void startSet() {
        if (currentSet != null && currentSet.getWinner() != Player.Id.NONE) {
            ++ setsWon[currentSet.getWinner().ordinal()];
        }
        if (!this.isOver()) {
            currentSet = new SetScore();
            sets.add(currentSet);
        } else {
            if (setsWon[0] == 3) {
                status = Status.PLAYER1_WINS;
            } else {
                status = Status.PLAYER2_WINS;
            }
            sets = Collections.unmodifiableList(sets);
            currentSet = null;
        }
    }

}
