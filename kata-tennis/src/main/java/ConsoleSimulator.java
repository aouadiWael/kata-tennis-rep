import java.util.Random;

import com.aouadi.kata.tennis.Match;
import com.aouadi.kata.tennis.MatchFactory;
import com.aouadi.kata.tennis.Player;
import com.aouadi.kata.tennis.Status;

/**
 * 
 * @author Wael.Aouadi
 *
 */
public class ConsoleSimulator {

	private static final Random RANDOM = new Random();

	public static void main(String[] args) {
		final Match match = MatchFactory.createMatch("Player 1", "Player 2");
		final Player first = match.getFirstPlayer();
		final Player second = match.getSecondPlayer();

		printMatch(match);
		do {
			randomPlayer(first, second).winPoint();
			printMatch(match);
		} while (match.getStatus().equals(Status.IN_PROGRESS));

	}

	private static void printMatch(Match match) {
		try {
			System.out.print("\n\f\n");
			match.printDetails();
			// Thread.sleep(1000);
		} catch (Throwable ignore) {
		}
	}

	private static Player randomPlayer(Player p1, Player p2) {
		int i = RANDOM.nextInt();
		if (i % 2 == 0) {
			return p1;
		}
		return p2;
	}
}
