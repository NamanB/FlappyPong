import processing.core.PApplet;

public class Ball {
	PApplet game;
	
	int ballX = 0;
	int ballY = 0;
	int ballSize = 20;
	int ballColor = game.color(0);
	
	float verticalBallSpeed = 0;
	float horizontalBallSpeed = 10;
	float health = 100;
	float healthDecrease = 1;
	float healthBarWidth = 60;
	
	public Ball(PApplet p) {
		this.game = p;
	}
	
	public Ball(PApplet p, int size ,int color) {
		this.game = p;
		this.ballSize = size;
		this.ballColor = game.color(color);
	}
	
	public void draw() {
		game.fill(ballColor);
		game.ellipse(ballX, ballY, ballSize, ballSize);
	}
}
