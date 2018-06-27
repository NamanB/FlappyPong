import processing.core.PApplet;

public class Ball {
	PApplet game;
	
	int ballX = 0;
	int ballY = 0;
	int ballSize = 20;
	int ballColor = game.color(0);
	
	public Ball(PApplet p) {
		this.game = p;
	}
	
	public Ball(PApplet p, int color) {
		this.game = p;
		this.ballColor = game.color(color);
	}
}
