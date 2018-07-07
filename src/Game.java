import java.util.ArrayList;

import processing.core.*;

public class Game extends PApplet {
	//TODO add threads to separate ticking and rendering
	//TODO add multiple game modes using the overloaded constructors to change speeds
	private int activeScreen = 0; // 0 = Initial Screen, 1 = Game Screen, 2 = Game-Over Screen
//	private int gameMode = 0;
	public int score = 0;

	public float gravity = 1;
	public float airFriction = (float) (0.0001);
	public float friction = (float) (.1);
	public float xRacketBounceCoefficient = (float) (0.17);
	
	public int minGapHeight = 200;
	public int maxGapHeight = 300;
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
		addStartingBall();
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
		//regular render method components
		background(255);
		racket.render();
		drawBalls();
		printScore();
		drawWalls();
	}
	
	//to be used in tick thread
	public void tickGameScreen() {
		watchRacketBounce();
		updateBalls();
		wallAdder();
		updateWalls();
	}
	
	public void gameOverScreen() {
		background(37, 104, 85);
		textAlign(CENTER);
		fill(240, 240, 240);
		textSize(16);
		text("Your Score", width/2, height/2 - 120);
		textSize(132);
		text(score, width/2, height/2);
		textSize(16);
		text("Click to Restart", width/2, height-30);
	}
	
	// Inputs
	public void mousePressed() {
		if (activeScreen == 0) {
			startGame();
		}
		if (activeScreen == 2) {
			restart();
		}
	}
	
	//Functions
	public void startGame() {
		activeScreen = 1;
	}

	public void gameOver() {
		activeScreen = 2;
	}
	
	public void restart() {
		score = 0;
		balls.clear();
		addStartingBall();
		lastAddTime = 0;
		walls.clear();
		activeScreen = 1;
	}
	
	public void addStartingBall() {
		Ball b = new Ball(this, 20, 0);
		b.ballX = width / 4;
		b.ballY = height / 5;
		balls.add(b);
	}
	
	public void drawBalls() { 
		for (Ball b : balls) {
			b.render();
		}
	}
	
	public void updateBalls() {
		for (int i = 0; i < balls.size(); i++) {
			Ball b = balls.get(i);
			if (!b.isAlive) {
				balls.remove(b);
				i--;
				if (balls.size() == 0)
					gameOver();
			}
			b.move(gravity, airFriction);
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
	
	public void drawWalls() {
		for (Wall w : walls) {
			w.render();
		}
	}
	
	public void wallAdder() {
		if (millis() - lastAddTime > wallInterval) {
			int randHeight = round(random(minGapHeight, maxGapHeight));
			int randY = round(random(0, height - randHeight));
			Wall w = new Wall(this, width, randY, randHeight);
			walls.add(w);
			lastAddTime = millis();
		}
	}
	
	public void updateWalls() {
		for (int i = 0; i < walls.size(); i++) {
			Wall w = walls.get(i);
			if (w.gapWallX + w.wallWidth <= 0) {
				walls.remove(i);
			} else {
				w.updateWall();
				watchWallCollision(w);
			}
		}
	}
	
	/**
	 * Checks for balls colliding with wall w
	 * @param w the wall to be checked
	 */
	public void watchWallCollision(Wall w) {
		int wallTopY = 0;
		int wallBottomY = w.gapWallY + w.gapWallHeight;
		int wallBottomHeight = height - (w.gapWallY + w.gapWallHeight);

		for (Ball b : balls) {
			if ((b.ballX + (b.ballSize / 2) > w.gapWallX) && (b.ballX - (b.ballSize / 2) < w.gapWallX + w.wallWidth)
					&& (b.ballY + (b.ballSize / 2) > wallTopY) && (b.ballY - (b.ballSize / 2) < wallTopY + w.gapWallY)) {
				// collides with upper wall
				b.takeDamage();
			} 
			
			if ((b.ballX + (b.ballSize / 2) > w.gapWallX) && (b.ballX - (b.ballSize / 2) < w.gapWallX + w.wallWidth)
					&& (b.ballY + (b.ballSize / 2) > wallBottomY)
					&& (b.ballY - (b.ballSize / 2) < wallBottomY + wallBottomHeight)) {
				// collides with lower wall
				b.takeDamage();
			} 
			
			if (b.ballX > w.gapWallX + (w.wallWidth / 2) && !w.scoredBalls.contains(b)) {
				w.addBallScored(b);
				score();
			}
		}
	}
	
	public void score() {
		score++;
	}

	public void printScore() {
		textAlign(CENTER);
		fill(0);
		textSize(30);
		text(score, height / 2, 50);
	}

}
