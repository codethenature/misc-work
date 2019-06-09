package assignment4Game;

public class Configuration {

	public int[][] board;
	public int[] available;
	boolean spaceLeft;

	public Configuration() {
		board = new int[7][6];
		available = new int[7];
		spaceLeft = true;
	}

	public void print() {
		System.out.println("| 0 | 1 | 2 | 3 | 4 | 5 | 6 |");
		System.out.println("+---+---+---+---+---+---+---+");
		for (int i = 0; i < 6; i++) {
			System.out.print("|");
			for (int j = 0; j < 7; j++) {
				if (board[j][5 - i] == 0) {
					System.out.print("   |");
				} else {
					System.out.print(" " + board[j][5 - i] + " |");
				}
			}
			System.out.println();
		}
	}

	public void addDisk(int index, int player) {
		board[index][available[index]] = player;
		available[index]++;
		for (int i = 0; i < 7; i++) {
			if (available[i] != 6)
				break;
			if (i == 6)
				spaceLeft = false;
		}
	}

	public boolean isWinning(int lastColumnPlayed, int player) {
		int lastJ = available[lastColumnPlayed] - 1;
		int lastI = lastColumnPlayed;
		boolean ok;
		int i, j, count;

		for (i = lastI, j = lastJ, count = 0; i < 7 && board[i][j] == player; i++, count++)
			; // right side count
		for (i = lastI - 1, j = lastJ; i > -1 && board[i][j] == player; i--, count++)
			; // left side count

		ok = count > 3;
		if (ok)
			return true;

		for (i = lastI, j = lastJ, count = 0; j > -1 && board[i][j] == player; j--, count++)
			; // down side count

		ok = count > 3;
		if (ok)
			return true;

		for (i = lastI, j = lastJ, count = 0; i < 7 && j < 6 && board[i][j] == player; i++, j++, count++)
			; // cross right up side count
		for (i = lastI - 1, j = lastJ - 1; i > -1 && j > -1 && board[i][j] == player; i--, j--, count++)
			; // cross left down side count

		ok = count > 3;
		if (ok)
			return true;

		for (i = lastI, j = lastJ, count = 0; i > -1 && j < 6 && board[i][j] == player; i--, j++, count++)
			; // cross left up side count
		for (i = lastI + 1, j = lastJ - 1; i < 7 && j > -1 && board[i][j] == player; i++, j--, count++)
			; // cross right down side count

		ok = count > 3;
		if (ok)
			return true;

		return false;
	}

	public int canWinNextRound(int player) {
		for (int i = 0; i < 7; i++) {
			if (available[i] != 6) {
				addDisk(i, player);
				boolean ok = isWinning(i, player);
				// remove disk
				removeDisk(i);
				if (ok)
					return i;
			}
		}
		return -1;
	}

	public int canWinTwoTurns(int player) {
		// check first second player can win or not.
		int anotherPlayer = player == 1 ? 2 : 1;

		int anotherPlayerFirstWinI = canWinNextRound(anotherPlayer);

		if (anotherPlayerFirstWinI != -1) {
			// Then our player must add his disk into this column.

			addDisk(anotherPlayerFirstWinI, player);

			int anotherPlayerSecondWinI = canWinNextRound(anotherPlayer);
			if (anotherPlayerSecondWinI != -1) {
				removeDisk(anotherPlayerFirstWinI);
				return -1;
			} else {
				int playerWinI = canWinNextRound(player);

				if (playerWinI != -1) {
					addDisk(playerWinI, anotherPlayer);
					if (canWinNextRound(player) != -1) {
						removeDisk(playerWinI);
						removeDisk(anotherPlayerFirstWinI);
						return anotherPlayerFirstWinI;
					}
					removeDisk(playerWinI);
				} else {
					for (int i = 0; i < 7; i++) {
						if (available[i] == 6)
							continue;
						addDisk(i, anotherPlayer);
						if (canWinNextRound(player) != -1) {
							removeDisk(i);
							removeDisk(anotherPlayerFirstWinI);
							return anotherPlayerFirstWinI;
						}
						removeDisk(i);
					}
				}

			}
			removeDisk(anotherPlayerFirstWinI);
		} else {
			for (int i = 0; i < 7; i++) {
				if (available[i] == 6)
					continue;

				addDisk(i, player);

				int playerWinI = canWinNextRound(player);

				if (playerWinI != -1) {
					addDisk(playerWinI, anotherPlayer);
					if (canWinNextRound(player) != -1) {
						removeDisk(playerWinI);
						removeDisk(i);
						return i;
					}
					removeDisk(playerWinI);
				} else {
					for (int j = 0; j < 7; j++) {
						if (available[j] == 6)
							continue;
						addDisk(j, anotherPlayer);
						if (canWinNextRound(player) != -1) {
							removeDisk(i);
							removeDisk(j);
							return i;
						}
						removeDisk(j);
					}
				}
				removeDisk(i);
			}
		}
		return -1;
	}

	// helper function
	private void removeDisk(int index) {
		board[index][--available[index]] = 0;
		if (spaceLeft == false)
			spaceLeft = true;
	}
}
