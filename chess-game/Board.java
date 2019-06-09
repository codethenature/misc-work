import java.util.Random;

public class Board {
	
	private static int countCapturedBlack = 0;
	private static int countCapturedWhite = 0;
	private static int[] cache;
	
	int length, width;
	
	Random random;
	Knight[][] board;
	
	Board(int length, int width, Random random) {
		this.length = length;
		this.width = width;
		this.random = random;
		
		board =  new Knight[width][length];
		
	}
	
	public void randomPlace(Knight knight) {
		
		while(true) {
			int x = random.nextInt(Math.min(length, width) - 1);
			int y = random.nextInt(Math.min(length, width) - 1);
			
			if(board[x][y] == null) {
				board[x][y] = knight;
				break;
			}
		}
	}
	
	public void draw() {
		
		for(int i = 0; i< length; i++)
			System.out.print(" " + Character.toString( (char) (65 + i)));
		System.out.println();
		
		for(int i = 0 ; i < board.length ; i++) {
			
			for(int j = 0 ; j < board[i].length; j++) {
				System.out.print("|");
				System.out.print(whatIsOnBoard(i, j));
			}
			System.out.print("| " + (i+1));
			System.out.println();
			printdash();
			System.out.println();
			
		}
	}
	
	public boolean manualMove(String startLetter, int y1, String endLetter, int y2) {
		int x1 = ( (int) startLetter.charAt(0) ) - 65;
		int x2 = ( (int) endLetter.charAt(0) ) - 65;
		
		// board starts with 0
		y1--;
		y2--;
		
		
		if(board[y1][x1] != null && board[y1][x1].isWhite()) {
			
			if(x2 >= 0 && x2 < length && y2 >= 0 && y2 < width) {
				
				if(board[y2][x2] != null && board[y2][x2].isWhite()) {
					
					System.out.println("Invalid move");
					return false;
					
				} else {
					
					if(checkMove(x1, y1, x2, y2)) {
						if(board[y2][x2] != null && board[y2][x2].isBlack()) {
							System.out.println("You captured the black !!");
							countCapturedBlack++;
						}
							
						board[y2][x2] = board[y1][x1];
						board[y1][x1] = null;
						return true;
					}
						
					else {
						System.out.println("Invalid move");
						return false;
					}
				}
			} else {
				System.out.println("End position is not inside the board.");
				return false;
			}
		} else {
			System.out.println("The player does not have a piece at " + (char)(x1 + 65) + " " + (y1 + 1));
			return false;
		}
	}
	
	public void aiMove() {
		// For every black check surrounding valid position if white appears then capture it.
		for(int i = 0 ; i < board.length ; i++) {
			for(int j = 0 ; j < board[i].length; j++) {
				
				if(board[i][j] != null && board[i][j].isBlack()) {
					int[] ans = getAllPositions(i, j);
					if(ans != null) {
						board[ans[0]][ans[1]] = board[i][j];
						board[i][j] = null;
						System.out.println("AI moving from " + (char) (65+j) + " " + (i + 1) + " to " + (char) (65+ans[1]) + " " + (ans[0]+1));
						cache = null;
						return;
					}
				}
			}
		}
		// move to cached position
		board[cache[2]][cache[3]] = board[cache[0]][cache[1]];
		board[cache[0]][cache[1]] = null;
		System.out.println("AI moving from " + (char) (65+cache[1]) + " " + (cache[0] + 1) + " to " + (char) (65+cache[3]) + " " + (cache[2]+1));
		cache = null;
		return;
		
	}
	
	public int getCapturedPieces(int color) {
		if (color == 0)
			return countCapturedBlack;
		else
			return countCapturedWhite;
	}
	
	private String whatIsOnBoard(int i, int j) {
		if (board[i][j] == null)
			return  " ";
		
		else if (board[i][j].isWhite()) {
			return "N";
		}
		else {
			return "n";
		}
	}
	
	private void printdash() {
		for(int i = 0; i < 2*length ; i++)
			System.out.print("-");
	}
	
	private boolean checkMove(int x1, int y1, int x2, int y2) {
		int diffX = Math.abs(x1-x2);
		int diffY = Math.abs(y1-y2);
		
		if(( diffX == 1 && diffY == 2 ) || ( diffX == 2 && diffY == 1 ))
			return true;
		else
			return false;
	}
	
	private int[] getAllPositions(int x1, int y1) {
		int ans[] = null;
		
		int data[][]  = {
				{x1 -2, y1 -1},
				{x1 -2, y1 +1},
				{x1 -1, y1 -2},
				{x1 -1, y1 +2},
				{x1 +2, y1 -1},
				{x1 +2, y1 +1},
			    {x1 +1, y1 -2},
				{x1 +1, y1 +2}
		};
		
		for(int[] row : data) {
			if ( (ans = checkAIMove(x1, y1, row[0], row[1])) != null ) {
				return ans;
			}
		}
		return ans;
	}
	
	private boolean checkValidMove(int x, int y) {
		if(x >= 0 && x < board.length && y >= 0 && y < board[0].length)
			return true;
		else
			return false;
	}
	
	private int[] checkAIMove(int x1, int y1, int x2, int y2) {
		
		if(checkValidMove(x2, y2) ) {
			
			if(board[x2][y2] != null) {
				
				if(board[x2][y2].isWhite()) {
					// Found!
					System.out.println("AI capturing white at position " + (char) (65 + y2) + " " +  (1 + x2));
					countCapturedWhite++;
					return new int[] {x2, y2};
					
				} else {
					// Skip. Its me.
					return null;
				}
			} else {
				// just for case when I don't found any one.
				cache = new int[] {x1, y1, x2, y2};
				return null;
			}
		} else {
			return null;
		}
	}
}
