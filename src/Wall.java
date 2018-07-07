import processing.core.PApplet;

public class Wall {
	PApplet game;
	
	int wallSpeed = 3;
	int minGapHeight = 200;
	int maxGapHeight = 300;
	int wallWidth = 80;
	int wallColor = game.color(37, 104, 85);
	
	public Wall(PApplet p) {
		game = p;
	}
	
	public Wall(PApplet p, int speed, int minGapHeight, int maxGapHeight, int width, int color) {
		game = p;
		this.wallSpeed = speed;
		this.minGapHeight = minGapHeight;
		this.maxGapHeight = maxGapHeight;
		this.wallWidth = width;
		this.wallColor = color;
	}
}
