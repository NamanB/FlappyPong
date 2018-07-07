import java.util.ArrayList;

import processing.core.*;

public class Game extends PApplet {
	int activeScreen = 0; // 0 = Initial Screen, 1 = Game Screen, 2 = Game-Over Screen
	int score = 0;

	float gravity = 1;
	float airFriction = (float) (0.0001);
	float friction = (float) (.1);

	ArrayList<Ball> balls = new ArrayList<Ball>();
	ArrayList<Wall> walls = new ArrayList<Wall>();
	
	public static void main(String[] args) {
		PApplet.main("Game");
	}
	
	public void settings() {
		size(500, 500);
	}
	
	public void setup() {
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
		
	}
	
	public void gameScreen() {
		
	}
	
	public void gameOverScreen() {
		
	}
	
}
