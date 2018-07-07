import java.util.ArrayList;

import processing.core.*;

public class Game extends PApplet {
	int activeScreen = 0; // 0 = Initial Screen, 1 = Game Screen, 2 = Game-Over Screen
	int gameMode = 0;
	int score = 0;

	float gravity = 1;
	float airFriction = (float) (0.0001);
	float friction = (float) (.1);
	float xRacketBounceCoefficient = (float) (0.17);
	
	int wallInterval = 1500;
	float lastAddTime = 0;
	
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
		background(255);
		racket.draw();
		watchRacketBounce();

	}
	
	public void gameOverScreen() {
		
	}
	
	public void watchRacketBounce() {
		float mouseFrameDist = mouseY - pmouseY;
		for (Ball b : balls) {
			if ((b.ballX + b.ballSize / 2 > mouseX - racket.racketWidth / 2) && (b.ballX - b.ballSize / 2 < mouseX + racket.racketWidth / 2)) {
				if (dist(b.ballX, b.ballY, b.ballX, mouseY) <= (b.ballSize / 2) + abs(mouseFrameDist)) {
					bottomBounce(mouseY, b);
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

	public void bottomBounce(float surface, Ball b) {
		bounce(surface, true, b);
	}
	
	public void bounce(float surface, boolean isVertical, Ball b) {
		if (isVertical) {
			b.ballY = (int) (surface + -b.ballSize / 2);
			b.verticalBallSpeed *= -1;
			// corrects for constant gravity speed increase being larger than decrease on
			// bounce
			b.verticalBallSpeed -= (b.verticalBallSpeed * friction) - (gravity + (gravity * friction));
		} else {
			b.ballX = (int) (surface + -b.ballSize / 2);
			b.horizontalBallSpeed *= -1;
			b.horizontalBallSpeed -= b.horizontalBallSpeed * friction;
		}
	}
	
}
