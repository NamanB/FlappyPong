import processing.core.PApplet;
import processing.core.PConstants;

public class Ball {
	PApplet game;
	
	int ballX = 0;
	int ballY = 0;
	int ballSize = 20;
	int ballColor = game.color(0);
	
	float verticalBallSpeed = 0;
	float horizontalBallSpeed = 10;
	float maxHealth = 100;
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
		drawHealth();
	}
	
	public void drawHealth() {
		game.noStroke(); // no border
		game.fill(236, 240, 241);
		game.rectMode(PConstants.CORNER);
		game.rect(ballX - (healthBarWidth / 2), ballY - 30, healthBarWidth, 5);
		if (health > 60) {
			game.fill(46, 204, 113);
		} else if (health > 30) {
			game.fill(230, 126, 34);
		} else {
			game.fill(231, 76, 60);
		}
		game.rectMode(PConstants.CORNER);
		game.rect(ballX - (healthBarWidth / 2), ballY - 30, healthBarWidth * (health / maxHealth), 5);
	}
}
