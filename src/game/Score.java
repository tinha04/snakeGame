package game;

public class Score {
	private int score; 
	
	public Score(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void addScore() {
		score++;
	}
	
	public void resetScore() {
		score = 0;
	}
}
