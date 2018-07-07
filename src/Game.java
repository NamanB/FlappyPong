import java.util.ArrayList;

import processing.core.*;

public class Game extends PApplet {
	//TODO add threads to separate ticking and rendering
	private int activeScreen = 0; // 0 = Initial Screen, 1 = Game Screen, 2 = Game-Over Screen
//	private int gameMode = 0;
	public int score = 0;

	public float gravity = 1;
	public float airFriction = (float) (0.0001);
	public float friction = (float) (.1);
	public float xRacketBounceCoefficient = (float) (0.17);
	
	public int wallInterval = 1500;
	public float lastAddTime = 0;
	
	Racket racket;

	ArrayList<Ball> balls = new ArrayList<Ball>();
	ArrayList<Wall> walls = new ArrayList<Wall>();
	
	public static void main(String[] args) {
		PApplet.main("Game");
	}
	
	public void settings() {
		size(500, 500);
	}
	
	public void setup() {
		racket = new Racket(this);
		Ball b = new Ball(this, 20, 0);
		b.ballX = width / 4;
		b.ballY = height / 5;
		balls.add(b);
	}
	
	public void draw() {
		if (activeScreen == 0)
			initScreen();
		else if (activeScreen == 1)
			gameScreen();
		else if (activeScreen == 2)
			gameOverScreen();
	}

	//Screens
	public void initScreen() {
		background(198, 242, 221);
		textAlign(CENTER);
		fill(37, 104, 85);
		textSize(80);
		text("Flappy Pong", width/2, height/2);
		textSize(15); 
		text("Click to start", width/2, height-30);
	}
	
	public void gameScreen() {
		tickGameScreen();
		renderGameScreen();
	}
	
	public void renderGameScreen() {
		background(255);
		racket.draw();
		drawBalls();
	}
	
	public void tickGameScreen() {
		watchRacketBounce();
		updateBalls();
	}
	
	public void gameOverScreen() {
		
	}
	
	public void drawBalls() { 
		for (Ball b : balls) {
			b.draw();
		}
	}
	
	public void updateBalls() {
		for (Ball b : balls) {
			b.keepInScreen(friction, gravity);
		}
	}
	
	public void watchRacketBounce() {
		float mouseFrameDist = mouseY - pmouseY;
		for (Ball b : balls) {
			if ((b.ballX + b.ballSize / 2 > mouseX - racket.racketWidth / 2) && (b.ballX - b.ballSize / 2 < mouseX + racket.racketWidth / 2)) {
				if (dist(b.ballX, b.ballY, b.ballX, mouseY) <= (b.ballSize / 2) + abs(mouseFrameDist)) {
					b.bottomBounce(mouseY, friction, gravity);
					b.horizontalBallSpeed = (b.ballX - mouseX) * xRacketBounceCoefficient;
					// carry upward speed of racket into ball if moving up into the ball
					if (mouseFrameDist < 0) {
						b.ballY += mouseFrameDist;
						b.verticalBallSpeed += mouseFrameDist;
					}
				}
			}
		}
	}

	
}
