package assignment4Game;

import java.io.*;

public class Game {

	public static int play(InputStreamReader input) {
		BufferedReader keyboard = new BufferedReader(input);
		Configuration c = new Configuration();
		int columnPlayed = 3;
		int player;

		// first move for player 1 (played by computer) : in the middle of the grid
		c.addDisk(firstMovePlayer1(), 1);
		int nbTurn = 1;

		while (nbTurn < 42) { // maximum of turns allowed by the size of the grid
			player = nbTurn % 2 + 1;
			if (player == 2) {
				columnPlayed = getNextMove(keyboard, c, 2);
			}
			if (player == 1) {
				columnPlayed = movePlayer1(columnPlayed, c);
			}
			System.out.println(columnPlayed);
			c.addDisk(columnPlayed, player);
			if (c.isWinning(columnPlayed, player)) {
				c.print();
				System.out.println("Congrats to player " + player + " !");
				return (player);
			}
			nbTurn++;
		}
		return -1;
	}

	public static int getNextMove(BufferedReader keyboard, Configuration c, int player) {
		int anotherPlayer = player == 1 ? 2 : 1;
		int input = -1;
		while (true) {
			try {
				System.out.print("Enter your move (Integer number): ");
				input = Integer.valueOf(keyboard.readLine());
				if (c.available[input] != 6) {
					if (c.canWinNextRound(anotherPlayer) != -1) {
						System.out.println("Warning: Your opponent will win in next chance If you play this!");
						c.print();
						System.out.print("Please confirm again: ");
						input = Integer.valueOf(keyboard.readLine());
						if (c.available[input] != 6) {
							return input;
						}
					} else {
						System.out.println("Great. you are going good!");
						break;
					}

				} else
					System.out
							.println("Error: No space left in this Coulmn " + input + ", Please try different column!");
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
		return input;
	}

	public static int firstMovePlayer1() {
		return 3;
	}

	public static int movePlayer1(int columnPlayed2, Configuration c) {
		int player = 1;
		int next = c.canWinNextRound(player);

		if (next != -1) {
			return next;
		} else {
			int next2next = c.canWinTwoTurns(player);
			if (next2next != -1) {
				return next2next;
			} else {
				int last = columnPlayed2;
				if (last > -1 && last < 7 && c.available[last] != 6) {
					return last;
				}

				int moveBack = last, moveNext = last;
				boolean check = false;
				while (moveBack > -1 || moveNext < 7) {
					if (!check && --moveBack > -1 && c.available[moveBack] != 6) {
						return moveBack;
					}

					if (check && ++moveNext < 7 && c.available[moveNext] != 6) {
						return moveNext;
					}

					check = !check;
				}
			}
		}
		return -1;
	}

}
