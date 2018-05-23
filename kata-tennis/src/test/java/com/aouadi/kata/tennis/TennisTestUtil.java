package com.aouadi.kata.tennis;

import com.aouadi.kata.tennis.impl.SetScore;

/**
 * 
 * @author Wael.Aouadi
 *
 */
public class TennisTestUtil {

    private TennisTestUtil() {
    }

    /************************** Utility Methods For Match **************************/

    private static void winBall(Player p, int n) {
        for (int i = 0; i < n; i++) {
            p.winPoint();
        }
    }

    public static void winBall(Player p1, int n1, Player p2, int n2) {
        if (n1 < n2) {
            winBall(p1, n1);
            winBall(p2, n2);
        } else {
            winBall(p2, n2);
            winBall(p1, n1);
        }
    }

    public static void winGame(Player p, int n) {
        winBall(p, n * 4);
    }

    public static void winGame(Player p1, int n1, Player p2, int n2) {
        if (n1 < n2) {
            winGame(p1, n1);
            winGame(p2, n2);
        } else {
            winGame(p2, n2);
            winGame(p1, n1);
        }
    }


    /************************** Utility Methods For Set Score **************************/

    public static SetScore initSet(SetScore set, Player.Id pid, int p) {
        for (int i = 0; i < p; i++) {
            set.winPoint(pid);
        }
        return set;
    }

    public static SetScore createSet(Player.Id pid, int p) {
        return initSet(new SetScore(), pid, p);
    }

    public static SetScore createSet(Player.Id pid1, int p1, Player.Id pid2, int p2) {
        if (p1 < p2) {
            final SetScore set = createSet(pid1, p1);
            return initSet(set, pid2, p2);
        }
        final SetScore set = createSet(pid2, p2);
        return initSet(set, pid1, p1);
    }
}
