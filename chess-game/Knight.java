
public class Knight {
	static final int WHITE = 1;
	static final int BLACK = 0;
	
	int color;
	
	Knight(int color) {
		this.color = color;
	}
	
	public boolean isWhite() {
		return color == WHITE;
	}
	
	public boolean isBlack() {
		return color == BLACK;
	}
}
