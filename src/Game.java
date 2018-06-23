import processing.core.*;

public class Game extends PApplet {
	int activeScreen = 0; // 0 = Initial Screen, 1 = Game Screen, 2 = Game-Over Screen
	
	public static void main(String[] args) {
		PApplet.main("Game");
	}
	
	public void settings() {
		size(500, 500);
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
